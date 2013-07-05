package br.ufma.cm.mobilesuscliente;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.ufma.cm.mobilesuscliente.entidades.Chamado;
import br.ufma.cm.mobilesuscliente.mbhelth.ConstrantesteMOBHA;
import br.ufma.cm.mobilesuscliente.mbhelth.TesteChat;
import br.ufma.cm.mobilesuscliente.mbhelth.TesteUpload;

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
		init();
		carregar();
		TesteChat.socilitarChamado();
	}

	private void init() {
		cam = (Button) findViewById(R.id.btnEventRegist);
		cam.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				register();

			}
		});
		mapa = (Button) findViewById(R.id.btnOcorrenciaMaps);
		mapa.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				carregarMapa();

			}
		});
		
		finalizar = (Button) findViewById(R.id.btnOcorrenciaMaps);
		finalizar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finalizar();

			}
		});
	}

	protected void carregarMapa() {
		Intent i = new Intent(this, TelaMaps.class);
		startActivity(i);
	}

	protected void register() {
		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

		startActivityForResult(cameraIntent, CAMERA_REQUEST);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.tela_ocorrencia, menu);
		return true;
	}

	protected void carregar() {
		Chamado c = MainActivity.chamado;
		System.out.println("carregando chamado:" + c);
		if (c == null) {
			return;
		}

		TextView localizacao = (TextView) findViewById(R.id.txtLOorrenciaLocalizacao);
		TextView descricao = (TextView) findViewById(R.id.txtOcorrenciaDescricao);
		System.out.println("Descricacao:" + c.getDescricao());
		descricao.setText(c.getDescricao());
		localizacao.setText("Latitude:" + c.getLatitude() + " Longitude:"
				+ c.getLongitude());
	}
	
	protected void finalizar() {
		TextView relatorio = (TextView) findViewById(R.id.txtLOorrenciaLocalizacao);
		MainActivity.chamado.setRelatorio(relatorio.getText().toString());
		MainActivity.logica.enviarChamado(MainActivity.chamado);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (MainActivity.chamado == null) {
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

				TesteUpload.upload(ConstrantesteMOBHA.idUM, ""
						+ MainActivity.chamado.getId(),
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
