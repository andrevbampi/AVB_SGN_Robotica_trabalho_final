package classes;

import minimax.Peca;

public class Celula {
	private Peca valor = Peca.VAZIO;
	private char[] caminho;

	public Peca getValor() {
		return valor;
	}

	public void setValor(Peca valor) {
		this.valor = valor;
	}

	public char[] getCaminho() {
		return caminho;
	}

	public void setCaminho(char[] caminho) {
		this.caminho = caminho;
	}
}