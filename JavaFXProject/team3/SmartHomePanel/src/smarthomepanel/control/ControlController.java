package smarthomepanel.control;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ControlController implements Initializable {

	@FXML
	private Button btnHeating;
	@FXML
	private Button btnLight;
	@FXML
	private Button btnHome;
	@FXML
	private Button btnBack;
	@FXML
	private AnchorPane mainControl;
	@FXML
	private Label lblTime;
	private boolean stop;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		btnHeating.setOnAction(e -> handleBtnHeating(e));
		btnLight.setOnAction(e -> handleBtnLight(e));
		btnHome.setOnAction(e -> handleBtnHome(e));
		btnBack.setOnAction(e -> handleBtnBack(e));

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
		thread.setDaemon(true);
		thread.start();
	}

	private void handleBtnHeating(ActionEvent e) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("Heating.fxml"));
			StackPane rootPane = (StackPane) btnHome.getScene().getRoot();
			rootPane.getChildren().add(parent);
			parent.setOpacity(0);
			KeyValue keyValue = new KeyValue(parent.opacityProperty(), 1);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
			Timeline timeLine = new Timeline();
			timeLine.getKeyFrames().add(keyFrame);
			timeLine.play();

		} catch (IOException ex) {
		}
	}

	private void handleBtnLight(ActionEvent e) {
		try {
			System.out.println("버튼클릭");
			Parent parent = FXMLLoader.load(getClass().getResource("Light.fxml"));
			StackPane rootPane = (StackPane) btnHome.getScene().getRoot();
			rootPane.getChildren().add(parent);
			parent.setOpacity(0);
			KeyValue keyValue = new KeyValue(parent.opacityProperty(), 1);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(500), keyValue);
			Timeline timeLine = new Timeline();
			timeLine.getKeyFrames().add(keyFrame);
			timeLine.play();

		} catch (IOException ex) {
		}
	}

	private void handleBtnHome(ActionEvent e) {
		StackPane rootPane = (StackPane) btnHome.getScene().getRoot();
		mainControl.setOpacity(1);
		KeyValue keyValue = new KeyValue(mainControl.opacityProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(500),
				event -> rootPane.getChildren().remove(mainControl),
				keyValue
		);
		Timeline timeLine = new Timeline();
		timeLine.getKeyFrames().add(keyFrame);
		timeLine.play();
		stop = true;
	}

	private void handleBtnBack(ActionEvent e) {
		StackPane rootPane = (StackPane) btnHome.getScene().getRoot();
		mainControl.setOpacity(1);
		KeyValue keyValue = new KeyValue(mainControl.opacityProperty(), 0);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(500),
				event -> rootPane.getChildren().remove(mainControl),
				keyValue
		);
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

}
