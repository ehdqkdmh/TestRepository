package test;

import java.io.IOException;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;

public class CoapResourceClient {
	//Field
	private CoapClient coapClient;
	private CoapObserveRelation coapObserveRelation;
	
	//Constructor
	public CoapResourceClient() {
		coapClient = new CoapClient();
	}
	
	//Method
	public void observe() {
		coapClient.setURI("coap://192.168.3.11/resource01");
		coapObserveRelation = coapClient.observe(new CoapHandler() {
			@Override
			public void onLoad(CoapResponse response) {
				String text = response.getResponseText();
				System.out.println(text);
			}
			@Override
			public void onError() {
			}
		});
	}
	
	public void shutdown() {
		coapObserveRelation.proactiveCancel();
		coapClient.shutdown();
	}
	
	public static void main(String[] args) throws IOException {
		CoapResourceClient client = new CoapResourceClient();
		client.observe();
		System.in.read();
		client.shutdown();
	}
}
