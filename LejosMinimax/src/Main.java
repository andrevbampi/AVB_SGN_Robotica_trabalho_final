import classes.Celula;
import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;


public class Main {
	
	

	public static void main(String[] args) throws InterruptedException {
		
		Celula[][] tabuleiro = new Celula[2][2];

		while (!Button.ENTER.isPressed());
		
		ColorSensor color = new ColorSensor(SensorPort.S1);
		
//		Motor.A.rotate(360);
		
		Motor.B.rotate(65);
		
		Thread.sleep(3000);
		
		Motor.B.rotate(-65);

		Thread.sleep(3000);
		
		int giro = 10;
		int giroMotorB = 5;
		
		while (color.getColorID() != 0) {
			Motor.B.rotate(giro);
			
			giroMotorB += giro;
		}
		
		System.out.println("giro: " + giroMotorB);

		while (!Button.ENTER.isPressed());
	}

}
