package smarthomepanel.control;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TemperatureController implements Initializable {
    @FXML
    private Button btnUp;
    @FXML
    private Button btnDown;
    @FXML
    private Label lbTitle;
    @FXML
    private Label lbTempNow;
    @FXML
    private Label lbTempSetting;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;

    public static int tempKey = 1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbTitle.setText(HeatingController.tempTitle);
        lbTempSetting.setText(String.valueOf(HeatingController.tempNew));
        lbTempNow.setText(lbTempSetting.getText());
        btnUp.setOnAction(e -> handleBtnUp(e));
        btnDown.setOnAction(e -> handleBtnDown(e));
        btnConfirm.setOnAction(e -> handleBtnConfirm(e));
        btnCancel.setOnAction(e -> handleBtnCancel(e));

    }

    private void handleBtnUp(ActionEvent e) {
        //버튼 숫자 up
        lbTempSetting.setText(String.valueOf(Integer.parseInt(lbTempSetting.getText()) + 1));
    }

    private void handleBtnDown(ActionEvent e) {
        //버튼 숫자 down
        lbTempSetting.setText(String.valueOf(Integer.parseInt(lbTempSetting.getText()) - 1));
    }

    private void handleBtnConfirm(ActionEvent e) {
        //각 방의 설정온도 저장
        
        if (tempKey == 1) {
            HeatingController.tempMain = Integer.parseInt(lbTempSetting.getText());
        } else if (tempKey == 2) {
            HeatingController.tempBig = Integer.parseInt(lbTempSetting.getText());
        } else if (tempKey == 3) {
            HeatingController.tempRoom1 = Integer.parseInt(lbTempSetting.getText());
        } else if (tempKey == 4) {
            HeatingController.tempRoom2 = Integer.parseInt(lbTempSetting.getText());
        } else if (tempKey == 5) {
            HeatingController.tempRoom3 = Integer.parseInt(lbTempSetting.getText());
        }
        Stage dialog = (Stage) btnConfirm.getScene().getWindow();
        dialog.close();
    }

    private void handleBtnCancel(ActionEvent e) {
        Stage dialog = (Stage) btnCancel.getScene().getWindow();
        dialog.close();
    }

}
