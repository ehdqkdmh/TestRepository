package smarthomepanel.call;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class CallController implements Initializable {

    @FXML
    private Button btnNum6;
    @FXML
    private Button btnNum2;
    @FXML
    private Button btnNum3;
    @FXML
    private Button btnNum4;
    @FXML
    private Button btnNum5;
    @FXML
    private Button btnNum7;
    @FXML
    private Button btnNum8;
    @FXML
    private Button btnNum9;
    @FXML
    private Button btnDong;
    @FXML
    private Button btnNum0;
    @FXML
    private Button btnHo;
    @FXML
    private Button btnNum1;
    @FXML
    private TextField txtDong;
    @FXML
    private TextField txtHo;
    @FXML
    private Button btnBackspace;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblTime;
    @FXML
    private AnchorPane callControl;

    private Task<Void> task;
    private boolean stop = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnDong.setOnAction(e -> handleBtnDong());
        btnHo.setOnAction(e -> handleBtnHo());

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
    }

    private void handleBtnDong() {
        btnBackspace.setOnAction(e -> {
            try {
                txtDong.deleteText(txtDong.getText().length() - 1, txtDong.getText().length());
            } catch (IndexOutOfBoundsException ex) {
            }
        });
        btnNum1.setOnAction(e -> {
            txtDong.appendText("1");
        });
        btnNum2.setOnAction(e -> {
            txtDong.appendText("2");
        });
        btnNum3.setOnAction(e -> {
            txtDong.appendText("3");
        });
        btnNum4.setOnAction(e -> {
            txtDong.appendText("4");
        });
        btnNum5.setOnAction(e -> {
            txtDong.appendText("5");
        });
        btnNum6.setOnAction(e -> {
            txtDong.appendText("6");
        });
        btnNum7.setOnAction(e -> {
            txtDong.appendText("7");
        });
        btnNum8.setOnAction(e -> {
            txtDong.appendText("8");
        });
        btnNum9.setOnAction(e -> {
            txtDong.appendText("9");
        });
        btnNum0.setOnAction(e -> {
            txtDong.appendText("0");
        });
    }

    private void handleBtnHo() {
        btnBackspace.setOnAction(e -> {
            try {
                txtHo.deleteText(txtHo.getText().length() - 1, txtHo.getText().length());
            } catch (IndexOutOfBoundsException ex) {
            }
        });
        btnNum1.setOnAction(e -> {
            txtHo.appendText("1");
        });
        btnNum2.setOnAction(e -> {
            txtHo.appendText("2");
        });
        btnNum3.setOnAction(e -> {
            txtHo.appendText("3");
        });
        btnNum4.setOnAction(e -> {
            txtHo.appendText("4");
        });
        btnNum5.setOnAction(e -> {
            txtHo.appendText("5");
        });
        btnNum6.setOnAction(e -> {
            txtHo.appendText("6");
        });
        btnNum7.setOnAction(e -> {
            txtHo.appendText("7");
        });
        btnNum8.setOnAction(e -> {
            txtHo.appendText("8");
        });
        btnNum9.setOnAction(e -> {
            txtHo.appendText("9");
        });
        btnNum0.setOnAction(e -> {
            txtHo.appendText("0");
        });
    }

    @FXML
    private void handleBtnCall(ActionEvent event) throws IOException {
        if (txtHo.getLength() != 0 && txtDong.getLength() != 0) {
            showCallDialog();
        } else {
            Stage dialog = new Stage(StageStyle.TRANSPARENT);
            Parent parent = FXMLLoader.load(getClass().getResource("CallWarningDialog.fxml"));

            Button dialogBtnClose = (Button) parent.lookup("#dialogBtnClose");
            ImageView imageWarning = (ImageView) parent.lookup("#imageWarning");

            task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (true) {
                        for (int i = 0; i < 2; i++) {
                            if (i == 0) {
                                Platform.runLater(() -> {
                                    imageWarning.setOpacity(0);
                                });
                            } else {
                                Platform.runLater(() -> {
                                    imageWarning.setOpacity(1);
                                });
                            }
                            try {
                                Thread.sleep(400);
                            } catch (InterruptedException ex) {
                            }
                        }
                    }
                }

                @Override
                protected void cancelled() {
                    dialog.close();
                }
            };

            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();

            dialogBtnClose.setOnAction(e -> {
                task.cancel();
            });

            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            dialog.setScene(scene);
            dialog.initOwner(btnBack.getScene().getWindow());
            dialog.initModality(Modality.WINDOW_MODAL);
            dialog.setX(btnBack.getScene().getWindow().getX() + btnBack.getScene().getWindow().getWidth() / 4);
            dialog.setY(btnBack.getScene().getWindow().getY() + btnBack.getScene().getWindow().getHeight() / 3.6);
            dialog.show();
        }

    }
    

    private void showCallDialog() throws IOException {
        Stage dialog = new Stage(StageStyle.TRANSPARENT);
        Parent parent = FXMLLoader.load(getClass().getResource("CallDialog.fxml"));

        Button dialogBtnClose = (Button) parent.lookup("#dialogBtnClose");
        Label lblDong = (Label) parent.lookup("#lblDong");
        Label lblHo = (Label) parent.lookup("#lblHo");
        Label lblCalling = (Label) parent.lookup("#lblCalling");

        lblDong.setText(txtDong.getText());
        lblHo.setText(txtHo.getText());
        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(() -> {
                        lblCalling.setText("호출중");
                    });
                    Thread.sleep(400);
                    for (int i = 0; i < 4; i++) {
                        Platform.runLater(() -> {
                            lblCalling.setText(lblCalling.getText() + ".");
                        });
                        Thread.sleep(400);
                    }
                    if (isCancelled()) {
                        break;
                    }
                }
                return null;
            }

            @Override
            protected void cancelled() {
                dialog.close();
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        dialogBtnClose.setOnAction(e -> {
            task.cancel();
        });

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        dialog.setScene(scene);
        dialog.initOwner(btnBack.getScene().getWindow());
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setX(btnBack.getScene().getWindow().getX() + btnBack.getScene().getWindow().getWidth() / 4);
        dialog.setY(btnBack.getScene().getWindow().getY() + btnBack.getScene().getWindow().getHeight() / 3.6);
        dialog.show();

    }

    @FXML
    private void handleBtnJanitorsOffice(ActionEvent event) throws IOException {
        Stage dialog = new Stage(StageStyle.TRANSPARENT);
        Parent parent = FXMLLoader.load(getClass().getResource("CallOfficeDialog.fxml"));

        Button dialogBtnClose = (Button) parent.lookup("#dialogBtnClose");
        Label lblOffice = (Label) parent.lookup("#lblOffice");
        Label lblCalling = (Label) parent.lookup("#lblCalling");

        lblOffice.setText("관리실");

        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(() -> {
                        lblCalling.setText("호출중");
                    });
                    Thread.sleep(400);
                    for (int i = 0; i < 4; i++) {
                        Platform.runLater(() -> {
                            lblCalling.setText(lblCalling.getText() + ".");
                        });
                        Thread.sleep(400);
                    }
                    if (isCancelled()) {
                        break;
                    }
                }
                return null;
            }

            @Override
            protected void cancelled() {
                dialog.close();
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        dialogBtnClose.setOnAction(e -> {
            task.cancel();
        });

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        dialog.setScene(scene);
        dialog.initOwner(btnBack.getScene().getWindow());
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setX(btnBack.getScene().getWindow().getX() + btnBack.getScene().getWindow().getWidth() / 4);
        dialog.setY(btnBack.getScene().getWindow().getY() + btnBack.getScene().getWindow().getHeight() / 3.6);
        dialog.show();
    }

    @FXML
    private void handleBtnSecurityOffice(ActionEvent event) throws IOException {
        Stage dialog = new Stage(StageStyle.TRANSPARENT);
        Parent parent = FXMLLoader.load(getClass().getResource("CallOfficeDialog.fxml"));

        Button dialogBtnClose = (Button) parent.lookup("#dialogBtnClose");
        Label lblOffice = (Label) parent.lookup("#lblOffice");
        Label lblCalling = (Label) parent.lookup("#lblCalling");

        lblOffice.setText("경비실");

        task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true) {
                    Platform.runLater(() -> {
                        lblCalling.setText("호출중");
                    });
                    Thread.sleep(400);
                    for (int i = 0; i < 4; i++) {
                        Platform.runLater(() -> {
                            lblCalling.setText(lblCalling.getText() + ".");
                        });
                        Thread.sleep(400);
                    }
                    if (isCancelled()) {
                        break;
                    }
                }
                return null;
            }

            @Override
            protected void cancelled() {
                dialog.close();
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

        dialogBtnClose.setOnAction(e -> {
            task.cancel();
        });

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        dialog.setScene(scene);
        dialog.initOwner(btnBack.getScene().getWindow());
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setX(btnBack.getScene().getWindow().getX() + btnBack.getScene().getWindow().getWidth() / 4);
        dialog.setY(btnBack.getScene().getWindow().getY() + btnBack.getScene().getWindow().getHeight() / 3.6);
        dialog.show();
    }

    @FXML
    private void handleBtnBack(ActionEvent event) {
        StackPane rootPane = (StackPane) btnBack.getScene().getRoot();
        callControl.setOpacity(1);
        KeyValue keyValue = new KeyValue(callControl.opacityProperty(), 0);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500),
                e -> rootPane.getChildren().remove(callControl),
                keyValue
        );
        Timeline timeLine = new Timeline();
        timeLine.getKeyFrames().add(keyFrame);
        timeLine.play();
        stop = true;
        System.gc();
    }
    
      @FXML
    private void handleBtnRefresh(ActionEvent event){
        txtDong.setText("");
        txtHo.setText("");
    }
}
