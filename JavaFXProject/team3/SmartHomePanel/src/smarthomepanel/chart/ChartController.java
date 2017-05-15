package smarthomepanel.chart;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ChartController implements Initializable {

    @FXML
    private Label lblTitle;
    @FXML
    private Button btnHome;
    @FXML
    private ImageView btnHomeImage;
    @FXML
    private Label lblElecCharge;
    @FXML
    private Label lblElecChargep;
    @FXML
    private Label lblWaterChargep;
    @FXML
    private Label lblWaterCharge;
    @FXML
    private Label lblGasCharge;
    @FXML
    private Label lblGasChargep;
    @FXML
    private AnchorPane chart;
    @FXML
    private Label lblTime;
    private boolean stop;
    @FXML
    private Label lblNotice1;
    @FXML
    private Label lblNotice2;
    @FXML
    private Label lblNotice3;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //home버튼 이벤트처리
        btnHome.setOnAction(e -> handleBtnHome(e));

        //현재 몇월인지 받아오기
        Calendar cal = Calendar.getInstance();
        int currMonth = cal.get(Calendar.MONTH) + 1;

        //시간정보 설정
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
        thread.start();

//사용량 데이터 받아오기(네트워크에서?)
        int[] elecUsage = new int[3];
        elecUsage[0] = 326;
        elecUsage[1] = 244;
        elecUsage[2] = 392;
        int[] waterUsage = new int[3];
        waterUsage[0] = 12;
        waterUsage[1] = 8;
        waterUsage[2] = 15;
        int[] gasUsage = new int[3];
        gasUsage[0] = 9;
        gasUsage[1] = 3;
        gasUsage[2] = 11;

        CategoryAxis elecXAxis = new CategoryAxis();
        NumberAxis elecYAxis = new NumberAxis();
        CustomBarChart elecChart = new CustomBarChart(elecXAxis, elecYAxis);
        elecChart.setLayoutX(105);
        elecChart.setLayoutY(102);
        elecChart.setPrefSize(200, 222);
        elecChart.setLegendVisible(true);
        elecChart.setLegendSide(Side.LEFT);
        chart.getChildren().add(elecChart);

        CategoryAxis waterXAxis = new CategoryAxis();
        NumberAxis waterYAxis = new NumberAxis();
        CustomBarChart waterChart = new CustomBarChart(waterXAxis, waterYAxis);
        waterChart.setLayoutX(311);
        waterChart.setLayoutY(102);
        waterChart.setPrefSize(200, 222);
        waterChart.setLegendVisible(false);
        chart.getChildren().add(waterChart);

        CategoryAxis gasXAxis = new CategoryAxis();
        NumberAxis gasYAxis = new NumberAxis();
        CustomBarChart gasChart = new CustomBarChart(gasXAxis, gasYAxis);
        gasChart.setLayoutX(516);
        gasChart.setLayoutY(102);
        gasChart.setPrefSize(200, 222);
        gasChart.setLegendVisible(false);
        chart.getChildren().add(gasChart);

        //전기 차트 데이터 생성
        XYChart.Series series1 = new XYChart.Series<>();
        series1.setName("우리집");
        ObservableList<XYChart.Data<String, Number>> data1 = FXCollections.observableArrayList();
        data1.add(new XYChart.Data<String, Number>(currMonth + "월", 0));
        series1.setData(data1);
        elecChart.getData().add(series1);

        XYChart.Series series2 = new XYChart.Series<>();
        series2.setName("평균");
        ObservableList<XYChart.Data<String, Number>> data2 = FXCollections.observableArrayList();
        data2.add(new XYChart.Data<String, Number>(currMonth + "월", 0));
        series2.setData(data2);
        elecChart.getData().add(series2);

        XYChart.Series series3 = new XYChart.Series<>();
        series3.setName("금월예상");
        ObservableList<XYChart.Data<String, Number>> data3 = FXCollections.observableArrayList();
        data3.add(new XYChart.Data<String, Number>(currMonth + "월", 0));
        series3.setData(data3);
        elecChart.getData().add(series3);

        //수도 차트 데이터 생성
        XYChart.Series series4 = new XYChart.Series<>();
        series4.setName("우리집");
        ObservableList<XYChart.Data<String, Number>> data4 = FXCollections.observableArrayList();
        data4.add(new XYChart.Data<String, Number>(currMonth + "월", 0));
        series4.setData(data4);
        waterChart.getData().add(series4);

        XYChart.Series series5 = new XYChart.Series<>();
        series5.setName("평균");
        ObservableList<XYChart.Data<String, Number>> data5 = FXCollections.observableArrayList();
        data5.add(new XYChart.Data<String, Number>(currMonth + "월", 0));
        series5.setData(data5);
        waterChart.getData().add(series5);

        XYChart.Series series6 = new XYChart.Series<>();
        series6.setName("금월예상");
        ObservableList<XYChart.Data<String, Number>> data6 = FXCollections.observableArrayList();
        data6.add(new XYChart.Data<String, Number>(currMonth + "월", 0));
        series6.setData(data6);
        waterChart.getData().add(series6);

        //가스 차트 데이터 생성
        XYChart.Series series7 = new XYChart.Series<>();
        series7.setName("우리집");
        ObservableList<XYChart.Data<String, Number>> data7 = FXCollections.observableArrayList();
        data7.add(new XYChart.Data<String, Number>(currMonth + "월", 0));
        series7.setData(data7);
        gasChart.getData().add(series7);

        XYChart.Series series8 = new XYChart.Series<>();
        series8.setName("평균");
        ObservableList<XYChart.Data<String, Number>> data8 = FXCollections.observableArrayList();
        data8.add(new XYChart.Data<String, Number>(currMonth + "월", 0));
        series8.setData(data8);
        gasChart.getData().add(series8);

        XYChart.Series series9 = new XYChart.Series<>();
        series9.setName("금월예상");
        ObservableList<XYChart.Data<String, Number>> data9 = FXCollections.observableArrayList();
        data9.add(new XYChart.Data<String, Number>(currMonth + "월", 0));
        series9.setData(data9);
        gasChart.getData().add(series9);

        elecYAxis.setOpacity(0);
        elecYAxis.setAutoRanging(false);
        elecYAxis.setLowerBound(0);
        elecYAxis.setUpperBound(500);

        waterYAxis.setOpacity(0);
        waterYAxis.setAutoRanging(false);
        waterYAxis.setLowerBound(0);
        waterYAxis.setUpperBound(50);

        gasYAxis.setOpacity(0);
        gasYAxis.setAutoRanging(false);
        gasYAxis.setLowerBound(0);
        gasYAxis.setUpperBound(50);

        //애니메이션
        barAnimation(elecChart, elecUsage);
        barAnimation(waterChart, waterUsage);
        barAnimation(gasChart, gasUsage);
        //Title 텍스트 설정
        lblTitle.setText(currMonth + "월 에너지 사용량");

        //Notice 라벨 설정
        String notice = new String();
        notice = "";
        if (elecUsage[0] > elecUsage[1]) {
            lblNotice1.setText("평균 이상");
        } else {
            lblNotice1.setText("");
        }
        if (waterUsage[0] > waterUsage[1]) {
            lblNotice2.setText("평균 이상");
        } else {
            lblNotice2.setText("");
        }
        if (gasUsage[0] > gasUsage[1]) {
            lblNotice3.setText("평균 이상");
        } else {
            lblNotice3.setText("");
        }

        //요금 계산(p->금월예상)
        int elecCharge, elecChargep, waterCharge, waterChargep, gasCharge, gasChargep;
        if (elecUsage[0] > 200) {
            elecCharge = (int) (1600 + 18660 + (elecUsage[0] - 200) * 187.9);
        } else {
            elecCharge = (int) (1600 + elecUsage[0] * 93.3);
        }
        if (elecUsage[2] > 200) {
            elecChargep = (int) (1600 + 18660 + (elecUsage[2] - 200) * 187.9);
        } else {
            elecChargep = (int) (1600 + elecUsage[2] * 93.3);
        }
        elecChargep = (int) (elecChargep * 1.137);
        String str = String.format("%,d", elecCharge);
        lblElecCharge.setText(str);
        elecCharge = (int) (elecCharge * 1.137);
        str = String.format("%,d", elecChargep);
        lblElecChargep.setText(str);

        waterCharge = waterUsage[0] * 1260 - 6900;
        waterChargep = waterUsage[2] * 1260 - 6900;
        str = String.format("%,d", waterCharge);
        lblWaterCharge.setText(str);
        str = String.format("%,d", waterChargep);
        lblWaterChargep.setText(str);

        gasCharge = (int) ((gasUsage[0] * 42.767) * 15.3614 * 1.1);
        gasChargep = (int) ((gasUsage[2] * 42.767) * 15.3614 * 1.1);
        str = String.format("%,d", gasCharge);
        lblGasCharge.setText(str);
        str = String.format("%,d", gasChargep);
        lblGasChargep.setText(str);

    }

    private void handleBtnHome(ActionEvent e) {
        StackPane rootPane = (StackPane) btnHome.getScene().getRoot(); //Root가 바로 stackPane
        chart.setOpacity(1); //opacity: 투명도 (1이 불투명 0이 투명)
        KeyValue keyValue = new KeyValue(chart.opacityProperty(), 0); //무엇을: login의 opacity값을 종료값: 0까지 바꾸겠다.
        KeyFrame keyFrame = new KeyFrame(Duration.millis(500),
                event -> rootPane.getChildren().remove(chart), //애니메이션 종료 후 실행되는 코드
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

    private void barAnimation(BarChart<String, Number> chart, int[] usage) {
        Timeline timeLine = new Timeline();

        KeyValue kv1 = new KeyValue(chart.getData().get(0).getData().get(0).YValueProperty(), usage[0]);
        KeyValue kv2 = new KeyValue(chart.getData().get(1).getData().get(0).YValueProperty(), usage[1]);
        KeyValue kv3 = new KeyValue(chart.getData().get(2).getData().get(0).YValueProperty(), usage[2]);
        KeyFrame kf = new KeyFrame(Duration.millis(700), kv1, kv2, kv3);

        timeLine.getKeyFrames().add(kf);
        timeLine.play();
    }

}
