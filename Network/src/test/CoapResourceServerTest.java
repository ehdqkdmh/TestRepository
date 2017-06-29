package test;

import java.net.InetSocketAddress;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;
import org.eclipse.californium.proxy.DirectProxyCoapResolver;
import org.eclipse.californium.proxy.ProxyHttpServer;
import org.eclipse.californium.proxy.resources.ForwardingResource;
import org.eclipse.californium.proxy.resources.ProxyCoapClientResource;

public class CoapResourceServerTest {
	//Field
	private CoapServer coapServer;
	
	//Constructor
	public CoapResourceServerTest() throws Exception {
		coapServer = new CoapServer();
		InetSocketAddress isa1 = new InetSocketAddress("192.168.3.11", 5683);
		InetSocketAddress isa2 = new InetSocketAddress("localhost", 5683);
		coapServer.addEndpoint(new CoapEndpoint(isa1));
		coapServer.addEndpoint(new CoapEndpoint(isa2));
		
		coapServer.add(new CoapResource01());
		
		coapServer.start();
	}
	
	//Method
	public void shutdown() {
		coapServer.stop();
		coapServer.destroy();
	}
	
	public static void main(String[] args) throws Exception {
		CoapResourceServerTest server = new CoapResourceServerTest();
		System.out.println("CoAP server is listening on port 5683");
		System.in.read();
		server.shutdown();
	}
}
