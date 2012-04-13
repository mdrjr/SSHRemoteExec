package net.mdrjr.sshremoteExec;

import java.util.List;

import net.mdrjr.sshremoteExec.db.Command;
import net.mdrjr.sshremoteExec.db.DAO.CommandDAO;
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

public class MenuCommandsListActivity extends Activity {
	private CommandDAO cDao;
	private Spinner spinnerEdtCommand;
	private EditText edtCommand, edtCommandAlias;
	private Button btnUpdateCommand, btnDeleteCommand;
	private Utils utils;
	private Command currentCommand;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu_command_list);
	
		utils = new Utils();
		
		cDao = new CommandDAO(this);
		spinnerEdtCommand = (Spinner) findViewById(R.id.spinnerEdtCommand);
		btnUpdateCommand = (Button) findViewById(R.id.btnUpdateCommand);
		btnDeleteCommand = (Button) findViewById(R.id.btnDeleteCommand);
		edtCommand = (EditText) findViewById(R.id.edtCommandCommand);
		edtCommandAlias = (EditText) findViewById(R.id.edtUpdateCommandName);
		
		populateSpinner();
		addButtonDeleteListener();
		addButtonUpdateListener();
		addSpinnerOnChangeListener();
	}
	
	private void populateSpinner() {
		List<Command> lstCommands = cDao.getAllCommands();
		ArrayAdapter<String> adapterCommands = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, utils.lstCommandToStringArray(lstCommands));
		adapterCommands.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerEdtCommand.setAdapter(adapterCommands);
	}
	
	private void addButtonDeleteListener() {
		OnClickListener l = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Command commandToDelete = currentCommand;
				cDao.delete(commandToDelete);
				populateSpinner();
				Toast.makeText(MenuCommandsListActivity.this, "Command Deleted", Toast.LENGTH_SHORT).show();
			}
		};
		btnDeleteCommand.setOnClickListener(l);
	}
	
	private void addButtonUpdateListener() {
		OnClickListener l = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Command c = new Command();
				c.setId(currentCommand.getId());
				c.setName(edtCommandAlias.getText().toString());
				c.setCommand(edtCommand.getText().toString());
				cDao.update(c);
				populateSpinner();
				Toast.makeText(MenuCommandsListActivity.this, "Command Updated", Toast.LENGTH_SHORT).show();
			}
		};
		btnUpdateCommand.setOnClickListener(l);
	}
	
	private void addSpinnerOnChangeListener() {
		OnItemSelectedListener l = new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
				String spinnerSelectedServer = adapterView.getItemAtPosition(pos).toString();
				
				Command selectedCommand = cDao.getCommandByName(spinnerSelectedServer);
				
				edtCommand.setText(selectedCommand.getCommand());
				edtCommandAlias.setText(selectedCommand.getName());
				currentCommand = selectedCommand;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) { /* Nothing Selected Nothing ToDo */ } 
		};
		
		spinnerEdtCommand.setOnItemSelectedListener(l);
	}
}
