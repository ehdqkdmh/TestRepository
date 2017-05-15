package smarthomepanel.setting;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Setting extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("SettingMain.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CellFactory");

        primaryStage.setOnCloseRequest((event) -> {
            System.out.println("Close....안녕......");
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
