package smarthomepanel.externalsignal;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ExternalSignController implements Initializable {

	@FXML
	private TextField txtDong;
	@FXML
	private TextField txtHo;
	private Socket socket;
	@FXML
	private TextField txtDate;
	@FXML
	private TextField txtNoticePlace;
	@FXML
	private TextField txtTitle;
	@FXML
	private TextArea txtContents;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		startClient();

	}

	private void startClient() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					// Socket 생성
					socket = new Socket();
					// Server와 연결하기
					socket.connect(new InetSocketAddress("192.168.3.11", 50001));
					// 연결 성공 출력하기
					System.out.println("연결성공");
					// 데이터받기
				} catch (IOException ex) {
					stopClient();
					return;
				}
			}
		};
		thread.start();
	}

	private void stopClient() {
		try {
			System.out.println("클라이언트 정상 종료");
			socket.close();
		} catch (IOException ex) {
			System.out.println("소켓 안닫힘");
		}
	}

	private void send(String str) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					OutputStream os = socket.getOutputStream();
					byte[] byteArr = str.getBytes();
					os.write(byteArr);
					os.flush();
					os.close();
				} catch (IOException e) {
					try {
						socket.close();
					} catch (IOException ex) {
					}
				}
				stopClient();
				startClient();
			}
		};
		thread.start();
	}

	@FXML
	private void handleBtnCall() {
		send(txtDong.getText() + ":" + txtHo.getText());
		txtDong.clear();
		txtHo.clear();
	}

	@FXML
	private void handleBtnArriveBox(ActionEvent e) {
		send("Box");
	}

	@FXML
	private void handleBtnCallExternalBuilding(ActionEvent e) {
		send("CallExternalBuilding");
	}

	@FXML
	private void handleBtnCallExternalDoor(ActionEvent e) {
		send("CallExternalDoor");
	}

	@FXML
	private void handleBtnCallExternalParkingLot(ActionEvent e) {
		send("CallExternalParkingLot");
	}

	@FXML
	private void handleBtnNotice(ActionEvent event) {

		send(txtDate.getText() + ";" + txtNoticePlace.getText() + ";" + txtTitle.getText() + ";" + txtContents.getText());
		txtDate.clear();
		txtNoticePlace.clear();
		txtTitle.clear();
		txtContents.clear();
	}

}
