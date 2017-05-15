package smarthomepanel.chart;

import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

class CustomBarChart extends BarChart<String, Number> {

    public CustomBarChart(CategoryAxis xAxis, Axis<Number> yAxis) {
        super(xAxis, yAxis);

        setAnimated(true);
//        setLegendVisible(true);
        setTitleSide(Side.TOP);
        setBackground(Background.EMPTY);
        setHorizontalGridLinesVisible(false);
        setVerticalGridLinesVisible(false);

    }

    @Override
    protected void layoutPlotChildren() {
        super.layoutPlotChildren();

        for (Series<String, Number> series : getData()) {
            for ( Data<String, Number> data : series.getData() ) {
                StackPane bar = (StackPane) data.getNode();

                Label label = null;

                for ( Node node : bar.getChildrenUnmodifiable() ) {
                    
                    if ( node instanceof Label ) {
                        label = (Label) node;
                        break;
                    }
                }

                if (label == null) {
                    label = new Label(data.getYValue() + "");
                    bar.getChildren().add(label);
                } else {
                    label.setText(data.getYValue() + "");
                }
            }
        }
    }
}
