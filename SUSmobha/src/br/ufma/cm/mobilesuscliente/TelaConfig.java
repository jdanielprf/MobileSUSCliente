package br.ufma.cm.mobilesuscliente;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class TelaConfig extends Activity {
	private View salvar;
	public static String diretorio = File.separator + "sdcard" + File.separator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_config);

		salvar = findViewById(R.id.txtConfigSalvar);
		salvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				salvar();
			}
		});
		
	}
	@Override
	protected void onStart() {
		ler();
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tela_config, menu);
		return true;
	}

	protected void salvar() {
		try {

			File file = new File(diretorio + "dadosID.txt");
			file.createNewFile();

			EditText txtIDUM = (EditText) findViewById(R.id.txtConfigIDUM);
			EditText txtCentral = (EditText) findViewById(R.id.txtConfigIDCentral);

			FileWriter fileEscrita = new FileWriter(file);
			fileEscrita.write(txtIDUM.getText().toString() + "\n");
			fileEscrita.write(txtCentral.getText().toString());
			fileEscrita.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
	}

	protected void ler() {
		try {
			FileInputStream inp = new FileInputStream(diretorio + "dadosID.txt");
		
			Scanner s = new Scanner(inp);

			EditText txtIDUM = (EditText) findViewById(R.id.txtConfigIDUM);
			if(s.hasNextLine()){
			txtIDUM.setText(s.nextLine());
			}
			EditText txtCentral = (EditText) findViewById(R.id.txtConfigIDCentral);
			if(s.hasNextLine()){
				txtCentral.setText(s.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
