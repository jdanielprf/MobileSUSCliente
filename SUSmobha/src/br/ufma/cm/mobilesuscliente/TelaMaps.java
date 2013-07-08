package br.ufma.cm.mobilesuscliente;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class TelaMaps extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tela_maps);
		
		final WebView engine = (WebView) findViewById(R.id.web_engine);  
		
		
		engine.loadUrl("http://www.devsi.com.br/principal.html");  
	
		
	
		WebSettings webSettings = engine.getSettings();
		webSettings.setBuiltInZoomControls(true);
		webSettings.setJavaScriptEnabled(true);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				String url="javascript:registrarUnidadeLivre(0, '"+-2.5164330206204784+"','"+-44.30511474609375+"')";
				System.out.println(url);
				engine.loadUrl(url);
			}
		}).start();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tela_maps, menu);
		return true;
	}

}
