package smarthomepanel.notify;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import smarthomepanel.MainController;

public class NotifyController implements Initializable {

	@FXML
	private AnchorPane notifyControl;
	@FXML
	private TableView<Data> tableView;
	@FXML
	private Button btnBack;
	@FXML
	private Label lblTime;

	private boolean stop = false;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnBack.setOnAction(e -> handleBtnBack());
		tableView.setOnMouseClicked(e -> handleSelectTableView());

		Thread thread = new Thread() {
			@Override
			public void run() {
				SimpleDateFormat sdf = new SimpleDateFormat("a hh:mm");
				while (!stop) {
					System.out.println("콜 스레드 동작");

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

		TableColumn tc0 = tableView.getColumns().get(0);
		TableColumn tc1 = tableView.getColumns().get(1);
		TableColumn tc2 = tableView.getColumns().get(2);

		tc0.setCellValueFactory(new PropertyValueFactory<Data, String>("date"));
		tc1.setCellValueFactory(new PropertyValueFactory<Data, String>("noticePlace"));
		tc2.setCellValueFactory(new PropertyValueFactory<Data, String>("title"));

		tableView.setItems(MainController.list);

	}

	private void handleBtnBack() {
		StackPane rootPane = (StackPane) btnBack.getScene().getRoot();
		notifyControl.setOpacity(1);
		KeyValue keyValue = new KeyValue(notifyControl.opacityProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(500), e -> rootPane.getChildren().remove(notifyControl), keyValue);
		Timeline timeLine = new Timeline();
		timeLine.getKeyFrames().add(keyFrame);
		timeLine.play();
		MainController.isNoticeAdded = false;
		stop = true;
		System.gc();
	}

	private void handleSelectTableView() {
		try {
			Data data = tableView.getSelectionModel().getSelectedItem();
			String date = data.getDate();
			String noticePlace = data.getNoticePlace();
			String title = data.getTitle();
			String contents = data.getContents();
			try {
				Stage dialog = new Stage(StageStyle.TRANSPARENT);
				Parent parent = FXMLLoader.load(getClass().getResource("NotifyDetail.fxml"));

				Label lblDate = (Label) parent.lookup("#lblDate");
				Label lblTitle = (Label) parent.lookup("#lblTitle");
				Label lblNoticePlace = (Label) parent.lookup("#lblNoticePlace");
				Label lblContents = (Label) parent.lookup("#lblContents");

				lblDate.setText(date);
				lblTitle.setText(noticePlace);
				lblNoticePlace.setText(title);
				lblContents.setText(contents);
				lblContents.wrapTextProperty();
				Button dialogBtnClose = (Button) parent.lookup("#dialogBtnClose");

				dialogBtnClose.setOnAction(e -> {
					dialog.close();
					System.gc();
				});

				Scene scene = new Scene(parent);
				scene.setFill(Color.TRANSPARENT);
				dialog.setScene(scene);
				dialog.initOwner(lblTime.getScene().getWindow());
				dialog.initModality(Modality.WINDOW_MODAL);
				dialog.setX(lblTime.getScene().getWindow().getX() + lblTime.getScene().getWindow().getWidth() / 8);
				dialog.setY(lblTime.getScene().getWindow().getY() + lblTime.getScene().getWindow().getHeight() / 3);
				dialog.show();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("선택된 공지사항 데이터가 없음");

		}

	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("-----------------------------------가비지 컬렉터-------------------------------------------");
	}

}
