package http.exam02;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

public class HttpMultipartClient {
	public static void main(String[] args) throws IOException {
		String title = "제목";
		StringBody titleBody = new StringBody(title, ContentType.create("text/plain", Charset.forName("UTF-8")));
		
		String content = "내용";
		StringBody contentBody = new StringBody(content, ContentType.create("text/plain", Charset.forName("UTF-8")));
		
		File attach = new File("C:/Temp/사막.jpg");
		FileBody attachBody = new FileBody(attach, ContentType.create("image/jpeg"));
		
		HttpPost httpPost = new HttpPost("http://192.168.3.11:8080/IoTWebProgramming/http/exam02");
	
		MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
		//문자 파트
		multipartEntityBuilder.addPart("title", titleBody);
		multipartEntityBuilder.addPart("content", contentBody);
		//파일 파트
		multipartEntityBuilder.setCharset(Charset.forName("UTF-8")); //파일 이름이 한글이 포함되어 있을 경우
		multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE); //브라우저가 파일을 보내는 방식과 동일하게함
		multipartEntityBuilder.addPart("attach", attachBody);
		//멀티파트 인코딩된 본문 얻기
		HttpEntity reqEntity = multipartEntityBuilder.build();
		httpPost.setEntity(reqEntity);
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					InputStream is = resEntity.getContent();
					try {
						String json = "";
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						while(true) {
							String data = br.readLine();
							if(data == null) break;
							json += data;
						}
						JSONObject jsonObject = new JSONObject(json);
						String resTitle = jsonObject.getString("title");
						String resContent = jsonObject.getString("content");
						String originalfilename = jsonObject.getString("originalfilename");
						String savedfilename = jsonObject.getString("savedfilename");
						String filecontenttype = jsonObject.getString("filecontenttype");
						
						System.out.println("title: " + resTitle);
						System.out.println("content: " + resContent);
						System.out.println("originalfilename: " + originalfilename);
						System.out.println("savedfilename: " + savedfilename);
						System.out.println("filecontenttype: " + filecontenttype);						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						is.close();
					}
				}
			} finally {
				response.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.close();
		}
	}
}
