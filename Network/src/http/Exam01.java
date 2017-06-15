package http;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Exam01 {

	//Field
	//Constructor
	//Method
	public static void main(String[] args) throws IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://192.168.3.11:8080/IoTWebProgramming");
		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					System.out.println("success");
				} finally {
					instream.close();
				}
			}
		} finally {
			response.close();
		}
	}
}
