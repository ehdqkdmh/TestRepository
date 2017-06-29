package coap.exam01.server;

import test.CoapResource01;
import java.io.IOException;
import java.net.InetSocketAddress;
import org.eclipse.californium.core.CoapServer;
import org.eclipse.californium.core.network.CoapEndpoint;

public class CoapResourceServer {
	//Field
	private CoapServer coapServer;
	
	//Constructor
	public CoapResourceServer() {
		coapServer = new CoapServer();
		//InetSocketAddress isa = new InetSocketAddress("192.168.3.9", 5683);
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
	
	public static void main(String[] args) throws IOException {
		CoapResourceServer server = new CoapResourceServer();
		System.out.println("CoAP server is listening on port 5683");
		System.in.read();
		server.shutdown();
	}
}
