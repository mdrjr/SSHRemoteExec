package net.mdrjr.sshremoteExec;

import net.mdrjr.sshremoteExec.db.DAO.ServerDAO;
import android.app.Activity;
import android.os.Bundle;

public class MenuServersListActivity extends Activity {
	private ServerDAO sDao;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainmenu_server_list);
		sDao = new ServerDAO(this);
	}
}
