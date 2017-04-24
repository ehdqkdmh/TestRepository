package ch17.exam27;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class RootController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void menuItemNew(ActionEvent event) {
        System.out.println("새로만들기 클릭됨");
    }

    @FXML
    private void menuItemJavaApp(ActionEvent event) {
        System.out.println("자바 애플리케이션 프로젝트 생성");
    }
    
}
