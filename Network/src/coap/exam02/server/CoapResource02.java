package coap.exam02.server;

import com.pi4j.io.gpio.RaspiPin;
import hardware.motor.PCA9685;
import hardware.motor.SG90ServoPCA9685Duration;
import hardware.sensor.UltrasonicSensor;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.json.JSONObject;

public class CoapResource02 extends CoapResource {
	//Field
	private SG90ServoPCA9685Duration servoMotor;
	private PCA9685 pca9685;
	private UltrasonicSensor ultrasonicSensor;
	private int distance;
	
	//Constructor
	public CoapResource02() throws Exception {
		super("resource02");
		pca9685 = PCA9685.getInstance();
		servoMotor = new SG90ServoPCA9685Duration(pca9685, PCA9685.PWM_11);
		ultrasonicSensor = new UltrasonicSensor(RaspiPin.GPIO_28, RaspiPin.GPIO_29);
		
		Thread thread = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						distance = ultrasonicSensor.getDistance();
					} catch(Exception e) {
						e.printStackTrace();
					}
					try { Thread.sleep(100); } catch(Exception e) {}
				}
			}
		};
		thread.start();
	}
	
	//Method
	@Override
	public void handleGET(CoapExchange exchange) {
		String kind = exchange.getRequestOptions().getUriQuery().get(0).split("=")[1];
		String angle = exchange.getRequestOptions().getUriQuery().get(1).split("=")[1];
		
		if(kind.equals("ultrasonicsensor")) {
			servoMotor.setAngle(Integer.parseInt(angle));
			try { Thread.sleep(500); } catch(Exception e) {}
			exchange.respond(String.valueOf(distance));
		} else {
			exchange.respond("ok");
		}
	}

	@Override
	public void handlePOST(CoapExchange exchange) {
		String json = exchange.getRequestText();
		JSONObject jsonObject = new JSONObject(json);
		String kind = jsonObject.getString("kind");
		int angle = jsonObject.getInt("angle");
		
		if(kind.equals("ultrasonicsensor")) {
			servoMotor.setAngle(angle);
			try { Thread.sleep(500); } catch(Exception e) {}
			exchange.respond(String.valueOf(distance));
		} else {
			exchange.respond("ok");
		}
	}
}
