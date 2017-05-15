package smarthomepanel.externalsignal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExternalSignMain extends Application {

	public static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		// how1
		ExternalSignMain.primaryStage = primaryStage;

		// how2
		Parent parent = FXMLLoader.load(getClass().getResource("ExternalSign.fxml"));
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
		primaryStage.setTitle("외부 신호");

		primaryStage.setOnCloseRequest((e) -> {
			System.exit(0);
		});
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
