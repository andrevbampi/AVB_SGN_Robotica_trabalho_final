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
			
			jogadaJogador();
			imprimirTabuleiro();
			
			if (tabuleiro.fimJogo())
				break;

			jogadaLejos();
			imprimirTabuleiro();
			
			if (tabuleiro.fimJogo())
				break;
		}
		
	}

	
	private void jogadaJogador() {

		
	}
	
	private void jogadaLejos() {
		MelhorJogada jogada = minimax.buscaMinimax(tabuleiro, Peca.COMPUTADOR, 0, Integer.MIN_VALUE, Integer.MAX_VALUE); 

		tabuleiro.fazerJogada(jogada.getX(), jogada.getY());
	}
	
	private void imprimirTabuleiro() {
		System.out.println("Tabuleiro atual");
		System.out.println(tabuleiro.toString() + "\n");
	}
}
