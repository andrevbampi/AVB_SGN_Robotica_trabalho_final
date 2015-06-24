import classes.Celula;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import minimax.MelhorJogada;
import minimax.MiniMax;
import minimax.Peca;
import minimax.Tabuleiro;


public class JogoVelha {

	private MiniMax minimax;
	private Tabuleiro tabuleiro;
	private UltrasonicSensor sonic;
	
	public JogoVelha() {
		tabuleiro = new Tabuleiro();
		minimax = new MiniMax(tabuleiro);
		minimax.setMaxDepth(10);
		sonic = new UltrasonicSensor(SensorPort.S3);
	}
	
	public void iniciar() {
		
		imprimirTabuleiro();
		
		while (true) {
			
			jogadaJogador();
			imprimirTabuleiro();
			
			if (tabuleiro.fimJogo())
				break;

			esperar();
			
			jogadaLejos();
			imprimirTabuleiro();
			
			if (tabuleiro.fimJogo())
				break;
		}
		
		Peca vencedor = tabuleiro.getVencedor();
		
		System.out.println("Win: " + vencedor.toString());
		
		esperar();
	}

	
	private void jogadaJogador() {
		System.out.println("jog. ini");
		esperar();
		
//		while (true) {
//			System.out.println(sonic.getDistance());
//		}
		
		tabuleiro.proximoJogador();
		
		tabuleiro.lerTabuleiro();
		System.out.println("jog. fim");
	}
	
	private void jogadaLejos() {
		System.out.println("lejos. ini");
		MelhorJogada jogada = minimax.buscaMinimax(tabuleiro, Peca.COMPUTADOR, 0, Integer.MIN_VALUE, Integer.MAX_VALUE); 

		System.out.println("x:" + jogada.getX() + " y:" + jogada.getY());
		
		tabuleiro.fazerJogada(jogada.getX(), jogada.getY());
		colocarBolinha(jogada.getX(), jogada.getY());
		System.out.println("lejos. fim");
	}
	
	private void colocarBolinha(int x, int y) {
		Celula c = tabuleiro.getTabuleiro()[x][y];
		
		for (int i = 0; i < c.getCaminho().length; i++) {
			tabuleiro.mover(c.getCaminho()[i]);
		}
	}
	
	private void esperar() {
		while (!Button.ENTER.isPressed());
	}
	
	private void imprimirTabuleiro() {
		System.out.println("Tabuleiro atual");
		System.out.println(tabuleiro.toString() + "\n");
	}

	public void frente() {
		Motor.B.setSpeed(100);

		try {
			Motor.B.forward();

			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Motor.B.stop();
		}
	}

	public void pratras() {
		Motor.B.setSpeed(100);

		try {
			Motor.B.backward();

			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Motor.B.stop();
		}
	}
}
