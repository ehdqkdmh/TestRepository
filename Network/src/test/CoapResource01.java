package test;

import java.util.List;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.network.Endpoint;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class CoapResource01 extends CoapResource {
	//Field
	private int value;
	
	//Constructor
	public CoapResource01() {
		super("resource01");
		//관찰 기능 활성화
		setObservable(true);
		//관찰 기능을 제공하다라는 것을 클라이언트가 알 수 있도록 설정
		getAttributes().setObservable();
		//테스트
		Thread thread = new Thread() {
			@Override
			public void run() {
				int i = 0;
				while(true) {
					value = i;
					changed();
					i++;
					try { Thread.sleep(1000); } catch(Exception e) {}
				}
			}
		};
		thread.start();
		System.out.println(this.getObserverCount());
	}
	//Method
	@Override
	public void handleGET(CoapExchange exchange) {
		exchange.respond("value=" + value);
		//System.out.println(exchange.getSourceAddress());
		//System.out.println(this.getObserverCount());
	}
	
	

	@Override
	public void handlePOST(CoapExchange exchange) {
	}
}
