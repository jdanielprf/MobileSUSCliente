package br.ufma.cm.mobilesuscliente;

import br.ufma.cm.mobilesuscliente.controller.Controller;
import br.ufma.cm.mobilesuscliente.mbhelth.ConstantesMOBHA;
import br.ufma.cm.mobilesuscliente.mbhelth.InterfaceChat;
import br.ufma.cm.mobilesuscliente.mbhelth.TesteChat;
import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tela_chat, menu);
		return true;
	}
	private void init() {
		history = (TextView) findViewById(R.id.sadfsadf);
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
	public synchronized void receberMsg(String id, final String texto, String data) {
	
		   TelaChat.this.runOnUiThread(new Runnable(){  
			    @Override  
			    public void run() {  
			    	Controller.getChat().addMessage("Central", texto);
					String msgHistory = Controller.getChat().history();
					history = (TextView) findViewById(R.id.sadfsadf);
					if (history != null)
						history.setText(msgHistory);
					
			    }  
		   });
	}

}
