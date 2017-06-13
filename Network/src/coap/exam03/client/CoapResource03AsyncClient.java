package coap.exam03.client;

import coap.exam02.client.*;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.MediaTypeRegistry;
import org.json.JSONObject;

public class CoapResource03AsyncClient {

	//Field
	private CoapClient coapClient;

	//Constructor
	public CoapResource03AsyncClient() {
		coapClient = new CoapClient();
	}

	//Method
	public void get(int angle) {
		String queryString = "kind=ultrasonicsensor&angel=" + angle;
		coapClient.setURI("coap://192.168.3.9/resource02?" + queryString);
		coapClient.get(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				if (response.getCode() == CoAP.ResponseCode.CONTENT) {
					String text = response.getResponseText();
					System.out.println(angle + "각도 거리: " + text);
				}
			}
			@Override
			public void onError() {
			}
		});
	}

	public void post(int angle) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("kind", "ultrasonicsensor");
		jsonObject.put("angle", angle);
		String json = jsonObject.toString();

		coapClient.setURI("coap://192.168.3.9/resource02");
		
		coapClient.post(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				if (response.getCode() == CoAP.ResponseCode.CONTENT) {
					String text = response.getResponseText();
					System.out.println(angle + "각도 거리: " + text);
				}
			}
			@Override
			public void onError() {
			}
		}, json, MediaTypeRegistry.APPLICATION_JSON);
	}

	public void shutdown() {
		coapClient.shutdown();
	}

	public static void main(String[] args) throws Exception {
		CoapResource03AsyncClient client = new CoapResource03AsyncClient();
		for (int i = 0; i <= 180; i += 10) {
			client.post(i);
			Thread.sleep(1000);
		}
		System.in.read();
		client.shutdown();
	}
}
