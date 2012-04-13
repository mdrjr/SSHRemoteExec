package net.mdrjr.sshremoteExec;

import java.util.List;

import net.mdrjr.sshremoteExec.db.Server;
import net.mdrjr.sshremoteExec.db.DAO.ServerDAO;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MenuServersListActivity extends Activity {
	private ServerDAO sDao;
	private Spinner spinnerSelectServer;
	private EditText edtServerName, edtServerIP, edtServerPort, edtServerUsername, edtServerPassword;
	private Button btnUpdate, btnDelete;
	private Utils utils;
	private Server currentServer;
	

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu_server_list);
	
		utils = new Utils();
		
		sDao = new ServerDAO(this);
		
		spinnerSelectServer = (Spinner) findViewById(R.id.spinner_mainmenu_server_list);
		
		edtServerName = (EditText) findViewById(R.id.listEdtServerName);
		edtServerIP = (EditText) findViewById(R.id.listEdtServerIP); 
		edtServerPort = (EditText) findViewById(R.id.listEdtServerPort);
		edtServerUsername = (EditText) findViewById(R.id.listEdtServerUsername);
		edtServerPassword = (EditText) findViewById(R.id.listEdtServerPassword);
		
		btnUpdate = (Button) findViewById(R.id.btn_mainmenu_server_list_update);
		btnDelete = (Button) findViewById(R.id.btn_mainmenu_server_list_delete);
		
		
		populateSpinner();

		addButtonDeleteListener();
		addButtonUpdateListener();
		addSpinnerListener();
	}
	
	private void populateSpinner() {
		List<Server> lstServers = sDao.getAllServers();
		ArrayAdapter<String> adapterServers = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, utils.lstServerToStringArray(lstServers));
		adapterServers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSelectServer.setAdapter(adapterServers);
	}

	private void addButtonDeleteListener() {
		OnClickListener l = new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println(spinnerSelectServer.getSelectedItem().toString());

			}
		};

		btnDelete.setOnClickListener(l);
	}

	private void addButtonUpdateListener() {
		OnClickListener l = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Server s = new Server();
				s.setId(currentServer.getId());
				s.setIp(edtServerIP.getText().toString());
				s.setPassword(edtServerPassword.getText().toString());
				s.setPort(Integer.valueOf(edtServerPort.getText().toString()));
				s.setServerName(edtServerName.getText().toString());
				s.setUsername(edtServerUsername.getText().toString());
				sDao.update(s);
				Toast.makeText(MenuServersListActivity.this, "Server Updated", Toast.LENGTH_SHORT).show();
			}
		};

		btnUpdate.setOnClickListener(l);
	}

	private void addSpinnerListener() {
		OnItemSelectedListener l = new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
				String spinnerSelectedServer = adapterView.getItemAtPosition(pos).toString();
				
				Server selectedServer = sDao.getServerByName(spinnerSelectedServer);
				
				edtServerName.setText(selectedServer.getServerName());
				edtServerIP.setText(selectedServer.getIp());
				edtServerPort.setText(selectedServer.getPort().toString());
				edtServerUsername.setText(selectedServer.getUsername());
				edtServerPassword.setText(selectedServer.getPassword());
				currentServer = selectedServer;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// do nothing when nothing is selected! duh!!!

			}
		};
		spinnerSelectServer.setOnItemSelectedListener(l);
	}

	
}
