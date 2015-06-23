package minimax;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tabuleiro {

	private Peca[][] tabuleiro;
	private Peca jogadorAtual = Peca.JOGADOR;
	private Stack<int[]> ultimaJogada = new Stack<int[]>();
	
	public Tabuleiro() {
		tabuleiro = new Peca[3][3];
		zerarTabuleiro();
	}
	
	public Peca[][] getTabuleiro() {
		return tabuleiro;
	}
	public void setTabuleiro(Peca[][] tabuleiro) {
		this.tabuleiro = tabuleiro;
	}
	
	public Peca getJogadorAtual() {
		return jogadorAtual;
	}
	public void setJogadorAtual(Peca jogadorAtual) {
		this.jogadorAtual = jogadorAtual;
	}
	
	public void setUlimaJogada(int x, int y) {
		this.ultimaJogada.push(new int[] { x, y });
	}
	
	public void fazerJogada(int x, int y) {
		if (tabuleiro[x][y] != Peca.VAZIO)
			throw new IllegalArgumentException("Jogada não permitida!");
		
		tabuleiro[x][y] = jogadorAtual;
		
		proximoJogador();
		setUlimaJogada(x, y);
	}
	
	public void desfazerUltimaJogada() throws IllegalArgumentException {
		if (this.ultimaJogada.isEmpty())
			throw new IllegalArgumentException("Nenhuma jogada foi efetuada no tabuleiro");
		
		int[] ultimo = this.ultimaJogada.pop();
		
		this.tabuleiro[ultimo[0]][ultimo[1]] = Peca.VAZIO;
		proximoJogador();
	}

	public Peca proximoJogador() {
		jogadorAtual = (jogadorAtual == Peca.COMPUTADOR ? Peca.JOGADOR : Peca.COMPUTADOR);
		return jogadorAtual;
	}

	public boolean fimJogo() {
		Peca vencedor = temGanhadorOuEmpate();
		
		// caso o retorno seja VAZIO é porque não tem ganhador
		// e o tabuleiro ainda não está completo
		if (vencedor == Peca.VAZIO) {
			return false;
		}
		
		return true;
	}

	public void zerarTabuleiro() {
		for (int x = 0, len = this.tabuleiro.length; x < len; x++) {
			for (int y = 0, lenY = this.tabuleiro[x].length; y < lenY; y++) {
				this.tabuleiro[x][y] = Peca.VAZIO;
			}
		}
	}

	public int validarResultado() {
//	    public static final int UNCLEAR = 0;
//	    public static final int HUMAN_WIN = 1;
//	    public static final int DRAW = 2;
//	    public static final int COMPUTER_WIN = 3;
		Peca vencedor = temGanhadorOuEmpate();
		
		if (vencedor == Peca.COMPUTADOR) {
			return 3;
		} else if (vencedor == Peca.JOGADOR) {
			return 1;
		} else if (vencedor == null) {
			return 2;
		}
	    
		return 0; // tabuleiro não completo
	}

	public List<int[]> proximasJogadas() {
		List<int[]> proximos = new ArrayList<int[]>();
		
		for (int x = 0; x < tabuleiro.length; x++) {
			for (int y = 0; y < tabuleiro[x].length; y++) {
				if (tabuleiro[x][y] == Peca.VAZIO) {
					proximos.add(new int[] { x, y });
				}
			}
		}
		
		return proximos;
	}
	
	/**
	 * Metodo que retorna o vencedor ou empate.
	 * @return
	 */
	public Peca getVencedor() {
		Peca vencedor = temGanhadorOuEmpate();
		
		if (vencedor == Peca.VAZIO) {
			// throw new IllegalArgumentException("");
		}
		
		return vencedor;
	}
	
	private Peca temGanhadorOuEmpate() {
		Peca vencedor = null; // Peca.VAZIO;
		
		for (int x = 0; x < tabuleiro.length; x++) {
            if (temGanhador(tabuleiro[x][0], tabuleiro[x][1], tabuleiro[x][2])) { //vertical win
                vencedor = tabuleiro[x][0];
            } else if (temGanhador(tabuleiro[0][x], tabuleiro[1][x], tabuleiro[2][x])) { //horizontal win
                vencedor = tabuleiro[0][x];
            } else if (temGanhador(tabuleiro[0][0], tabuleiro[1][1], tabuleiro[2][2])) { //diagonal win
                vencedor = tabuleiro[0][0];
            } else if (temGanhador(tabuleiro[0][2], tabuleiro[1][1], tabuleiro[2][0])) { //diagonal win
                vencedor = tabuleiro[0][2];
            }
		}
		
		if (vencedor == null && temVazio()) {
			return Peca.VAZIO;
		}
		
		return vencedor;
	}

	private boolean temGanhador(Peca p1, Peca p2, Peca p3) {
		return (p1 == p2) && (p2 == p3) && (p3 != Peca.VAZIO);
	}
	
	private boolean temVazio() {
		for (int x = 0; x < tabuleiro.length; x++) {
			for (int y = 0; y < tabuleiro[x].length; y++) {
				if (tabuleiro[x][y] == Peca.VAZIO) {
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		char c;
		
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				c = '-';
				
				if (this.tabuleiro[i][j] == Peca.COMPUTADOR)
					c = 'O';
				else if (this.tabuleiro[i][j] == Peca.JOGADOR)
					c = 'X';
				
				str.append("[" + c + "]");
			}
			
			str.append("\n");
		}
		
		return str.toString();
	}
}
