package net.mdrjr.sshremoteExec;

import java.util.List;

import net.mdrjr.sshremoteExec.db.Command;
import net.mdrjr.sshremoteExec.db.Server;
import net.mdrjr.sshremoteExec.db.DAO.CommandDAO;
import net.mdrjr.sshremoteExec.db.DAO.ServerDAO;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SSHRemoteExecMainActivity extends Activity {
    private Utils utils;
    private Spinner spinnerServers, spinnerCommands;
    private Button btnQuery;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        utils = new Utils();
        populateAllSpinners();
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.mainmenu_about:
    		showAboutMenu();
    		return true;
    	case R.id.mainmenu_commands_add:
    		mainMenuCommandsAdd();
    		return true;
    	case R.id.mainmenu_commands_list:
    		mainMenuCommandsList();
    		return true;
    	case R.id.mainmenu_servers_add:
    		mainMenuServersAdd();
    		return true;
    	case R.id.mainmenu_servers_list:
    		mainMenuSeversList();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.mainmenu, menu);
    	return true;
    }
    
    public void populateAllSpinners() {
    	spinnerServers = (Spinner) findViewById(R.id.spinnerServer);
    	spinnerCommands = (Spinner) findViewById(R.id.spinnerCommand);
    	
    	ServerDAO daoServer = new ServerDAO(this);
    	CommandDAO daoCommand = new CommandDAO(this);
    	
    	List<Server> lstServers = daoServer.getAllServers();
    	List<Command> lstCommands = daoCommand.getAllCommands();
    	
    	ArrayAdapter<String> adapterServers = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, utils.lstServerToStringArray(lstServers));
    	adapterServers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinnerServers.setAdapter(adapterServers);
    	
    	ArrayAdapter<String> adapterCommands = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, utils.lstCommandToStringArray(lstCommands));
    	adapterCommands.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinnerCommands.setAdapter(adapterCommands);
    	
    }
    
    public void addButtonListener() {
    	btnQuery = (Button) findViewById(R.id.buttonQuery);
    	OnClickListener l = new OnClickListener() {
			@Override
			public void onClick(View v) {
				String selectedServer = String.valueOf(spinnerServers.getSelectedItem());
				String selectedCommand = String.valueOf(spinnerCommands.getSelectedItem());
				String show = "Server: " + selectedServer + " | Command: " + selectedCommand;
				Toast.makeText(SSHRemoteExecMainActivity.this, show , Toast.LENGTH_LONG).show();
			}
		};
		
		btnQuery.setOnClickListener(l);
    	
    }

    public void showAboutMenu() {   
    }

    public void mainMenuCommandsAdd() {
    	Intent intent = new Intent(SSHRemoteExecMainActivity.this, MenuCommandsAddActivity.class);
    	startActivity(intent);
    }

    public void mainMenuCommandsList() {
    	
    }

    public void mainMenuServersAdd() {
    	Intent intent = new Intent(SSHRemoteExecMainActivity.this, MenuServersAddActivity.class);
    	startActivity(intent);
    }

    public void mainMenuSeversList() {
    	
    }
}


