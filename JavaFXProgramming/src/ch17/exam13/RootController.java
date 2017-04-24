package ch17.exam13;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class RootController implements Initializable {
    @FXML private Button btn1;
    @FXML private Button btn2;
    @FXML private Button btn3;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //how1
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("버튼1이 클릭됨");
            }
        });
        
        //how2
        btn2.setOnAction(event -> {
            System.out.println("버튼2이 클릭됨");
        });
    }
    
    //how3
    public void btn3Handle(ActionEvent event) {
        System.out.println("버튼3이 클릭됨");
    }
    
}
