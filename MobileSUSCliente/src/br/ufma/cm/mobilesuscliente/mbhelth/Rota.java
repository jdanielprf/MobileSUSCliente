package br.ufma.cm.mobilesuscliente.mbhelth;

import br.ufma.cm.mobilesuscliente.MainActivity;

public class Rota {
	/*
	 * Parametros de latitude e longitude da origem e do detino
	 * Retorna tru quando a Unidade movel chegar no destino
	 * vcs vao receber como string trasforme em numero
	 * A funcção é cahamada a cada 0,5 segundos
	 */
	public static boolean rota(String latOrigem,String logOrigem,String latDestino,String logDestino){
		
		
		return true;
	}
	
	
	public static boolean iniciar(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				String latOrigem = null;
				String logOrigem = null;
				rota(latOrigem, logOrigem, MainActivity.chamado.getLatitude(), MainActivity.chamado.getLongitude());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		return true;
	}
	
}
