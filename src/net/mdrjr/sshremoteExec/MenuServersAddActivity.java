package net.mdrjr.sshremoteExec;

import net.mdrjr.sshremoteExec.db.Server;
import net.mdrjr.sshremoteExec.db.DAO.ServerDAO;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuServersAddActivity extends Activity {
	private Button btnAddServer;
	private EditText edtServerName, edtServerIP, edtServerPort, edtServerUserName, edtServerPassword;
	private ServerDAO sDao;
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu_server_add);
		btnAddServer = (Button) findViewById(R.id.buttonAddNewServer);
		
		edtServerName = (EditText) findViewById(R.id.edtServerName);
		edtServerIP = (EditText) findViewById(R.id.edtServerIP);
		edtServerPort = (EditText) findViewById(R.id.edtServerPort);
		edtServerPort.setText("22");
		edtServerUserName = (EditText) findViewById(R.id.edtServerUserName);
		edtServerPassword = (EditText) findViewById(R.id.edtServerPassword);
		
		sDao = new ServerDAO(this);
		addButtonHandler();
	}
	
	public void addButtonHandler() { 
		OnClickListener l = new OnClickListener() {
			@Override
			public void onClick(View v) {
				// POJO
				Server s = new Server();
				// Data
				s.setServerName(edtServerName.getText().toString());
				s.setIp(edtServerIP.getText().toString());
				s.setPassword(edtServerPassword.getText().toString());
				s.setPort(Integer.valueOf(edtServerPort.getText().toString()));
				s.setUsername(edtServerUserName.getText().toString());
				// Save
				sDao.create(s);
				// Toast its saved.
				Toast.makeText(MenuServersAddActivity.this, "Server: " + edtServerName.getText().toString() + " Saved.", Toast.LENGTH_SHORT).show();
				MenuServersAddActivity.this.finish();
			}
		};
		
		btnAddServer.setOnClickListener(l);
	}

}
