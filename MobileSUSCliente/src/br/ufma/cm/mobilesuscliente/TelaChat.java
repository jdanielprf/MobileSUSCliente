package br.ufma.cm.mobilesuscliente;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import br.ufma.cm.mobilesuscliente.controller.Controller;
import br.ufma.cm.mobilesuscliente.mbhelth.ConstantesMOBHA;
import br.ufma.cm.mobilesuscliente.mbhelth.InterfaceChat;
import br.ufma.cm.mobilesuscliente.mbhelth.TesteChat;

public class TelaChat extends Activity implements InterfaceChat {
	private Button enviar;
	private TextView history;
	private EditText message;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_chat);

		init();
		TesteChat.registrar(ConstantesMOBHA.central, this);
	}

	private void init() {
		history = (TextView) findViewById(R.id.txtChatHistorico);
		history.setMovementMethod(new ScrollingMovementMethod());
		
		message = (EditText) findViewById(R.id.txtChatMsg);
		message.setMovementMethod(new ScrollingMovementMethod());

		enviar = (Button) findViewById(R.id.btnChatEnviar);
		enviar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				sendMsg();
			}
		});
	}

	private void sendMsg() {
		String msg = message.getText().toString();
		Controller.getChat().addMessage("Eu", msg);

		String msgHistory = Controller.getChat().history();

		history.setText(msgHistory);
		message.setText(new String());
		TesteChat.enviar(msg);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.tela_chat, menu);
		return true;
	}

	@Override
	public synchronized void receberMsg(String id, final String texto, String data) {
	
		   TelaChat.this.runOnUiThread(new Runnable(){  
			    @Override  
			    public void run() {  
			    	Controller.getChat().addMessage("Central", texto);
					String msgHistory = Controller.getChat().history();
					history = (TextView) findViewById(R.id.txtChatHistorico);
					if (history != null)
						history.setText(msgHistory);
					
			    }  
		   });
	}

}
