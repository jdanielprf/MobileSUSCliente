package br.ufma.cm.mobilesuscliente.mbhelth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import br.ufma.lsd.mbhealthnet.android.mobha.content.MOBHAContentImpl;
import br.ufma.lsd.mbhealthnet.communication.ddstopics.ContentFile;
import br.ufma.lsd.mbhealthnet.communication.ddstopics.ContentMetaData;
import br.ufma.lsd.mbhealthnet.communication.ddstopics.ContentUploadRequest;
import br.ufma.lsd.mbhealthnet.communication.ddstopics.GenericInformation;
import br.ufma.lsd.mbhealthnet.communication.exception.DomainParticipantNotCreatedException;
import br.ufma.lsd.mbhealthnet.communication.pubsub.PubSubTopicListener;


public class TesteUpload {
	private static String userCentral="felipe";
	private static MOBHAContentImpl contService;


	public static void init(InputStream inp) {
		System.out.println("!!!!!!!!!!!!");
		
		try {
		
			contService=MOBHAContentImpl.getInstance(inp, userCentral);
		
			System.out.println("2");
			contService.registerSubTopicListener(new PubSubTopicListener() {			
				@Override
				public void processTopic(Object o) {
					System.out.println("processando...");
					System.out.println(o);
					if(o instanceof GenericInformation){
						GenericInformation g=(GenericInformation)o;
						System.out.println(g.message);
					}
				}
			});
			System.out.println("3");
		}catch (FileNotFoundException e) {
			System.out.println("arquivo nao encontrado");
			e.printStackTrace();
		}catch (DomainParticipantNotCreatedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	
	
	public static void upload(String id,String ChamadoId,String nome,byte[] dados){
		System.out.println("upload");
		ContentUploadRequest cont=new ContentUploadRequest();
		cont.fromUserName=id;
		cont.contentFile=new ContentFile(dados);
		cont.contentMetaData=new ContentMetaData();
		cont.contentMetaData.contentName=nome;
		cont.contentMetaData.path="/home/gateway/e1/"+ChamadoId+"/";
		cont.contentMetaData.owner=userCentral;
		cont.contentMetaData.mimeType="txt";
		try{
			contService.publishUploadContent(cont);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	


}
