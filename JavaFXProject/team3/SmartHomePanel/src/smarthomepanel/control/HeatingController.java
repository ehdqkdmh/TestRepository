package smarthomepanel.control;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class HeatingController implements Initializable {

    @FXML
    private Button btnOnOffMain;
    @FXML
    private Button btnOnOffBigRoom;
    @FXML
    private Button btnOnOffRoom1;
    @FXML
    private Button btnOnOffRoom2;
    @FXML
    private Button btnOnOffRoom3;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnAllOn;
    @FXML
    private Button btnAllOff;
    @FXML
    private ImageView imageMain;
    @FXML
    private ImageView imageBigRoom;
    @FXML
    private ImageView imageRoom1;
    @FXML
    private ImageView imageRoom2;
    @FXML
    private ImageView imageRoom3;
    @FXML
    private Button btnTemperMain;
    @FXML
    private Button btnTemperBigRoom;
    @FXML
    private Button btnTemperRoom1;
    @FXML
    private Button btnTemperRoom2;
    @FXML
    private Button btnTemperRoom3;
    @FXML
    private ImageView imgTempMain;
    @FXML
    private ImageView imgTempBig;
    @FXML
    private ImageView imgTempRoom1;
    @FXML
    private ImageView imgTempRoom2;
    @FXML
    private ImageView imgTempRoom3;

    public static String tempTitle = "";
    public static int tempMain = 20;
    public static int tempBig = 20;
    public static int tempRoom1 = 20;
    public static int tempRoom2 = 20;
    public static int tempRoom3 = 20;
    public static int tempNew = 20;
    @FXML
    private ImageView imgFireMain;
    @FXML
    private ImageView imgFireBig;
    @FXML
    private ImageView imgFireRoom1;
    @FXML
    private ImageView imgFireRoom2;
    @FXML
    private ImageView imgFireRoom3;
    @FXML
    private AnchorPane heatingControl;
    Image switch_off = new Image(getClass().getResource("images/icons/control/off.png").toString());
    Image thermometer_off = new Image(getClass().getResource("images/icons/control/thermometer_off.png").toString());
    Image fire_off = new Image(getClass().getResource("images/icons/control/fire_off.png").toString());
    Image switch_on = new Image(getClass().getResource("images/icons/control/on.png").toString());
    Image thermometer_on = new Image(getClass().getResource("images/icons/control/thermometer_on.png").toString());
    Image fire_on = new Image(getClass().getResource("images/icons/control/fire_on.png").toString());
    public static String onOffMain = "OFF";
    public static String onOffBig = "OFF";
    public static String onOffRoom1 = "OFF";
    public static String onOffRoom2 = "OFF";
    public static String onOffRoom3 = "OFF";
    @FXML
    private Label temperMain;
    @FXML
    private Label temperBig;
    @FXML
    private Label temperRoom1;
    @FXML
    private Label temperRoom2;
    @FXML
    private Label temperKitchen;
    private boolean stop=false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 변경된 온도를 설정하기 위한 스레드
      Thread thread = new Thread() {
         @Override
         public void run() {
            while (!stop) {
              // System.out.println("변경된 온도");
               Platform.runLater(() -> {
                  temperMain.setText(String.valueOf(tempMain));
                  temperBig.setText(String.valueOf(tempBig));
                  temperRoom1.setText(String.valueOf(tempRoom1));
                  temperRoom2.setText(String.valueOf(tempRoom2));
                  temperKitchen.setText(String.valueOf(tempRoom3));
               });
               try {
                  Thread.sleep(1000);
               } catch (InterruptedException ex) {
               }
            }
            stop = false;
           // System.out.println("스레드 종료");
         }
      };
      thread.start();
        //난방조절 화면에서 다른화면으로 나갔다와도 전에 저장했던 정보들을 유지하도록 설정
        savedInfo();

        //버튼처리
        btnOnOffMain.setOnAction(e -> handleBtnOnOffMain(e));
        btnOnOffBigRoom.setOnAction(e -> handleBtnOnOffBigRoom(e));
        btnOnOffRoom1.setOnAction(e -> handleBtnOnOffRoom1(e));
        btnOnOffRoom2.setOnAction(e -> handleBtnOnOffRoom2(e));
        btnOnOffRoom3.setOnAction(e -> handleBtnOnOffRoom3(e));
        btnBack.setOnAction(e -> handleBtnBack(e));
        btnHome.setOnAction(e -> handleBtnHome(e));
        btnAllOn.setOnAction(e -> handleBtnAllOn(e));
        btnAllOff.setOnAction(e -> handleBtnAllOff(e));
        btnTemperMain.setOnAction(e -> handleBtnTemperMain(e));
        btnTemperBigRoom.setOnAction(e -> handleBtnTemperBig(e));
        btnTemperRoom1.setOnAction(e -> handleBtnTemperRoom1(e));
        btnTemperRoom2.setOnAction(e -> handleBtnTemperRoom2(e));
        btnTemperRoom3.setOnAction(e -> handleBtnTemperRoom3(e));

    }

    private void handleBtnOnOffMain(ActionEvent e) {
        //ON일때 버튼 누르면 OFF로 변환
        if ((btnOnOffMain.getText()).equals("ON")) {
            mainSwitchOff();
        } //OFF일때 버튼 누르면 ON으로 변환
        else {
            mainSwitchOn();
        }
    }

    private void handleBtnOnOffBigRoom(ActionEvent e) {
        if ((btnOnOffBigRoom.getText()).equals("ON")) {
            bigSwitchOff();
        } else {
            bigSwitchOn();
        }
    }

    private void handleBtnOnOffRoom1(ActionEvent e) {
        if ((btnOnOffRoom1.getText()).equals("ON")) {
            room1SwitchOff();
        } else {
            room1SwitchOn();
        }
    }

    private void handleBtnOnOffRoom2(ActionEvent e) {
        if ((btnOnOffRoom2.getText()).equals("ON")) {
            room2SwitchOff();
        } else {
            room2SwitchOn();
        }
    }

    private void handleBtnOnOffRoom3(ActionEvent e) {
        if ((btnOnOffRoom3.getText()).equals("ON")) {
            room3SwitchOff();
        } else {
            room3SwitchOn();
        }
    }

    private void handleBtnAllOn(ActionEvent e) {
        //전체 OFF
        if ((btnOnOffMain.getText()).equals("OFF")) {
            handleBtnOnOffMain(e);
        }

        if ((btnOnOffBigRoom.getText()).equals("OFF")) {
            handleBtnOnOffBigRoom(e);
        }
        if ((btnOnOffRoom1.getText()).equals("OFF")) {
            handleBtnOnOffRoom1(e);
        }
        if ((btnOnOffRoom2.getText()).equals("OFF")) {
            handleBtnOnOffRoom2(e);
        }
        if ((btnOnOffRoom3.getText()).equals("OFF")) {
            handleBtnOnOffRoom3(e);
        }
        try {
            //popup 알림
            showNotification("알림1", "전체 난방 ON");
        } catch (IOException ex) {
        }
    }

    private void handleBtnAllOff(ActionEvent e) {
        //전체 OFF
        if ((btnOnOffMain.getText()).equals("ON")) {
            handleBtnOnOffMain(e);
        }
        if ((btnOnOffBigRoom.getText()).equals("ON")) {
            handleBtnOnOffBigRoom(e);
        }
        if ((btnOnOffRoom1.getText()).equals("ON")) {
            handleBtnOnOffRoom1(e);
        }
        if ((btnOnOffRoom2.getText()).equals("ON")) {
            handleBtnOnOffRoom2(e);
        }
        if ((btnOnOffRoom3.getText()).equals("ON")) {
            handleBtnOnOffRoom3(e);
        }
        try {
            //popup 알림
            showNotification("알림2", "전체 난방 OFF");
        } catch (IOException ex) {
        }

    }

    private void handleBtnBack(ActionEvent e) {
        StackPane rootPane = (StackPane) btnHome.getScene().getRoot();
        heatingControl.setOpacity(1);
        KeyValue keyValue = new KeyValue(heatingControl.opacityProperty(), 0);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500),
                event -> rootPane.getChildren().remove(heatingControl),
                keyValue
        );
        Timeline timeLine = new Timeline();
        timeLine.getKeyFrames().add(keyFrame);
        timeLine.play();
        stop=true;
    }

    private void handleBtnHome(ActionEvent e) {
        StackPane rootPane = (StackPane) btnHome.getScene().getRoot();
        //바로 홈으로 가기위해 중간에 Control.fxml 화면 미리 삭제
        rootPane.getChildren().remove(1);
        heatingControl.setOpacity(1);
        KeyValue keyValue = new KeyValue(heatingControl.opacityProperty(), 0);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500),
                event -> rootPane.getChildren().remove(heatingControl),
                keyValue
        );
        Timeline timeLine = new Timeline();
        timeLine.getKeyFrames().add(keyFrame);
        timeLine.play();
        stop=true;
    }

    private void handleBtnTemperMain(ActionEvent e) {
        try {
            //ON 상태일때만 온도설정 활성화
            if ((btnOnOffMain.getText()).equals("ON")) {
                //방 정보를 문자열과 키값으로 넘겨줌    
                tempTitle = "거실";
                tempNew = tempMain;
                TemperatureController.tempKey = 1;
                showDialog();
            } else {
                showNotification("경고", "난방을 켠 후 온도를 조절해주세요");
            }

        } catch (IOException ex) {
        }

    }

    private void handleBtnTemperBig(ActionEvent e) {

        try {
            if ((btnOnOffBigRoom.getText()).equals("ON")) {
                tempTitle = "안방";
                tempNew = tempBig;
                TemperatureController.tempKey = 2;
                showDialog();
            } else {
                showNotification("경고", "난방을 켠 후 온도를 조절해주세요");
            }
        } catch (IOException ex) {
        }

    }

    private void handleBtnTemperRoom1(ActionEvent e) {

        try {
            if ((btnOnOffRoom1.getText()).equals("ON")) {
                tempTitle = "침실1";
                tempNew = tempRoom1;
                TemperatureController.tempKey = 3;
                showDialog();
            } else {
                showNotification("경고", "난방을 켠 후 온도를 조절해주세요");
            }
        } catch (IOException ex) {
        }

    }

    private void handleBtnTemperRoom2(ActionEvent e) {

        try {
            if ((btnOnOffRoom2.getText()).equals("ON")) {
                tempTitle = "침실2";
                tempNew = tempRoom2;
                TemperatureController.tempKey = 4;
                showDialog();
            } else {
                showNotification("경고", "난방을 켠 후 온도를 조절해주세요");
            }
        } catch (IOException ex) {
        }

    }

    private void handleBtnTemperRoom3(ActionEvent e) {
        try {
            if ((btnOnOffRoom3.getText()).equals("ON")) {
                tempTitle = "주방";
                tempNew = tempRoom3;
                TemperatureController.tempKey = 5;
                showDialog();
            } else {
                showNotification("경고", "난방을 켠 후 온도를 조절해주세요");
            }
        } catch (IOException ex) {
        }

    }

    private void showNotification(String type, String message) throws IOException {
        Popup popup = new Popup();
        HBox hbox = (HBox) FXMLLoader.load(getClass().getResource("popup.fxml"));
        ImageView imgMessage = (ImageView) hbox.lookup("#imgMessage");
        Label lbMessage = (Label) hbox.lookup("#lbMessage");
        if (type.equals("알림1")) {
            imgMessage.setImage(new Image(getClass().getResource("images/icons/control/fire_on.png").toString()));
        } else if (type.equals("알림2")) {
            imgMessage.setImage(new Image(getClass().getResource("images/icons/control/fire_off.png").toString()));
        }         
        else if (type.equals("경고")) {
            imgMessage.setImage(new Image(getClass().getResource("images/icons/control/thermometer_off.png").toString()));
        }
        lbMessage.setText(message);
        popup.getContent().add(hbox);
        popup.setAutoHide(true);
        Stage primaryStage = (Stage) btnTemperMain.getScene().getWindow();
        popup.show(primaryStage);
    }

    private void showDialog() {

        try {
            Stage dialog = new Stage(StageStyle.UTILITY);
            dialog.initModality(Modality.WINDOW_MODAL);
            Stage primaryStage = (Stage) btnTemperMain.getScene().getWindow();
            dialog.initOwner(primaryStage);
            dialog.setTitle("온도설정");
            Parent parent = FXMLLoader.load(getClass().getResource("Temperature.fxml"));
            Scene scene = new Scene(parent);
            dialog.setScene(scene);
            dialog.setResizable(false);
            dialog.show();
        } catch (IOException ex) {
        }
    }

    private void mainSwitchOff() {
        btnOnOffMain.setText("OFF");
        onOffMain = "OFF";
        imageMain.setImage(switch_off);
        imgTempMain.setImage(thermometer_off);
        imgFireMain.setImage(fire_off);
    }

    private void mainSwitchOn() {
        btnOnOffMain.setText("ON");
        onOffMain = "ON";
        imageMain.setImage(switch_on);
        imgTempMain.setImage(thermometer_on);
        imgFireMain.setImage(fire_on);
    }

    private void bigSwitchOff() {
        btnOnOffBigRoom.setText("OFF");
        onOffBig = "OFF";
        imageBigRoom.setImage(switch_off);
        imgTempBig.setImage(thermometer_off);
        imgFireBig.setImage(fire_off);
    }

    private void bigSwitchOn() {
        btnOnOffBigRoom.setText("ON");
        onOffBig = "ON";
        imageBigRoom.setImage(switch_on);
        imgTempBig.setImage(thermometer_on);
        imgFireBig.setImage(fire_on);
    }

    private void room1SwitchOff() {
        btnOnOffRoom1.setText("OFF");
        onOffRoom1 = "OFF";
        imageRoom1.setImage(switch_off);
        imgTempRoom1.setImage(thermometer_off);
        imgFireRoom1.setImage(fire_off);
    }

    private void room1SwitchOn() {
        btnOnOffRoom1.setText("ON");
        onOffRoom1 = "ON";
        imageRoom1.setImage(switch_on);
        imgTempRoom1.setImage(thermometer_on);
        imgFireRoom1.setImage(fire_on);
    }

    private void room2SwitchOff() {
        btnOnOffRoom2.setText("OFF");
        onOffRoom2 = "OFF";
        imageRoom2.setImage(switch_off);
        imgTempRoom2.setImage(thermometer_off);
        imgFireRoom2.setImage(fire_off);
    }

    private void room2SwitchOn() {
        btnOnOffRoom2.setText("ON");
        onOffRoom2 = "ON";
        imageRoom2.setImage(switch_on);
        imgTempRoom2.setImage(thermometer_on);
        imgFireRoom2.setImage(fire_on);
    }

    private void room3SwitchOff() {
        btnOnOffRoom3.setText("OFF");
        onOffRoom3 = "OFF";
        imageRoom3.setImage(switch_off);
        imgTempRoom3.setImage(thermometer_off);
        imgFireRoom3.setImage(fire_off);
    }

    private void room3SwitchOn() {
        btnOnOffRoom3.setText("ON");
        onOffRoom3 = "ON";
        imageRoom3.setImage(switch_on);
        imgTempRoom3.setImage(thermometer_on);
        imgFireRoom3.setImage(fire_on);
    }

    //다른화면으로 나갔다 왔을 때 전에 했던 설정을 불러온다
    private void savedInfo() {
        
        if (onOffMain.equals("OFF")) {
            mainSwitchOff();
        }
        else if (onOffMain.equals("ON")) {
            mainSwitchOn();
        }
        if (onOffBig.equals("OFF")) {
            bigSwitchOff();
        }
        else if (onOffBig.equals("ON")) {
            bigSwitchOn();
        }
        if (onOffRoom1.equals("OFF")) {
            room1SwitchOff();
        }
        else if (onOffRoom1.equals("ON")) {
            room1SwitchOn();
        }
        if (onOffRoom2.equals("OFF")) {
            room2SwitchOff();
        }
        else if (onOffRoom2.equals("ON")) {
            room2SwitchOn();
        }
        if (onOffRoom3.equals("OFF")) {
            room3SwitchOff();
        }
        else if (onOffRoom3.equals("ON")) {
            room3SwitchOn();
        }
    }
}
