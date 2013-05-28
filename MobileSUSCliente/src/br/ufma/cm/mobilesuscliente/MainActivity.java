package br.ufma.cm.mobilesuscliente;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

	private View chat;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		chat=findViewById(R.id.btnMainChat);
		chat.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				initChat();
			}
		});
		
	}

	protected void initChat() {
		Intent i = new Intent(this, TelaChat.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
