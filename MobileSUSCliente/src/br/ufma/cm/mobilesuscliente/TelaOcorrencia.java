package br.ufma.cm.mobilesuscliente;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TelaOcorrencia extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_ocorrencia);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tela_ocorrencia, menu);
		return true;
	}

}
