package view;
import java.util.concurrent.Semaphore;

import controller.EscuderiaThread;

public class Formula1 {
	public static void main(String[] args) {
		Semaphore semaforo=new Semaphore(5);
		String []escuderias= {"Red Bull","Mercedes","Ferrari","Willians","McLaren","Aston Martin","Alpha Tauri"};
		
		for(int i=0;i<7;i++) {
			Thread pista=new EscuderiaThread(semaforo,escuderias[i]);
			pista.start();
		}
	}
}
