/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarthomepanel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ScreenSaverController implements Initializable {

	@FXML
	private AnchorPane ScreenSaver;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// 클릭시 화면 켜짐
		ScreenSaver.setOnMouseClicked(e -> {
			StackPane rootPane = (StackPane) ScreenSaver.getScene().getRoot();
			ScreenSaver.setOpacity(1);
			KeyValue keyValue = new KeyValue(ScreenSaver.opacityProperty(), 0);
			KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> rootPane.getChildren().remove(ScreenSaver), keyValue);
			Timeline timeLine = new Timeline();
			timeLine.getKeyFrames().add(keyFrame);
			timeLine.play();
//			MainController.waitTime = 0;
//			MainController.stop = false;

			System.out.println("SaverScreen 클릭 waitTime 초기화");
			System.gc();
		});
	}
}
