package br.ufma.cm.mobilesuscliente;

import br.ufma.cm.mobilesuscliente.controller.Controller;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TelaChat extends Activity {
	Button enviar;
	private TextView history;
	private EditText message;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_chat);
		init();
	}

	private void init() {
		history = (TextView) findViewById(R.id.txtChatHistorico);
		message = (EditText) findViewById(R.id.txtChatMsg);
		
		enviar = (Button) findViewById(R.id.btnChatEnviar);
		enviar.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 sendMsg();
             }
         });
	}
	private void sendMsg(){
		String msg=message.getText().toString();
		Controller.getChat().addMessage("Eu", msg);
		
		String msgHistory=Controller.getChat().history();
		
		history.setText(new String(msgHistory));
		message.setText(new String());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_chat, menu);
		return true;
	}

}
