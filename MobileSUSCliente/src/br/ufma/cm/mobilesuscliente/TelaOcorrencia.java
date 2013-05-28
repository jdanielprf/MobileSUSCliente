package br.ufma.cm.mobilesuscliente;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TelaOcorrencia extends Activity {

	private Button cam;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_ocorrencia);
		init();
	}

	private void init() {
		cam = (Button) findViewById(R.id.btnEventRegist);
		cam.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				register();
				
			}
		});
	}

	protected void register() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
        startActivity(cameraIntent); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.tela_ocorrencia, menu);
		return true;
	}

}
