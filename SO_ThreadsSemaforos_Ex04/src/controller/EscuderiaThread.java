package controller;

import java.util.concurrent.Semaphore;

public class EscuderiaThread extends Thread {
	
	private Semaphore semaforo;
	
	private String piloto;
	static int  numCorredores=14;
	private String escuderia;
	private int pilotosEscuderia; 
	private static String []grid=new String[14];
	private static int []tempo=new int[14];
	private static int i=0; 
	
	
	public EscuderiaThread(Semaphore semaforo, String escuderia) {
		this.semaforo=semaforo;
		this.escuderia=escuderia;
	}
	
	@Override
	public void run() {
		for(pilotosEscuderia=2;pilotosEscuderia>0;pilotosEscuderia--) {
			pilotos();
			if(numCorredores>0) {
				try {
					semaforo.acquire();
					pista();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					semaforo.release();
					numCorredores--;
				}
			}
			if(numCorredores==0) {
				ordenar();
				mostrar();
			}
		}
	}

	private void pista() {
		// TODO Auto-generated method stub
		int menorTempo=500;
		int tempoVolta;
		for(int voltas=3;voltas>0;voltas--) {
			tempoVolta=(int)(Math.random()*201)+100; //  112s - 220s
			System.out.println(piloto+"; tempo: "+tempoVolta+"s");
			if(tempoVolta<menorTempo) {
				menorTempo=tempoVolta;
			}
		}
		
//		i++;
		grid[i]=piloto;
		tempo[i]=menorTempo;
		i++;
		dormir();
	}

	private void dormir() {
		// TODO Auto-generated method stub
		try {
			sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void ordenar() {
		// TODO Auto-generated method stub
		String gridTroca;
		int tempoTroca;
		
		for(int i=0;i<13;i++) {
			if(tempo[i]>tempo[i+1]) {
				
				gridTroca=grid[i];
				grid[i]=grid[i+1];
				grid[i+1]=gridTroca;
				
				tempoTroca=tempo[i];
				tempo[i]=tempo[i+1];
				tempo[i+1]=tempoTroca;
				
				i=-1;
			}
		}
	}

	private void mostrar() {
		System.out.println("------------------------------------------");
		System.out.println("Resultado do grid: ");
		for(int i=1;i<=14;i++) {
			System.out.println(grid[i-1]+"-> "+i+"a largar; menor tempo: "+tempo[i-1] );
		}
		System.out.println("------------------------------------------");
	}

	
	private void pilotos() {
		// TODO Auto-generated method stub
		switch(escuderia) {
			case "Red Bull":
				if(pilotosEscuderia==2) {
					piloto="Max Verstappen"+"("+escuderia+")";
				}else{
					piloto="Sergio Pérez"+"("+escuderia+")";
				}
			break;
			case "Mercedes":
				if(pilotosEscuderia==2) {
					piloto="Lewis Hamilton"+"("+escuderia+")";
				}else{
					piloto="George Russell"+"("+escuderia+")";
				}
			break;
			case "Ferrari":
				if(pilotosEscuderia==2) {
					piloto="Charles Leclerc"+"("+escuderia+")";
				}else{
					piloto="Carlos Sainz"+"("+escuderia+")";
				}
			break;
			case "Willians":
				if(pilotosEscuderia==2) {
					piloto="Alex Albon"+"("+escuderia+")";
				}else{
					piloto="Sargento Logan"+"("+escuderia+")";
				}
			break;
			case "McLaren":
				if(pilotosEscuderia==2) {
					piloto="Lando Norris"+"("+escuderia+")";
				}else{
					piloto="Oscar Piastri"+"("+escuderia+")";
				}
			break;
			case "Aston Martin":
				if(pilotosEscuderia==2) {
					piloto="Fernando Alonso"+"("+escuderia+")";
				}else{
					piloto="Lance Stroll"+"("+escuderia+")";
				}
			break;
			case "Alpha Tauri":
				if(pilotosEscuderia==2) {
					piloto="Nyck Devries"+"("+escuderia+")";
				}else{
					piloto="Yuki Tsunoda"+"("+escuderia+")";
				}
			break;
		}
	}
}

