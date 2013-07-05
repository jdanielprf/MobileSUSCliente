package br.ufma.cm.mobilesuscliente.mbhelth;

import java.io.InputStream;

import android.os.RemoteException;
import br.ufma.lsd.mbhealthnet.android.mobha.context.MOBHAContext;
import br.ufma.lsd.mbhealthnet.communication.ddstopics.ContextInformationSubscribe;
import br.ufma.lsd.mbhealthnet.communication.ddstopics.ContextInformationSubscribeResponse;
import br.ufma.lsd.mbhealthnet.communication.exception.DomainParticipantNotCreatedException;
import br.ufma.lsd.mbhealthnet.communication.exception.TopicNotRegisteredException;
import br.ufma.lsd.mbhealthnet.communication.pubsub.PubSubTopicListener;

public class TesteContexto {

	private static String idUM = ConstantesMOBHA.idUM;

	private static MOBHAContext mobhaContext;

	public static void init(android.content.Context contexto, InputStream inp) {
		System.out.println("inicio contexto");
		try {
			mobhaContext = MOBHAContext.getInstance(
					inp,contexto, idUM);
			
			mobhaContext.registerSubTopicListener(new PubSubTopicListener() {

				@Override
				public void processTopic(Object o) {
					System.out.println("processando contexto...");
					System.out.println(o);
					if (o instanceof ContextInformationSubscribe) {
						ContextInformationSubscribe subscribe=(ContextInformationSubscribe)o;
						acceptRequest(true, subscribe);
					}
				}
			});

			System.out.println("2");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(10*1000);
					//mobhaContext.addContextProviderRepository("http://lsd.ufma.br/~dejailson/MobileHealthNet/contextProviders/ContextProviders.apk", "http://lsd.ufma.br/~dejailson/MobileHealthNet/contextProviders/ContextProviders.apk.desc");
					
					mobhaContext.addContextProviderRepository("http://200.137.131.192/~dejailson/MobileHealthNet/contextProviders/ContextProviders.apk", "http://200.137.131.192/~dejailson/MobileHealthNet/contextProviders/ContextProviders.apk.desc");
					
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		
		System.out.println("fim"); 
	}

	public static void acceptRequest(boolean accept,ContextInformationSubscribe subscribe){		
		ContextInformationSubscribeResponse response=new ContextInformationSubscribeResponse();
		boolean[] accepts={accept};
		response.accept = accepts;
		response.requestedUserName=subscribe.requestedUserName;
		response.requestorUserName=subscribe.requestorUserName;
		response.contextInformationInterest = subscribe.contextInformationInterest;
		response.contextInformationKey = new byte[1024];
		try {
			mobhaContext.publishContextInformationSubscribeResponse(response);
		} catch (RemoteException e) {
			
			e.printStackTrace();
		} catch (TopicNotRegisteredException e) {
		
			e.printStackTrace();
		} catch (DomainParticipantNotCreatedException e) {
			
			e.printStackTrace();
		}
	}
}

