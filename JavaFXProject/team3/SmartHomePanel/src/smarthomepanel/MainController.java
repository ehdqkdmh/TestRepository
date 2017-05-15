package smarthomepanel;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import smarthomepanel.notify.Data;

public class MainController implements Initializable {

	@FXML
	private Button btnVideo;
	@FXML
	private Button btnControl;
	@FXML
	private Button btnConsumed;
	@FXML
	private Button btnNotice;
	@FXML
	private Button btnSetting;
	@FXML
	private Button btnCall;
	@FXML
	private Label lblTime;
	@FXML
	private StackPane stackPane;
	@FXML
	private ImageView packImage;
	@FXML
	private ImageView noticeImage;

	ColorAdjust colorAdjust = new ColorAdjust();
	private ExecutorService executorService;
	private ServerSocket serverSocket;
	private boolean isBoxArrived = false;
	public static String VideoScreenState = "bfront";
	public static String callingBell = "setting/media/BIGBANG.mp3";

	public static double bellSound = 50;
	public static double screenBrightness = 50;
	public static boolean isNoticeAdded = false;
	public static ObservableList<Data> list = FXCollections.observableArrayList();
//	public static int waitTime = 0;
//	public static boolean stop = false;

	Media boxMedia = new Media(getClass().getResource("Box1.mp3").toString());
	MediaPlayer boxMediaPlayer = new MediaPlayer(boxMedia);
	Media bellMedia = new Media(getClass().getResource("bell.mp3").toString());
	MediaPlayer belllMediaPlayer = new MediaPlayer(bellMedia);

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		startServer();

		//시간정보 설정
		Thread thread = new Thread() {
			@Override
			public void run() {
				SimpleDateFormat sdf = new SimpleDateFormat("a hh:mm");
				while (true) {
					String strTime = sdf.format(new Date());
					colorAdjust.setBrightness((screenBrightness / 100) - 0.5);
					stackPane.setEffect(colorAdjust);
					Platform.runLater(() -> {
						lblTime.setText(strTime);
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		thread.start();

		//택배알림
		Thread boxThread = new Thread() {
			@Override
			public void run() {
				FadeTransition ft1 = new FadeTransition(Duration.millis(500), packImage);
				ft1.setFromValue(1.0);
				ft1.setToValue(0.1);
				ft1.setAutoReverse(true);
				ft1.setCycleCount(Timeline.INDEFINITE);
				while (true) {
					if (isBoxArrived == true) {
						packImage.setImage(new Image(getClass().getResource("images/icons/box.png").toString()));
						Platform.runLater(() -> {
							ft1.play();
						});
					} else if (isBoxArrived == false) {
						Platform.runLater(() -> {
							ft1.stop();
							packImage.setImage(new Image(getClass().getResource("images/icons/package.png").toString()));
						});
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		boxThread.start();

		//공지사항알림
		Thread noticeThread = new Thread() {
			@Override
			public void run() {
				FadeTransition ft2 = new FadeTransition(Duration.millis(500), noticeImage);
				ft2.setFromValue(1.0);
				ft2.setToValue(0.1);
				ft2.setAutoReverse(true);
				ft2.setCycleCount(Timeline.INDEFINITE);
				while (true) {
					if (isNoticeAdded == true) {
						noticeImage.setImage(new Image(getClass().getResource("images/icons/notepad(on).png").toString()));
						Platform.runLater(() -> {
							ft2.play();
						});
					} else if (isNoticeAdded == false) {
						Platform.runLater(() -> {
							ft2.stop();
							noticeImage.setImage(new Image(getClass().getResource("images/icons/notepad.png").toString()));
						});
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
					}
				}
			}
		};
		noticeThread.start();

		// 10초 대기 상태일 때 화면 꺼짐
//		Thread displayOffThread = new Thread() {
//			@Override
//			public void run() {
//				while (true) {
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException ex) {
//					}
//					if (!stop) {
//						waitTime += 1;
//						System.out.println(waitTime);
//						if (waitTime == 5) {
//							try {
//								Parent parent = FXMLLoader.load(getClass().getResource("ScreenSaver.fxml"));
//								Platform.runLater(() -> {
//									stackPane.getChildren().add(parent);
//									parent.setOpacity(0);
//								});
//								KeyValue keyValue = new KeyValue(parent.opacityProperty(), 1);
//								KeyFrame keyFrame = new KeyFrame(Duration.millis(100), keyValue);
//								Timeline timeLine = new Timeline();
//								timeLine.getKeyFrames().add(keyFrame);
//								timeLine.play();
//							} catch (IOException ex) {
//							}
//							stop = true;
//						}
//					}
//				}
//
//			}
//		};
//		displayOffThread.start();
		//버튼 눌렀을 때 화면 전환
		btnCall.setOnAction(e -> handleBtnCall(e));
		btnVideo.setOnAction(e -> handleBtnVideo(e));
		btnControl.setOnAction(e -> handleBtnControl(e));
		btnConsumed.setOnAction(e -> handleBtnConsumed(e));
		btnNotice.setOnAction(e -> handleBtnNotice(e));
		btnSetting.setOnAction(e -> handleBtnSetting(e));

//		stackPane.setOnMouseClicked(e -> {
//			waitTime = 0;
//			stop = false;
//			System.out.println("MainStackPane 클릭 waitTime 초기화");
//		});
	}

	private void boxArrive(boolean isBoxArrived) {
		boxMediaPlayer.stop();
		boxMediaPlayer.play();
		boxMediaPlayer.setVolume(bellSound / 100);

		System.out.println("택배가 도착했습니다");
		this.isBoxArrived = isBoxArrived;
	}

	@FXML
	private void handleBtnCheckArrived(ActionEvent e) {
		System.out.println("택배를 확인했습니다");
		this.isBoxArrived = false;
	}

	private void NoticeAdd(boolean isNoticeAdded) {
		boxMediaPlayer.stop();
		boxMediaPlayer.play();
		boxMediaPlayer.setVolume(bellSound / 100);
		System.out.println("공지사항이 추가되었습니다");
		this.isNoticeAdded = isNoticeAdded;
	}

	@FXML
	private void handleBtnCheckAdded(ActionEvent e) {
		System.out.println("공지사항을 확인했습니다");
		this.isNoticeAdded = false;
	}

	private void handleBtnCall(ActionEvent e) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("call/Call.fxml"));
			stackPane.getChildren().add(parent); // root.fxml의 stackpane보다 위에 Call.fxml이 쌓인다

			parent.setOpacity(0);

			KeyValue keyValue = new KeyValue(parent.opacityProperty(), 1);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
			Timeline timeLine = new Timeline();
			timeLine.getKeyFrames().add(keyFrame);
			timeLine.play();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private void handleBtnConsumed(ActionEvent e) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("chart/Chart.fxml"));
			stackPane.getChildren().add(parent); // root.fxml의 stackpane보다 위에 Chart.fxml이 쌓인다

			parent.setOpacity(0);

			KeyValue keyValue = new KeyValue(parent.opacityProperty(), 1);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
			Timeline timeLine = new Timeline();
			timeLine.getKeyFrames().add(keyFrame);
			timeLine.play();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void handleBtnVideo(ActionEvent e) {
		try {
			VideoScreenState = null;
			Parent parent = FXMLLoader.load(getClass().getResource("video/Video.fxml"));
			stackPane.getChildren().add(parent); // root.fxml의 stackpane보다 위에 Vidio.fxml이 쌓인다

			parent.setOpacity(0);

			KeyValue keyValue = new KeyValue(parent.opacityProperty(), 1);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
			Timeline timeLine = new Timeline();
			timeLine.getKeyFrames().add(keyFrame);
			timeLine.play();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private void handleBtnControl(ActionEvent e) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("control/Control.fxml"));
			stackPane.getChildren().add(parent);

			parent.setOpacity(0);

			KeyValue keyValue = new KeyValue(parent.opacityProperty(), 1);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
			Timeline timeLine = new Timeline();
			timeLine.getKeyFrames().add(keyFrame);
			timeLine.play();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void handleBtnNotice(ActionEvent e) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("notify/Notify.fxml"));
			stackPane.getChildren().add(parent);

			parent.setOpacity(0);

			KeyValue keyValue = new KeyValue(parent.opacityProperty(), 1);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
			Timeline timeLine = new Timeline();
			timeLine.getKeyFrames().add(keyFrame);
			timeLine.play();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void handleBtnSetting(ActionEvent e) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("setting/Setting.fxml"));
			stackPane.getChildren().add(parent);

			parent.setOpacity(0);

			KeyValue keyValue = new KeyValue(parent.opacityProperty(), 1);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
			Timeline timeLine = new Timeline();
			timeLine.getKeyFrames().add(keyFrame);
			timeLine.play();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void startServer() {
		// 스레드풀 생성
		executorService = Executors.newFixedThreadPool(100);

		// 서버 소켓 생성
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("192.168.3.11", 50001));

		} catch (IOException ex) {
			stopServer();
			return;
		}

		// 연결 수락 작업 정의
		Runnable acceptTask = () -> {
			System.out.println("서버 시작..");
			while (true) {
				try {
					// 수락 코드
					Socket socket = serverSocket.accept();
					// 통신용 External 객체 생성
					External external = new External(socket);
					// 총 Client 수 출력
				} catch (IOException ex) {
					stopServer();
					break;
				}
			}
		};
		// 스레드풀의 작업큐에 작업 넣기
		executorService.submit(acceptTask);
	}

	public void stopServer() {
		try {
			// how1
//			for (Client client : connections) {
//				client.socket.close();
//			}
//			connections.clear();

			// how2
//			Iterator<Client> iterator = connections.iterator();
//			while (iterator.hasNext()) {
//				Client client = iterator.next();
//				client.socket.close();
//				iterator.remove();
//			}
			if (executorService != null) {
				executorService.shutdownNow();
				serverSocket.close();
			}
		} catch (IOException ex) {
		}
		System.out.println("서버 종료");
	}

	class External {

		Socket socket;

		public External(Socket socket) {
			this.socket = socket;
			receive();
		}

		public void receive() {
			// 받기 작업 정의
			Thread thread = new Thread() {
				@Override
				public void run() {
					try {
						while (true) {
							InputStream inputStream = socket.getInputStream();
							byte[] byteArr = new byte[1000];
							int readBytes = inputStream.read(byteArr);
							if (readBytes == -1) {
								throw new Exception();
							}
							String str = new String(byteArr, 0, readBytes);
							if (str.contains(":")) {
								strStringToken(str);
							} else if (str.contains("Box")) {
								boxArrive(true);
							} else if (str.contains("CallExternalBuilding")) {
								showVideoScreen("bfront");
							} else if (str.contains("CallExternalDoor")) {
								showVideoScreen("dfront");
							} else if (str.contains("CallExternalParkingLot")) {
								showVideoScreen("pfront");
							} else if (str.contains(";")) {
								updateNotice(str);
								NoticeAdd(true);
							}
						}
					} catch (Exception e) {
						System.out.println("readBytes == -1 예외발생으로 인한 받기 작업 스레드 종료");
					}
				}
			};
			thread.start();
		}
	}

	private void ExternalCallDialog(String Dong, String Ho) {
		try {
			Stage dialog = new Stage(StageStyle.TRANSPARENT);
			Parent parent = FXMLLoader.load(getClass().getResource("call/ExternalCallDialog.fxml"));

			Media callMedia = new Media(getClass().getResource(callingBell).toString());
			MediaPlayer callMediaPlayer = new MediaPlayer(callMedia);
			callMediaPlayer.stop();
			callMediaPlayer.play();
			callMediaPlayer.setVolume(bellSound / 100);

			Button dialogBtnClose = (Button) parent.lookup("#dialogBtnClose");
			Label lblDong = (Label) parent.lookup("#lblDong");
			Label lblHo = (Label) parent.lookup("#lblHo");
			Label lblCalling = (Label) parent.lookup("#lblCalling");
			Button dialogBtnReceive = (Button) parent.lookup("#dialogBtnReceive");
			Button dialogBtnClose2 = (Button) parent.lookup("#dialogBtnClose2");

			lblDong.setText(Dong);
			lblHo.setText(Ho);

			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					while (true) {
						Platform.runLater(() -> {
							lblCalling.setOpacity(0);
						});
						Thread.sleep(400);
						Platform.runLater(() -> {
							lblCalling.setOpacity(1);
						});
						Thread.sleep(400);
						if (isCancelled()) {
							break;
						}
					}
					return null;
				}

				@Override
				protected void cancelled() {
					dialog.close();
				}
			};

			Thread thread = new Thread(task);
			thread.setDaemon(true);
			thread.start();

			dialogBtnReceive.setOnAction(e -> {
				callMediaPlayer.stop();
				callMediaPlayer.dispose();
				lblCalling.setText("통화중");

				Timeline tl1 = new Timeline();
				KeyValue keyValue1 = new KeyValue(dialogBtnClose.translateXProperty(), -52);
				KeyValue keyValue2 = new KeyValue(dialogBtnReceive.translateXProperty(), 51);
				KeyValue keyValue3 = new KeyValue(dialogBtnReceive.opacityProperty(), 0);
				KeyValue keyValue4 = new KeyValue(dialogBtnClose.opacityProperty(), 0);
				KeyValue keyValue5 = new KeyValue(dialogBtnClose2.translateYProperty(), 60);

				KeyFrame kf1 = new KeyFrame(Duration.millis(400), keyValue1, keyValue2, keyValue3, keyValue4, keyValue5);
				tl1.getKeyFrames().add(kf1);
				tl1.play();

				tl1.setOnFinished(e2 -> {
					dialogBtnClose2.setOpacity(1);
				});

			});

			dialogBtnClose.setOnAction(e -> {
				callMediaPlayer.stop();
				callMediaPlayer.dispose();

				task.cancel();
				System.gc();
			});

			dialogBtnClose2.setOnAction(e -> {
				dialog.close();
				System.gc();
			});

			Scene scene = new Scene(parent);
			scene.setFill(Color.TRANSPARENT);
			dialog.setScene(scene);
			dialog.initOwner(lblTime.getScene().getWindow());
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.setX(lblTime.getScene().getWindow().getX() + lblTime.getScene().getWindow().getWidth() / 4);
			dialog.setY(lblTime.getScene().getWindow().getY() + lblTime.getScene().getWindow().getHeight() / 3.6);
			dialog.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private void strStringToken(String str) {
		StringTokenizer st = new StringTokenizer(str, ":");
		String Dong = st.nextToken();
		String Ho = st.nextToken();
		Platform.runLater(() -> {
			ExternalCallDialog(Dong, Ho);
		});
	}

	private void showVideoScreen(String str) throws IOException {
		belllMediaPlayer.stop();
		belllMediaPlayer.play();
		changeVideoScreenState(str);

		Platform.runLater(() -> {
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("video/Video.fxml"));
				stackPane.getChildren().add(parent);

				parent.setOpacity(0);

				KeyValue keyValue = new KeyValue(parent.opacityProperty(), 1);
				KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
				Timeline timeLine = new Timeline();
				timeLine.getKeyFrames().add(keyFrame);
				timeLine.play();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		});
	}

	private void changeVideoScreenState(String str) {
		VideoScreenState = str;
	}

	private void updateNotice(String str) {
		StringTokenizer st = new StringTokenizer(str, ";");
		String date = st.nextToken();
		String noticePlace = st.nextToken();
		String title = st.nextToken();
		String Contents = st.nextToken();

		list.add(new Data(date, noticePlace, title, Contents));
	}

}
