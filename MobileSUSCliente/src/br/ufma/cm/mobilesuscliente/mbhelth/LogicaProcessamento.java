package br.ufma.cm.mobilesuscliente.mbhelth;

import java.util.Scanner;

import br.ufma.cm.mobilesuscliente.MainActivity;
import br.ufma.cm.mobilesuscliente.entidades.Chamado;


public class LogicaProcessamento {
	public void enviarChamado(Chamado chamado) {
		String texto = "*";
		texto += "Chamado\n";
		texto += chamado.getId() + "\n";
		texto += chamado.getLatitude() + "\n";
		texto += chamado.getLongitude() + "\n";
		texto += chamado.getDescricao() + "\n";
		texto += chamado.getStatus() + "\n";
		texto += chamado.getRelatorio() + "\n";

		TesteChat.enviar(ConstantesMOBHA.central,texto);
	
	}
	
	public Chamado receberChamado(String chamado) {
		Scanner s=new Scanner(chamado);
		if(s.nextLine().startsWith("*Chamado")){
			Chamado c = new Chamado();
			c.setId(s.nextLine());
			c.setLatitude(s.nextLine());
			c.setLongitude(s.nextLine());
			c.setDescricao(s.nextLine());
			c.setStatus(s.nextLine());
			c.setRelatorio(s.nextLine());
			s.close();
			return c;
		}
		return null;
	}

	public void processarRecebimentoChamado(String chamado) {
		Chamado c=receberChamado(chamado);
		System.out.println("Processar chamado:"+c);
		if(c!=null){
			MainActivity.chamado=c;
		}
	}

}
