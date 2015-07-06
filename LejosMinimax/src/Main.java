import lejos.nxt.Button;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("ENTER pra iniciar");

		while (!Button.ENTER.isPressed()) ;

		JogoVelha jogo = new JogoVelha();

		jogo.pratras();

		jogo.iniciar();
	}
}