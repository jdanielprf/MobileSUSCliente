package br.ufma.cm.mobilesuscliente;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import br.ufma.cm.mobilesuscliente.entidades.Chamado;
import br.ufma.cm.mobilesuscliente.mbhelth.LogicaProcessamento;
import br.ufma.cm.mobilesuscliente.mbhelth.TesteChat;
import br.ufma.cm.mobilesuscliente.mbhelth.TesteContexto;

public class MainActivity extends Activity {

	private View chat;
	private View ocorrencia;
	private View cam;
	public static Chamado chamado;
	public static LogicaProcessamento logica=new LogicaProcessamento();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		setContentView(R.layout.activity_main);
		init();
		
		
		try {
			TesteChat.init(settingsProperties());
			TesteContexto.init( getApplicationContext(),settingsProperties());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void init() {
		chat=findViewById(R.id.btnMainChat);
		chat.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initChat();
			}
		});
		
		ocorrencia=findViewById(R.id.btnMainEvent);
		ocorrencia.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initOcorrencia();
			}
		});
		
		cam=findViewById(R.id.btnMainImage);
		cam.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				capturarImagem();
			}
		});
		
	
		
	}

	protected void capturarImagem() {
	
	}

	protected void initChat() {
		Intent i = new Intent(this, TelaChat.class);
		startActivity(i);
	}
	
	protected void initOcorrencia() {
		Intent i = new Intent(this, TelaOcorrencia.class);
		startActivity(i);
	}

	protected void teste() {

		try {
			TesteContexto.init( getApplicationContext(),settingsProperties());

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private  InputStream settingsProperties()
			throws IOException {
		return getApplicationContext().getAssets().open("settings.properties");
	}

}
