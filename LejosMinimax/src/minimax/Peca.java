package minimax;

/**
 * Enumerador com as pe�as que podem ser colocadas no tabuleiro
 * Enumerador b�sico, para jogos mais complexos como xadrez � necess�rio mais estruturas de controle
 * 
 * @author Silvio Gon�alves Neto
 */
public enum Peca {

	VAZIO(0),
	
	JOGADOR(1),
	
	COMPUTADOR(2);
	
	public int valorPeca;
	Peca(int valor) {
		this.valorPeca = valor;
	}
	
	public static Peca getByValor(int id) {
		for (Peca p : values()) {
			if (p.valorPeca == id) {
				return p;
			}
		}
		
		return null;
	}
}
