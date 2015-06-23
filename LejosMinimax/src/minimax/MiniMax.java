package minimax;

import java.util.List;

/**
 * 
 * @author Silvio Gonçalves Neto
 *
 */
public class MiniMax {

	private int maxDepth = -1; // por padrão deixa sem um limite de profundidade
	private Tabuleiro tabuleiro;
	
	public MiniMax() {
		super();
	}
	
	public MiniMax(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}
	
	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}
	
	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
	/**
	 * Algoritmo de MiniMax.
	 * 
	 * Faz a pesquisa da melhor jogada para o jogador passado como parametro.
	 * 
	 * O metodo retorna um objeto contendo a melhor jogada (x, y) e qual foi o melhor resultado obtido.
	 * 
	 * @param t
	 * @param jogador
	 * @param profundidade
	 * @param alpha
	 * @param beta
	 * @return
	 */
	public MelhorJogada buscaMinimax(Tabuleiro t, Peca jogador, int profundidade, int alpha, int beta) {
		List<int[]> proximasJogadas = t.proximasJogadas();
		
		if (proximasJogadas.isEmpty() || t.fimJogo())
			return new MelhorJogada(-1, -1, t.validarResultado());
		else if (maxDepth != -1 && profundidade == maxDepth)
			return new MelhorJogada(-1, -1, t.validarResultado());
		
		MelhorJogada melhorJogada = new MelhorJogada();
		MelhorJogada minimaxJogada;
		
		Peca jogadorAtual = jogador;
		
		for (int[] jogada : proximasJogadas) {
			t.fazerJogada(jogada[0], jogada[1]);
			t.proximoJogador();
			
			minimaxJogada = buscaMinimax(t, t.getJogadorAtual(), (profundidade + 1), alpha, beta);
			
			if (jogadorAtual == Peca.COMPUTADOR) {
				if (minimaxJogada.getResultado() > alpha) { // MAX
					alpha = minimaxJogada.getResultado();
					
					melhorJogada.setResultado(alpha);
					melhorJogada.setX(jogada[0]);
					melhorJogada.setY(jogada[1]);
				}
			} else {
				if (minimaxJogada.getResultado() < beta) { // MIN
					beta = minimaxJogada.getResultado();

					melhorJogada.setResultado(beta);
					melhorJogada.setX(jogada[0]);
					melhorJogada.setY(jogada[1]);
				}
			}

			t.desfazerUltimaJogada();
			t.proximoJogador();
			
			if (alpha >= beta) break;
		}
		
		melhorJogada.setResultado((jogador == Peca.COMPUTADOR) ? alpha : beta);
		return melhorJogada;
	}

	@Override
	public String toString() {
		return "MiniMax";
	}
}
