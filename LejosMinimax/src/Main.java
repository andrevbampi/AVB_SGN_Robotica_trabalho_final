import lejos.nxt.Button;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("ENTER pra iniciar");

		while (!Button.ENTER.isPressed())
			;

		JogoVelha jogo = new JogoVelha();
		
		jogo.pratras();

		jogo.iniciar();

		// Motor.A.rotate(360);
		//
		// int giro = 10;
		// int giroMotorB = 5;
		//
		// while (color.getColorID() != 0) {
		// Motor.B.rotate(giro);
		//
		// giroMotorB += giro;
		// }
		//
		// System.out.println("giro: " + giroMotorB);
		//
		// while (!Button.ENTER.isPressed());
	}

}
