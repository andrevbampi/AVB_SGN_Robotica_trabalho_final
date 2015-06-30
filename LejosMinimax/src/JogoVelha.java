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
			
			try {
				jogadaJogador();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			imprimirTabuleiro();
			
			if (tabuleiro.fimJogo())
				break;
			
			jogadaLejos();
			imprimirTabuleiro();
			
			if (tabuleiro.fimJogo())
				break;
		}
		
		Peca vencedor = tabuleiro.getVencedor();
		if (vencedor == null) {
			System.out.println("Deu Velha!");
		} else {
			System.out.println("Win: " + vencedor.toString());
		}
		
		esperar();
	}

	
	private void jogadaJogador() throws InterruptedException {
		System.out.println("JOGADOR SUA VEZ");
		
		for (int i = 0; i < 4; i++) {
			sonic.getDistance();
			Thread.sleep(500);
		}

		// jogador ainda nao fez jogada
		while (sonic.getDistance() > 30) {
			Thread.sleep(500);
		}
		
		// jogador esta fazendo a jogada
		while (sonic.getDistance() < 30) {
			Thread.sleep(500);
		}
		
		tabuleiro.lerTabuleiro();
		tabuleiro.proximoJogador();
	}
	
	private void jogadaLejos() {
		MelhorJogada jogada = minimax.buscaMinimax(tabuleiro, Peca.COMPUTADOR, 0, Integer.MIN_VALUE, Integer.MAX_VALUE); 

		tabuleiro.fazerJogada(jogada.getX(), jogada.getY());
		colocarBolinha(jogada.getX(), jogada.getY());
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
