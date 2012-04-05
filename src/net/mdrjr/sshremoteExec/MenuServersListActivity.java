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

public class MenuServersListActivity extends Activity {
	private ServerDAO sDao;
	private Spinner spinnerSelectServer;
	private EditText edtServerName, edtServerIP, edtServerPort, edtServerUsername, edtServerPassword;
	private Button btnUpdate, btnDelete;
	private Utils utils;
	
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
				// TODO Auto-generated method stub
				
			}
		};
		
		btnUpdate.setOnClickListener(l);
	}
	
	private void addButtonUpdateListener() {
		OnClickListener l = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		};
		
		btnDelete.setOnClickListener(l);
	}
	
	private void addSpinnerListener() { 
		OnItemSelectedListener l = new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// do nothing when nothing is selected! duh!!!
				
			}
		};
		spinnerSelectServer.setOnItemSelectedListener(l);
	}
}
