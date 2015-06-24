package minimax;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import classes.Celula;

public class Tabuleiro {

	private Celula[][] tabuleiro;
	private Peca jogadorAtual = Peca.JOGADOR;
	private Stack<int[]> ultimaJogada = new Stack<int[]>();
	private ColorSensor color;
	
	public Tabuleiro() {
		color = new ColorSensor(SensorPort.S1);
		tabuleiro = new Celula[3][3];
		zerarTabuleiro();

		tabuleiro[0][0].setCaminho(new char[] {'f','e','e','e','x','d','d','d','t'});
		tabuleiro[0][1].setCaminho(new char[] {'f','e','e','e','e','x','d','d','d','d','t'});
		tabuleiro[0][2].setCaminho(new char[] {'f','d','d','d','x','e','e','e','t'});
		tabuleiro[1][0].setCaminho(new char[] {'f','e','e','x','d','d','t'});
		tabuleiro[1][1].setCaminho(new char[] {'f','f','x','t','t'});
		tabuleiro[1][2].setCaminho(new char[] {'f','d','d','x','e','e','t'});
		tabuleiro[2][0].setCaminho(new char[] {'f','e','x','d','t'});
		tabuleiro[2][1].setCaminho(new char[] {'f','x','t'});
		tabuleiro[2][2].setCaminho(new char[] {'f','d','x','e','t'});
	}
	
	public Celula[][] getTabuleiro() {
		return tabuleiro;
	}
	public void setTabuleiro(Celula[][] tabuleiro) {
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
		if (tabuleiro[x][y].getValor() != Peca.VAZIO)
			throw new IllegalArgumentException("Jogada não permitida!");
		
		tabuleiro[x][y].setValor(jogadorAtual);
		
		proximoJogador();
		setUlimaJogada(x, y);
	}
	
	public void desfazerUltimaJogada() throws IllegalArgumentException {
		if (this.ultimaJogada.isEmpty())
			throw new IllegalArgumentException("Nenhuma jogada foi efetuada no tabuleiro");
		
		int[] ultimo = this.ultimaJogada.pop();
		
		this.tabuleiro[ultimo[0]][ultimo[1]].setValor(Peca.VAZIO);
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
				this.tabuleiro[x][y] = new Celula();
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
				if (tabuleiro[x][y].getValor() == Peca.VAZIO) {
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
            if (temGanhador(tabuleiro[x][0].getValor(), tabuleiro[x][1].getValor(), tabuleiro[x][2].getValor())) { //vertical win
                vencedor = tabuleiro[x][0].getValor();
            } else if (temGanhador(tabuleiro[0][x].getValor(), tabuleiro[1][x].getValor(), tabuleiro[2][x].getValor())) { //horizontal win
                vencedor = tabuleiro[0][x].getValor();
            } else if (temGanhador(tabuleiro[0][0].getValor(), tabuleiro[1][1].getValor(), tabuleiro[2][2].getValor())) { //diagonal win
                vencedor = tabuleiro[0][0].getValor();
            } else if (temGanhador(tabuleiro[0][2].getValor(), tabuleiro[1][1].getValor(), tabuleiro[2][0].getValor())) { //diagonal win
                vencedor = tabuleiro[0][2].getValor();
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
				if (tabuleiro[x][y].getValor() == Peca.VAZIO) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void lerTabuleiro() {
		
		mover('f');
			
		mover('d');
		tabuleiro[2][2].setValor(Peca.getByValor(color.getColorID())); //ler
		mover('d');
		tabuleiro[1][2].setValor(Peca.getByValor(color.getColorID())); //ler
		mover('d');
		tabuleiro[0][2].setValor(Peca.getByValor(color.getColorID())); //ler
		mover('d');
		tabuleiro[0][1].setValor(Peca.getByValor(color.getColorID())); //ler
		mover('d');
		tabuleiro[0][0].setValor(Peca.getByValor(color.getColorID())); //ler
		mover('d');
		tabuleiro[1][0].setValor(Peca.getByValor(color.getColorID())); //ler
		mover('d');
		tabuleiro[2][0].setValor(Peca.getByValor(color.getColorID())); //ler
		mover('d');
		tabuleiro[2][1].setValor(Peca.getByValor(color.getColorID())); //ler
		mover('f');
		tabuleiro[1][1].setValor(Peca.getByValor(color.getColorID())); //ler
		mover('t');
		mover('t');
	}
	
	public void mover(char direcao) {
		switch (direcao) {
		case 'f':
			//Mover pra frente
			frente();
			break;
		case 't':
			//mover pra trás
			pratras();
			break;
		case 'e':
			//Mover pra esquerda
			Motor.C.rotate(-45);
			break;
		case 'd':
			//mover pra direita
			Motor.C.rotate(45);
			break;
		case 'x':
			//soltar a bolinha (dar um giro de 360 graus no motor)
			frente();
			Motor.A.rotate(360);
			pratras();
			break;
		}
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		char c;
		
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				c = '-';
				
				if (this.tabuleiro[i][j].getValor() == Peca.COMPUTADOR)
					c = 'O';
				else if (this.tabuleiro[i][j].getValor() == Peca.JOGADOR)
					c = 'X';
				
				str.append("[" + c + "]");
			}
			
			str.append("\n");
		}
		
		return str.toString();
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
