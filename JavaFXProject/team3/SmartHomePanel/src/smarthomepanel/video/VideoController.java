package smarthomepanel.video;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import smarthomepanel.MainController;

public class VideoController implements Initializable {

	@FXML
	private StackPane videoControl;
	@FXML
	private ImageView imgScreen; // 화면 이미지뷰
	@FXML
	private Button btn_Back; // 뒤로 가기 버튼	
	@FXML
	private Label lblTime; // 시간 표시 라벨	
	@FXML
	private Button btn_BFront; // 동 앞 보기	버튼
	@FXML
	private ImageView imgBFront; // 동 앞 보기 버튼의 이미지뷰
	@FXML
	private Button btn_DFront; // 현관 앞 보기 버튼
	@FXML
	private ImageView imgDFront; // 현관 앞 보기 버튼의 이미지뷰
	@FXML
	private Button btn_PFront; // 주차장 앞 보기 버튼
	@FXML
	private ImageView imgPFront; // 주차장 앞 보기 버튼의 이미지뷰

	@FXML
	private Button btn_Call; // 전화 호출 버튼
	@FXML
	private ImageView imgCall; // 전화 호출 버튼의 이미지 뷰
	@FXML
	private Button btn_Door; // 문 열기 버튼
	@FXML
	private ImageView imgDoor; // 문 열기 버튼의 이미지뷰

	@FXML
	private Slider slider; // 음량 조절 슬라이더
	@FXML
	private Button btn_speaker; // 음량 키기 버튼
	@FXML
	private ImageView imgSpeaker; // 음량 키기 버튼의 이미지 뷰

	private boolean stop; // 스레드 플래그
	private Task<Void> task;

	Image closeddoor = new Image(getClass().getResource("images/icons/suyang/closeddoor4.png").toString());
	Image openeddoor = new Image(getClass().getResource("images/icons/suyang/open-exit-door.png").toString());
	Image bfront = new Image(getClass().getResource("images/icons/suyang/buildingfront.JPG").toString());
	Image dfront = new Image(getClass().getResource("images/icons/suyang/doorfront1.JPG").toString());
	Image pfront = new Image(getClass().getResource("images/icons/suyang/parkingfront.PNG").toString());
	Image speaker = new Image(getClass().getResource("images/icons/suyang/speaker.png").toString());
	Image notspeaker = new Image(getClass().getResource("images/icons/suyang/not_speaker.png").toString());
	// Image call = new Image(getClass().getResource("images/icons/suyang/phone-call.png").toString());
	Image calloff = new Image(getClass().getResource("images/icons/suyang/phone-call.png").toString());
	Image callgreen = new Image(getClass().getResource("images/icons/suyang/phone-call(green).png").toString());
	FadeTransition ft1;
	FadeTransition ft2;

	@FXML
	private AnchorPane anchorpaneScreen;
	@FXML
	private Label lblCallState;
	@FXML
	private ImageView imgDoor2;

	//private boolean stop; // 스레드 플래그 
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		slider.setValue(50);

		ft1 = new FadeTransition(Duration.INDEFINITE.millis(700), imgCall);
		ft2 = new FadeTransition(Duration.INDEFINITE.millis(700), lblCallState);
		ft1.setFromValue(1.0);
		ft1.setToValue(0.1);
		ft1.setCycleCount(Timeline.INDEFINITE);
		ft1.setAutoReverse(true);

		ft2.setFromValue(1.0);
		ft2.setToValue(0.1);
		ft2.setCycleCount(Timeline.INDEFINITE);
		ft2.setAutoReverse(true);

		if (MainController.VideoScreenState != null) {
			changeScreenState(MainController.VideoScreenState);
		}

		// 버튼 이벤트 처리
		btn_BFront.setOnAction(e -> handleBtnBFront(e));
		btn_DFront.setOnAction(e -> handleBtnDFront(e));
		btn_PFront.setOnAction(e -> handleBtnPark(e));

		btn_Call.setOnAction(e -> handleBtnCall(e));
		btn_speaker.setOnAction(e -> handleBtnSpeaker(e));
		btn_Back.setOnAction(e -> handleBtnBack());

		//시간정보 설정
		Thread thread = new Thread() {
			@Override
			public void run() {
				SimpleDateFormat sdf = new SimpleDateFormat("a hh:mm");
				stop = false;
				while (!stop) {
					String strTime = sdf.format(new Date());
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

	}

	@FXML
	private void handleBtnOpen(ActionEvent e) throws IOException {

		//버튼이 눌리면
		btn_Door.setOpacity(0);
		imgDoor2.setOpacity(1);
		showDialogOpen("문이 열렸습니다.");

		//일정 시간 후
		Timeline tl = new Timeline();
		KeyValue kv1 = new KeyValue(btn_Door.opacityProperty(), 1);
		KeyValue kv2 = new KeyValue(imgDoor2.opacityProperty(), 0);
		KeyFrame kf = new KeyFrame(Duration.millis(10), kv1, kv2);
		tl.getKeyFrames().add(kf);
		tl.setDelay(Duration.millis(3000));
		tl.play();

	}

	@FXML
	private void handleBtnSpeaker(ActionEvent e) {
		Button btn_speaker = (Button) e.getSource();
		if ((btn_speaker.getId()).equals("btn_speaker")) {
			btn_speaker.setId("btn_speaker_Back");
			Platform.runLater(() -> imgSpeaker.setImage(notspeaker));

		} else if ((btn_speaker.getId()).equals("btn_speaker_Back")) {
			btn_speaker.setId("btn_speaker");
			Platform.runLater(() -> imgSpeaker.setImage(speaker));
		}
	}

	private void handleBtnBack() {
		StackPane rootPane = (StackPane) btn_Back.getScene().getRoot();
		videoControl.setOpacity(1);
		KeyValue keyValue = new KeyValue(videoControl.opacityProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(500), e -> rootPane.getChildren().remove(videoControl), keyValue);
		Timeline timeLine = new Timeline();
		timeLine.getKeyFrames().add(keyFrame);
		timeLine.play();
		stop = true;
		System.gc();
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("-----------------------------------가비지 컬렉터-------------------------------------------");
	}

	@FXML
	private void handleBtnBFront(ActionEvent e) {
		Button btn_BFront = (Button) e.getSource();
		if ((btn_BFront.getId()).equals("btn_BFront")) {
			imgScreen.setImage(bfront);
		}
	}

	@FXML
	private void handleBtnDFront(ActionEvent e) {
		Button btn_DFront = (Button) e.getSource();
		if ((btn_DFront.getId()).equals("btn_DFront")) {
			imgScreen.setImage(dfront);
		}
	}

	@FXML
	private void handleBtnPark(ActionEvent e) {
		Button btn_PFront = (Button) e.getSource();
		if ((btn_PFront.getId()).equals("btn_PFront")) {
			imgScreen.setImage(pfront);
		}
	}

	private void handleBtnCall(ActionEvent e) {

		Thread changeImageThread = new Thread() {
			@Override
			public void run() {
				if ((btn_Call.getId()).equals("btn_Call")) {
					System.out.println("1번");
					btn_Call.setId("btn_Call_back");
					Platform.runLater(() -> {
						imgCall.setImage(callgreen);
						lblCallState.setText("ON");
						ft1.play();
						ft2.play();
					});
				} else if ((btn_Call.getId()).equals("btn_Call_back")) {
					System.out.println("2번");
					btn_Call.setId("btn_Call");
					Platform.runLater(() -> {
						ft1.stop();
						ft2.stop();
						System.out.println("btn_call_back");
						lblCallState.setText("");
						imgCall.setImage(calloff);
					});
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
				}
			}
		};
		changeImageThread.start();

//		Thread changeLabelThread = new Thread() {
//			@Override
//			public void run() {
//				if ((btn_Call.getId()).equals("btn_Call")) {
//					System.out.println("1번");
//					btn_Call.setId("btn_Call_back");
//					Platform.runLater(() -> {
//						imgCall.setImage(callgreen);
//						lblCallState.setText("ON");
//						ft2.play();
//					});
//				} else if ((btn_Call.getId()).equals("btn_Call_back")) {
//					System.out.println("2번");
//					btn_Call.setId("btn_Call");
//					Platform.runLater(() -> {
//						ft2.stop();
//						System.out.println("btn_call_back");
//						lblCallState.setText("");
//						imgCall.setImage(calloff);
//					});
//				}
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException ex) {
//				}
//			}
//		};
//		changeLabelThread.start();
	}

	private void changeScreenState(String str) {
		if (str.equals("bfront")) {
			Platform.runLater(() -> imgScreen.setImage(dfront));
		} else if (str.equals("dfront")) {
			Platform.runLater(() -> imgScreen.setImage(bfront));
		} else if (str.equals("pfront")) {
			Platform.runLater(() -> imgScreen.setImage(pfront));
		}
	}

	private void showDialogOpen(String str) throws IOException {

		Popup popup = new Popup();
		HBox hbox = (HBox) FXMLLoader.load(getClass().getResource("doorPopupOpen.fxml"));
		ImageView iv = (ImageView) hbox.lookup("#imgOpenMsg");
		Label label = (Label) hbox.lookup("#lblOpenMsg");

		label.setText(str);
		popup.getContent().add(hbox);
		popup.setAutoHide(true);
		//popup.setHideOnEscape(true);
		Stage primaryStage = (Stage) btn_Door.getScene().getWindow();

		Thread t = new Thread() {
			@Override
			public void run() {
				Platform.runLater(() -> {
					popup.show(primaryStage);
				});

				try {
					Thread.sleep(3000);
				} catch (InterruptedException ex) {
				} finally {
					Platform.runLater(() -> {
						popup.hide();
					});
				}
			}
		};
		t.setDaemon(true);
		t.start();
	}

}
