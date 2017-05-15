package smarthomepanel.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class LightController implements Initializable {

    @FXML
    private ImageView imgRoom1;
    @FXML
    private ImageView imgRoom2;
    @FXML
    private ImageView imgMain;
    @FXML
    private ImageView imgBig;
    @FXML
    private ImageView imgKitchen;
    @FXML
    private Button btnMain;
    @FXML
    private Button btnBig;
    @FXML
    private Button btnKitchen;
    @FXML
    private Button btnRoom1;
    @FXML
    private Button btnRoom2;
    @FXML
    private Button btnAllOff;
    @FXML
    private Button btnAllOn;
    @FXML
    private Button btnBack;
    @FXML
    private AnchorPane lightControl;
    @FXML
    private Button btnHome;
    Image switch_off = new Image(getClass().getResource("images/icons/control/light_off2.png").toString());
    Image switch_on = new Image(getClass().getResource("images/icons/control/light_on.png").toString());
    public static String Main = "OFF";
    public static String Big = "OFF";
    public static String Room1 = "OFF";
    public static String Room2 = "OFF";
    public static String Kitchen = "OFF";
    @FXML
    private Region regionKitchen;
    @FXML
    private Region regionBig;
    @FXML
    private Region regionMain;
    @FXML
    private Region regionRoom2;
    @FXML
    private Region regionRoom1;
    @FXML
    private Region regionBig2;
    @FXML
    private Region regionMain2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setting();
        btnMain.setOnAction(e -> handleBtnMain(e));
        btnBig.setOnAction(e -> handleBtnBig(e));
        btnKitchen.setOnAction(e -> handleBtnKitchen(e));
        btnRoom1.setOnAction(e -> handleBtnRoom1(e));
        btnRoom2.setOnAction(e -> handleBtnRoom2(e));
        btnAllOff.setOnAction(e -> handleBtnAllOff(e));
        btnAllOn.setOnAction(e -> handleBtnAllOn(e));
        btnBack.setOnAction(e -> handleBtnBack(e));
        btnHome.setOnAction(e -> handleBtnHome(e));

    }

    private void setting() {

        if (Main.equals("OFF")) {
            imgMain.setImage(switch_off);
            btnSetting(imgMain, regionMain, btnMain);
        } else {
            imgMain.setImage(switch_on);
            btnSetting(imgMain, regionMain, btnMain);
        }
        if (Big.equals("OFF")) {
            imgBig.setImage(switch_off);
            btnSetting(imgBig, regionBig, btnBig);
        } else {
            imgBig.setImage(switch_on);
            btnSetting(imgBig, regionBig, btnBig);
        }
        if (Room1.equals("OFF")) {
            imgRoom1.setImage(switch_off);
            btnSetting(imgRoom1, regionRoom1, btnRoom1);
        } else {
            imgRoom1.setImage(switch_on);
            btnSetting(imgRoom1, regionRoom1, btnRoom1);
        }
        if (Room2.equals("OFF")) {
            imgRoom2.setImage(switch_off);
            btnSetting(imgRoom2, regionRoom2, btnRoom2);
        } else {
            imgRoom2.setImage(switch_on);
            btnSetting(imgRoom2, regionRoom2, btnRoom2);
        }
        if (Kitchen.equals("OFF")) {
            imgKitchen.setImage(switch_off);
            btnSetting(imgKitchen, regionKitchen, btnKitchen);
        } else {
            imgKitchen.setImage(switch_on);
            btnSetting(imgKitchen, regionKitchen, btnKitchen);
        }
    }

    private void handleBtnMain(ActionEvent e) {
        btnSetting(imgMain, regionMain, btnMain);
        if (Main.equals("ON")) {
            Main = "OFF";
        } else {
            Main = "ON";
        }
    }

    private void handleBtnBig(ActionEvent e) {
        btnSetting(imgBig, regionBig, btnBig);
        if (Big.equals("ON")) {
            Big = "OFF";
        } else {
            Big = "ON";
        }
    }

    private void handleBtnRoom1(ActionEvent e) {
        btnSetting(imgRoom1, regionRoom1, btnRoom1);
        if (Room1.equals("ON")) {
            Room1 = "OFF";
        } else {
            Room1 = "ON";
        }
    }

    private void handleBtnRoom2(ActionEvent e) {
        btnSetting(imgRoom2, regionRoom2, btnRoom2);
        if (Room2.equals("ON")) {
            Room2 = "OFF";
        } else {
            Room2 = "ON";
        }
    }

    private void handleBtnKitchen(ActionEvent e) {
        btnSetting(imgKitchen, regionKitchen, btnKitchen);
        if (Kitchen.equals("ON")) {
            Kitchen = "OFF";
        } else {
            Kitchen = "ON";
        }
    }

    private void handleBtnBack(ActionEvent e) {
        StackPane rootPane = (StackPane) btnBack.getScene().getRoot();
        lightControl.setOpacity(1);
        KeyValue keyValue = new KeyValue(lightControl.opacityProperty(), 0);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500),
                event -> rootPane.getChildren().remove(lightControl),
                keyValue
        );
        Timeline timeLine = new Timeline();
        timeLine.getKeyFrames().add(keyFrame);
        timeLine.play();
    }

    private void handleBtnHome(ActionEvent e) {
        StackPane rootPane = (StackPane) btnBack.getScene().getRoot();
        rootPane.getChildren().remove(1);
        lightControl.setOpacity(1);
        KeyValue keyValue = new KeyValue(lightControl.opacityProperty(), 0);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500),
                event -> rootPane.getChildren().remove(lightControl),
                keyValue
        );
        Timeline timeLine = new Timeline();
        timeLine.getKeyFrames().add(keyFrame);
        timeLine.play();
    }

    private void handleBtnAllOff(ActionEvent e) {
        if (imgMain.getImage() == switch_on) {
            handleBtnMain(e);
        }
        if (imgBig.getImage() == switch_on) {
            handleBtnBig(e);
        }
        if (imgRoom1.getImage() == switch_on) {
            handleBtnRoom1(e);
        }
        if (imgRoom2.getImage() == switch_on) {
            handleBtnRoom2(e);
        }
        if (imgKitchen.getImage() == switch_on) {
            handleBtnKitchen(e);
        }

    }

    private void handleBtnAllOn(ActionEvent e) {
        if (imgMain.getImage() == switch_off) {
            handleBtnMain(e);
        }
        if (imgBig.getImage() == switch_off) {
            handleBtnBig(e);
        }
        if (imgRoom1.getImage() == switch_off) {
            handleBtnRoom1(e);
        }
        if (imgRoom2.getImage() == switch_off) {
            handleBtnRoom2(e);
        }
        if (imgKitchen.getImage() == switch_off) {
            handleBtnKitchen(e);
        }
    }
    //ON, OFF에 따라 전구 이미지, 방 그림자, 글씨 색 설정

    private void btnSetting(ImageView imageView, Region region, Button button) {
        if (imageView.getImage() == switch_on) {
            imageView.setImage(switch_off);
            lightOff(region, button);
            if(region==regionMain){
                lightOff(regionMain2, button);
            }
            else if(region==regionBig){
                lightOff(regionBig2, button);
            }
        } else {
            imageView.setImage(switch_on);
            lightOn(region, button);
            if(region==regionMain){
                lightOn(regionMain2, button);
            }
            else if(region==regionBig){
                lightOn(regionBig2, button);
            }
        }
    }

    //방 어두워지고 환해지는거 설정
    private void lightOn(Region region, Button button) {
        region.setOpacity(0.7);
        KeyValue keyValue = new KeyValue(region.opacityProperty(), 0);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000),
                keyValue
        );
        Timeline timeLine = new Timeline();
        timeLine.getKeyFrames().add(keyFrame);
        timeLine.play();
        button.setTextFill(Color.BLACK);
    }

    private void lightOff(Region region, Button button) {
        region.setOpacity(0);
        KeyValue keyValue = new KeyValue(region.opacityProperty(), 0.7);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000),
                keyValue
        );
        Timeline timeLine = new Timeline();
        timeLine.getKeyFrames().add(keyFrame);
        timeLine.play();
        button.setTextFill(Color.WHITE);

    }
}
