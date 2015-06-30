import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;


public class MainCalibragem {

	public static void main(String[] args) {
		System.out.println("ENTER pra iniciar");

		while (!Button.ENTER.isPressed())
			;

		UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S3);

		Motor.C.rotate(45);
		
		while (true) {
			Motor.C.setSpeed(200);
			Motor.C.rotate(45);
			
//			System.out.println(sonic.getDistance());
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
