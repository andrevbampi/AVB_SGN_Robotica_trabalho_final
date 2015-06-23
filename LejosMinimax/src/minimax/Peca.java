package minimax;

/**
 * Enumerador com as peças que podem ser colocadas no tabuleiro
 * Enumerador básico, para jogos mais complexos como xadrez é necessário mais estruturas de controle
 * 
 * @author Silvio Gonçalves Neto
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
