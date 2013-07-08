package br.ufma.cm.mobilesuscliente;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import br.ufma.cm.mobilesuscliente.controller.GPSTracker;
import br.ufma.cm.mobilesuscliente.entidades.Chamado;
import br.ufma.cm.mobilesuscliente.mbhelth.ConstantesMOBHA;
import br.ufma.cm.mobilesuscliente.mbhelth.LogicaProcessamento;
import br.ufma.cm.mobilesuscliente.mbhelth.Rota;
import br.ufma.cm.mobilesuscliente.mbhelth.TesteChat;
import br.ufma.cm.mobilesuscliente.mbhelth.TesteContexto;

public class MainActivity extends Activity {
	private View chat;
	private View ocorrencia;

	private View config;
	private View rota;
	private static Chamado chamado;
	public static LogicaProcessamento logica = new LogicaProcessamento();

	public static Chamado getChamado(){
		return chamado;
	}
	public static void setChamado(Chamado c){
		chamado=c;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ConstantesMOBHA.lerID();
		init();

		try {
			TesteChat.init(settingsProperties());
			TesteContexto.init(getApplicationContext(), settingsProperties());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onStart() {
		checkGPS();
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void init() {
		chat = findViewById(R.id.btnMainChat);
		chat.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initChat();
			}
		});

		ocorrencia = findViewById(R.id.btnMainEvent);
		ocorrencia.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initOcorrencia();
			}
		});

		config = findViewById(R.id.btnMainConfig);
		config.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initConfig();
			}
		});

		rota = findViewById(R.id.btnMainRota);
		rota.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initRota();
			}
		});

	}

	protected void initRota() {
		if (chamado != null) {
			GPSTracker gps = new GPSTracker(this);

			if (gps.canGetLocation()) {

				Rota.latOrigem = gps.getLatitude();
				Rota.latDestino = gps.getLongitude();
				
				Rota.latDestino = Float.parseFloat(chamado.getLatitude());
				Rota.logDestino = Float.parseFloat(chamado.getLongitude());
				Rota.iniciar();
			}else{
				System.out.println("erro na localização");
			}
		}else{
			System.out.println("nao existe chamado");
		}

	}

	protected void initChat() {
		Intent i = new Intent(this, TelaChat.class);
		startActivity(i);
	}

	protected void initConfig() {
		Intent i = new Intent(this, TelaConfig.class);
		startActivity(i);
	}

	protected void initOcorrencia() {
		Intent i = new Intent(this, TelaOcorrencia.class);
		startActivity(i);
	}

	private InputStream settingsProperties() throws IOException {

		return getApplicationContext().getAssets().open("settings.properties");
		// return new ByteArrayInputStream(
		// "gateway_address=lsd.ufma.br\ngateway_port=50000\n"
		// .getBytes("UTF-8"));
	}

	private void checkGPS() {
		LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		boolean isGPS = locationManager
				.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (!isGPS) {
			startActivityForResult(new Intent(
					android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),
					0);
		}
	}

}
