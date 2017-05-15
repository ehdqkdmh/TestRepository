package smarthomepanel.setting;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import smarthomepanel.MainController;

public class SettingController implements Initializable {

    @FXML
    private AnchorPane settingControl;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblTime;
    private boolean stop;
    @FXML
    private ComboBox<String> SelectBell;
    @FXML
    private Slider SoundSlider;
    @FXML
    private ProgressIndicator SoundProgress;
    @FXML
    private Slider LightSlider;
    @FXML
    private ProgressIndicator LightProgress;
    private Media media = new Media(getClass().getResource("media/BIGBANG.mp3").toString());
    private MediaPlayer mediaPlayer = new MediaPlayer(media);
    ColorAdjust colorAdjust = new ColorAdjust();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBack.setOnAction(e -> handleBtnBack(e));

        //화면상단 시계부분
        Thread thread = new Thread() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("a hh:mm");
                while (!stop) {
                    System.out.println("시간스레드 작동");
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

        // 벨소리 선택 및 벨소리 크기 설정
        ObservableList<String> value = FXCollections.observableArrayList();
        value.add("BIGBANG");
        value.add("BLACKPINK");
        value.add("D");
        value.add("IU");
        SelectBell.setItems(value);
        SoundSlider.setValue(MainController.bellSound);
        SoundProgress.setProgress(MainController.bellSound / 100.0);

        LightSlider.setValue(MainController.screenBrightness);
        LightProgress.setProgress(MainController.screenBrightness / 100.0);

        SelectBell.setOnAction(e -> {
            int index = SelectBell.getSelectionModel().getSelectedIndex();
            if (index == 0) {
                mediaPlayer.stop();
                media = new Media(getClass().getResource("media/BIGBANG.mp3").toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                MainController.callingBell = "setting/media/BIGBANG.mp3";

            } else if (index == 1) {
                mediaPlayer.stop();
                media = new Media(getClass().getResource("media/BLACKPINK.mp3").toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                MainController.callingBell = "setting/media/BLACKPINK.mp3";

            } else if (index == 2) {
                mediaPlayer.stop();
                media = new Media(getClass().getResource("media/D.mp3").toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                MainController.callingBell = "setting/media/D.mp3";

            } else if (index == 3) {
                mediaPlayer.stop();
                media = new Media(getClass().getResource("media/IU.mp3").toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                MainController.callingBell = "setting/media/IU.mp3";
            }
            mediaPlayer.setVolume(MainController.bellSound / 100);
        });

        //소리조절 기능
        SoundSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(SoundSlider.getValue());
                SoundProgress.setProgress(SoundSlider.getValue() / 100.0);
                SoundProgress.progressProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        if(newValue.doubleValue() == 1){
                            SoundProgress.applyCss();
                            Text text = (Text) SoundProgress.lookup(".text.percentage");
                            text.setText("100%");
                        }
                    }
                });
                MainController.bellSound = (double) newValue;
                mediaPlayer.setVolume(MainController.bellSound / 100.0);

            }
        });

        //Screen 설정 화면밝기 부분 (창현형이 작성한 코드 그대로 가져옴)
        LightSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                LightProgress.setProgress(LightSlider.getValue() / 100);
                LightProgress.progressProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        if(newValue.doubleValue() == 1){
                            LightProgress.applyCss();
                            Text text = (Text) LightProgress.lookup(".text.percentage");
                            text.setText("100%");
                        }
                    }
                });
                MainController.screenBrightness = LightSlider.getValue();
            }
        });

    }

    private void handleBtnBack(ActionEvent e) {
        StackPane rootPane = (StackPane) btnBack.getScene().getRoot();
        settingControl.setOpacity(1);
        KeyValue keyValue = new KeyValue(settingControl.opacityProperty(), 0);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500), event -> rootPane.getChildren().remove(settingControl), keyValue);
        Timeline timeLine = new Timeline();
        timeLine.getKeyFrames().add(keyFrame);
        timeLine.play();
        stop = true;
        mediaPlayer.stop();
        mediaPlayer.dispose();
    }

}
