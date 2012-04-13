package net.mdrjr.sshremoteExec;

import net.mdrjr.sshremoteExec.db.Command;
import net.mdrjr.sshremoteExec.db.DAO.CommandDAO;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuCommandsAddActivity extends Activity {

	private CommandDAO cDao;
	private EditText edtCommandAlias, edtCommand;
	private Button btnSaveCommand;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu_command_add);

		edtCommandAlias = (EditText) findViewById(R.id.edtCommandAlias);
		edtCommand = (EditText) findViewById(R.id.edtCommand);
		btnSaveCommand = (Button) findViewById(R.id.btnSaveCommand);
		
		cDao = new CommandDAO(this);
		
		addButtonHandler();

	}
	
	private void addButtonHandler() { 
		OnClickListener l = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Command c = new Command();
				c.setCommand(edtCommand.getText().toString());
				c.setName(edtCommandAlias.getText().toString());
				cDao.create(c);
				Toast.makeText(MenuCommandsAddActivity.this, "Command: " + edtCommandAlias.getText().toString() + " added.", Toast.LENGTH_SHORT).show();
				MenuCommandsAddActivity.this.finish();
			}
		};
		
		btnSaveCommand.setOnClickListener(l);
	}
}
