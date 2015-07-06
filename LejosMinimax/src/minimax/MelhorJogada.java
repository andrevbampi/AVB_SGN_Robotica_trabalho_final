package minimax;

/**
 * Classe que guarda a melhor jogada obtida pelo MiniMax
 * 
 * @author Silvio
 */
public class MelhorJogada {

	private int x;
	private int y;
	private int resultado;

	public MelhorJogada() {
	}

	public MelhorJogada(int x, int y, int resultado) {
		this.x = x;
		this.y = y;
		this.resultado = resultado;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}
}
