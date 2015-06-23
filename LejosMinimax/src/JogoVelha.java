import classes.Celula;
import lejos.nxt.Button;
import minimax.MelhorJogada;
import minimax.MiniMax;
import minimax.Peca;
import minimax.Tabuleiro;


public class JogoVelha {

	private MiniMax minimax;
	private Tabuleiro tabuleiro;
	
	public JogoVelha() {
		tabuleiro = new Tabuleiro();
		minimax = new MiniMax(tabuleiro);
	}
	
	public void iniciar() {
		
		imprimirTabuleiro();
		
		while (true) {

			esperar();
			
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
		
	}

	
	private void jogadaJogador() {
		
		
	}
	
	private void jogadaLejos() {
		tabuleiro.lerTabuleiro();
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
}
