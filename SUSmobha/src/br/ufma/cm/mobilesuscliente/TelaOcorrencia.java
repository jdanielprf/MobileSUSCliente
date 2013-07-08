package br.ufma.cm.mobilesuscliente;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import br.ufma.cm.mobilesuscliente.entidades.Chamado;
import br.ufma.cm.mobilesuscliente.mbhelth.ConstantesMOBHA;
import br.ufma.cm.mobilesuscliente.mbhelth.TesteChat;
import br.ufma.cm.mobilesuscliente.mbhelth.TesteUpload;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TelaOcorrencia extends Activity {
	final int CAMERA_CAPTURE = 1;
	private static final int CAMERA_REQUEST = 1888;
	private Button cam;
	private Button mapa;
	private Button finalizar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_ocorrencia);

		cam = (Button) findViewById(R.id.btnEventRegist);
		cam.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				register();

			}
		});
		mapa = (Button) findViewById(R.id.btnOcorrenciaMap);
		mapa.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				carregarMapa();

			}
		});

		finalizar = (Button) findViewById(R.id.btnOcorrenciaFinalizar);
		finalizar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finalizar();

			}
		});
		carregar();
		TesteChat.socilitarChamado();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tela_ocorrencia, menu);
		return true;
	}

	

	protected void carregarMapa() {
		System.out.println("Mapa");
		Intent i = new Intent(this, TelaMaps.class);
		startActivity(i);
	}

	protected void register() {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

		startActivityForResult(cameraIntent, CAMERA_REQUEST);

	}

	protected void carregar() {
		Chamado c = MainActivity.getChamado();
		System.out.println("carregando chamado:" + c);
		if (c == null) {
			return;
		}

		TextView localizacao = (TextView) findViewById(R.id.txtLOorrenciaLocalizacao);
		TextView descricao = (TextView) findViewById(R.id.txtOcorrenciaDescricao);
		TextView status = (TextView) findViewById(R.id.txtOcorrenciaSituacao);
		System.out.println("Descricacao:" + c.getDescricao());
		descricao.setText(c.getDescricao());
		localizacao.setText("Latitude:" + c.getLatitude() + " Longitude:"
				+ c.getLongitude());
		
		if(c.getStatus()==null){
			status.setText(Chamado.STATUS_EM_ATENDIMENTO);
		}else{
			status.setText(c.getStatus());
		}
	}

	protected void finalizar() {
		System.out.println("Finalizar");
		TextView relatorio = (TextView) findViewById(R.id.txtOcorrenciaRelatorio);
		System.out.println("relatorio:"+relatorio.getText().toString());
		MainActivity.getChamado().setRelatorio(relatorio.getText().toString());
		MainActivity.getChamado().setStatus(Chamado.STATUS_FECHADO);
		
		MainActivity.logica.enviarChamado(MainActivity.getChamado());
		
		
	//	Intent i = new Intent(this, MainActivity.class);
	//	startActivity(i);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (MainActivity.getChamado() == null) {
				return;
			}
			try {
				System.out.println("Salvando foto");
				TesteUpload.init(settingsProperties());
				System.out.println("result");
				Bitmap photo = (Bitmap) data.getExtras().get("data");
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
				byte[] byteArray = stream.toByteArray();

				TesteUpload.upload(ConstantesMOBHA.idUM, ""
						+ MainActivity.getChamado().getId(),
						System.currentTimeMillis() + ".png", byteArray);

				System.out.println("Foto enviada");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private InputStream settingsProperties() throws IOException {
		return getApplicationContext().getAssets().open("settings.properties");
	}
}
