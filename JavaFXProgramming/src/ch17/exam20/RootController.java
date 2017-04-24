package ch17.exam20;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class RootController implements Initializable {

    @FXML
    private ListView<Phone> listView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setCellFactory(new Callback<ListView<Phone>, ListCell<Phone>>() {
            @Override
            public ListCell<Phone> call(ListView<Phone> param) {
                ListCell<Phone> listCell = new ListCell<Phone>() {
                    @Override
                    protected void updateItem(Phone item, boolean empty) {
                        super.updateItem(item, empty);
                        if(empty) return;
                        try {
                            //Cell에 들어갈 컨테이너 생성
                            HBox hbox = (HBox) FXMLLoader.load(getClass().getResource("item.fxml"));
                            ImageView phoneImage = (ImageView) hbox.lookup("#image");
                            Label phoneName = (Label) hbox.lookup("#name");
                            Label phoneContent = (Label) hbox.lookup("#content");
                            phoneImage.setImage(new Image(getClass().getResource("images/"+item.getImage()).toString()));
                            phoneName.setText(item.getName());
                            phoneContent.setText(item.getContent());
                            //Cell의 내용으로 설정
                            setGraphic(hbox);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                };
                return listCell;
            }
        });
        
        //선택 속성 감시
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Phone>() {
            @Override
            public void changed(ObservableValue<? extends Phone> observable, Phone oldValue, Phone newValue) {
                System.out.println(newValue.getName() + ":" + newValue.getImage());
            }
        });
        
        //클릭 이벤트 처리
        listView.setOnMouseClicked(e-> {
            Phone phone = listView.getSelectionModel().getSelectedItem();
            System.out.println(phone.getName());
        });
        
        //데이타 세팅
        ObservableList<Phone> value = FXCollections.observableArrayList();
        value.add(new Phone("phone01.png", "갤럭시S1", "삼성 스마트폰의 최초 모델입니다."));
        value.add(new Phone("phone02.png", "갤럭시S2", "삼성 스마트폰의 두번째 모델입니다."));
        value.add(new Phone("phone03.png", "갤럭시S3", "삼성 스마트폰의 세번째 모델입니다."));
        listView.setItems(value);
    }    
    
}
