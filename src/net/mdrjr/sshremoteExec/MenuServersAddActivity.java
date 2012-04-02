package net.mdrjr.sshremoteExec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuServersAddActivity extends Activity {
	private Button btnAddServer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu_server_add);
		btnAddServer = (Button) findViewById(R.id.buttonAddNewServer);
	}
	
	public void addButtonHandler() { 
		OnClickListener l = new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		};
		
		btnAddServer.setOnClickListener(l);
	}

}
