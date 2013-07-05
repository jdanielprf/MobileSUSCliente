package br.ufma.cm.mobilesuscliente.mbhelth;

import java.io.InputStream;
import java.util.HashMap;

import br.ufma.cm.mobilesuscliente.MainActivity;
import br.ufma.lsd.mbhealthnet.communication.ddstopics.Chat;
import br.ufma.lsd.mbhealthnet.communication.ddstopics.GenericInformation;
import br.ufma.lsd.mbhealthnet.communication.pubsub.PubSubTopicListener;

public class TesteChat {

	private static br.ufma.lsd.mbhealthnet.android.mobha.chat.MOBHAChat contService;
	private static HashMap<String, InterfaceChat> lista = new HashMap<String, InterfaceChat>();

	private static void recever(Object o) {

		System.out.println(o);
		if (o instanceof GenericInformation) {
			GenericInformation g = (GenericInformation) o;
			System.out.println(g.message);
		}else if (o instanceof Chat) {
			Chat g = (Chat) o;

			System.out.println(g.fromUserName);
			System.out.println(g.message);

			if (g.message.startsWith("*Chamado")) {
				MainActivity.logica.processarRecebimentoChamado(g.message);
			} else {
				InterfaceChat cal = lista.get(g.fromUserName);
				if (cal != null) {
					cal.receberMsg(g.fromUserName, g.message, null);
				}
			}
			
		}
	}

	public static void init(InputStream inp) {
		System.out.println("!!!!!!!!!!!!" + ConstantesMOBHA.idUM);
		if (contService != null) {
			return;
		}
		try {

			contService = new br.ufma.lsd.mbhealthnet.android.mobha.chat.MOBHAChat(
					ConstantesMOBHA.idUM, inp);
			contService.registerSubTopicListener(new PubSubTopicListener() {
				@Override
				public void processTopic(Object o) {
					recever(o);
				}
			});
			socilitarChamado();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("fim");
	}

	public static void enviar(String dados, String para) {
		if (contService == null) {
			return;
		}
		try {
			Chat c = new Chat();
			c.message = dados;
			c.fromUserName = ConstantesMOBHA.idUM;
			c.toUserName = para;

			contService.publishChat(c);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void enviar(String dados) {
		enviar(dados, ConstantesMOBHA.central);
	}

	public static void registrar(String u, InterfaceChat c) {
		lista.put(u, c);
	}
	
	public static void socilitarChamado() {
		enviar("*SolicitacaoChamado");
	}
}
