import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;


public class Main {
	
	private void colocarBolinha(Celula[][] tabuleiro, int x, int y) {
		for (int i = 0; i < tabuleiro[x][y].getCaminho().length; i++) {
			mover(tabuleiro[x][y].getCaminho()[i]);
		}
	}
	
	private void mover(char direcao) {
		switch (direcao) {
		case 'f':
			//Mover pra frente
			break;
		case 't':
			//mover pra trás
			break;
		case 'e':
			//Mover pra esquerda
			break;
		case 'd':
			//mover pra direita
			break;
		case 'x':
			//soltar a bolinha (dar um giro de 360 graus no motor)
			break;
		}
	}
	
	private void lerTabuleiro(Celula[][] tabuleiro, boolean primeiraVez) {
		if(!primeiraVez) {
			mover('f');
		}
		mover('d');
		tabuleiro[2][2].setValor(0); //ler
		mover('d');
		tabuleiro[1][2].setValor(0); //ler
		mover('d');
		tabuleiro[0][2].setValor(0); //ler
		mover('d');
		tabuleiro[0][1].setValor(0); //ler
		mover('d');
		tabuleiro[0][0].setValor(0); //ler
		mover('d');
		tabuleiro[1][0].setValor(0); //ler
		mover('d');
		tabuleiro[2][0].setValor(0); //ler
		mover('d');
		tabuleiro[2][1].setValor(0); //ler
		mover('f');
		tabuleiro[1][1].setValor(0); //ler
		mover('t');
		mover('t');
	}

	public static void main(String[] args) throws InterruptedException {
		
		Celula[][] tabuleiro = new Celula[2][2];
		tabuleiro[0][0].setCaminho(new char[] {'f','e','e','e','x','d','d','d','t'});
		tabuleiro[0][1].setCaminho(new char[] {'f','f','f','x','t','t','t'});
		tabuleiro[0][2].setCaminho(new char[] {'f','d','d','d','x','e','e','e','t'});
		tabuleiro[1][0].setCaminho(new char[] {'f','e','e','x','d','d','t'});
		tabuleiro[1][1].setCaminho(new char[] {'f','f','x','t','t'});
		tabuleiro[1][2].setCaminho(new char[] {'f','d','d','x','e','e','t'});
		tabuleiro[2][0].setCaminho(new char[] {'f','e','x','d','t'});
		tabuleiro[2][1].setCaminho(new char[] {'f','x','t'});
		tabuleiro[2][2].setCaminho(new char[] {'f','d','x','e','t'});

		while (!Button.ENTER.isPressed());
		
		ColorSensor color = new ColorSensor(SensorPort.S1);
		
//		Motor.A.rotate(360);
		
		Motor.B.rotate(65);
		
		Thread.sleep(3000);
		
		Motor.B.rotate(-65);

		Thread.sleep(3000);
		
		int giro = 10;
		int giroMotorB = 5;
		
		while (color.getColorID() != 0) {
			Motor.B.rotate(giro);
			
			giroMotorB += giro;
		}
		
		System.out.println("giro: " + giroMotorB);

		while (!Button.ENTER.isPressed());
	}

}
