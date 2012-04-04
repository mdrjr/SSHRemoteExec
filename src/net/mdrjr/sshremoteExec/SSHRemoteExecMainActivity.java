package net.mdrjr.sshremoteExec;

import java.util.List;

import net.mdrjr.sshremoteExec.db.Command;
import net.mdrjr.sshremoteExec.db.Server;
import net.mdrjr.sshremoteExec.db.DAO.CommandDAO;
import net.mdrjr.sshremoteExec.db.DAO.ServerDAO;
import net.mdrjr.sshremoteExec.ssh.SSHConnection;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SSHRemoteExecMainActivity extends Activity {
	private Utils utils;
	private Spinner spinnerServers, spinnerCommands;
	private Button btnQuery;
	private CommandDAO cDao;
	private ServerDAO sDao;
	private EditText edtTextReturnServer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		cDao = new CommandDAO(this);
		sDao = new ServerDAO(this);
		utils = new Utils();
		populateAllSpinners();
		edtTextReturnServer = (EditText) findViewById(R.id.edtTextReturnServer);
		addButtonListener();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
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

		ArrayAdapter<String> adapterServers = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				utils.lstServerToStringArray(lstServers));
		adapterServers
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerServers.setAdapter(adapterServers);

		ArrayAdapter<String> adapterCommands = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item,
				utils.lstCommandToStringArray(lstCommands));
		adapterCommands
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerCommands.setAdapter(adapterCommands);

	}

	public void addButtonListener() {
		btnQuery = (Button) findViewById(R.id.buttonQuery);
		OnClickListener l = new OnClickListener() {
			@Override
			public void onClick(View v) {
				String selectedServer = String.valueOf(spinnerServers
						.getSelectedItem());
				String selectedCommand = String.valueOf(spinnerCommands
						.getSelectedItem());
				String show = "Running -> Server: " + selectedServer
						+ " | Command: " + selectedCommand;
				Toast.makeText(SSHRemoteExecMainActivity.this, show,
						Toast.LENGTH_LONG).show();

				Server s = sDao.getServerByName(selectedServer);
				Command c = cDao.getCommandByName(selectedCommand);

				ProgressDialog dialog = ProgressDialog.show(
						SSHRemoteExecMainActivity.this, "Connecting...",
						"Connecting to Server: " + s.getIp() + ":"
								+ s.getPort().toString(), true);
				SSHConnection sshConnection = new SSHConnection(
						s.getUsername(), s.getPassword(), s.getIp(),
						s.getPort());
				dialog.dismiss();

				dialog = ProgressDialog.show(SSHRemoteExecMainActivity.this,
						"Executing Command...", "Executing: " + c.getName()
								+ " on Server: " + s.getServerName(), true);
				edtTextReturnServer.setText(sshConnection.execCMD(c
						.getCommand()));
				dialog.dismiss();
			}
		};

		btnQuery.setOnClickListener(l);

	}

	public void showAboutMenu() {
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
		alertBuilder
				.setMessage(
						"SSH Remote Exec is a small open-source android project.\n"
								+ "It\'s made out to show how to use a ORM on Android.\n"
								+ "Source code avaliable on: http://github.com/mdrjr\n"
								+ "My blog: http://www.mdrjr.net")
				.setCancelable(true)
				.setNegativeButton("Close",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();

							}
						});
		AlertDialog alert = alertBuilder.create();
		alert.show();
	}

	public void mainMenuCommandsAdd() {
		Intent intent = new Intent(SSHRemoteExecMainActivity.this,
				MenuCommandsAddActivity.class);
		startActivity(intent);
	}

	public void mainMenuCommandsList() {
		Intent intent = new Intent(SSHRemoteExecMainActivity.this,
				MenuCommandsListActivity.class);
		startActivity(intent);
	}

	public void mainMenuServersAdd() {
		Intent intent = new Intent(SSHRemoteExecMainActivity.this,
				MenuServersAddActivity.class);
		startActivity(intent);
	}

	public void mainMenuSeversList() {
		Intent intent = new Intent(SSHRemoteExecMainActivity.this,
				MenuServersListActivity.class);
		startActivity(intent);
	}
}
