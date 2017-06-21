package sensingcar.coap.server.resource;

import com.pi4j.io.gpio.RaspiPin;
import hardware.led.RgbLedPWM;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LcdResource extends CoapResource {
	//Field
	private static final Logger logger = LoggerFactory.getLogger(LcdResource.class);
					
	//Constructor
	public LcdResource() throws Exception {
		super("lcd");
	}
	
	//Method
	private void setText(String line0, String line1) {
	}
	
	@Override
	public void handleGET(CoapExchange exchange) {
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		//{ "command":"change", "line0":"xxx", "line1":"xxx" }
		//{ "command":"status" }
		try {
			String requestJson = exchange.getRequestText();
			JSONObject requestJsonObject = new JSONObject(requestJson);
			String command = requestJsonObject.getString("command");
			if(command.equals("change")) {
			} else if(command.equals("status")) {
			}
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "success");
			String responseJson = responseJsonObject.toString();
			exchange.respond(responseJson);
		} catch(Exception e) {
			logger.info(e.toString());
			JSONObject responseJsonObject = new JSONObject();
			responseJsonObject.put("result", "fail");
			String responseJson = responseJsonObject.toString();
			exchange.respond(responseJson);
		}		
	}
}
