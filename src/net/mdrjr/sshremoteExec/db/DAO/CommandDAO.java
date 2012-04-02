package net.mdrjr.sshremoteExec.db.DAO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import net.mdrjr.sshremoteExec.db.Command;
import net.mdrjr.sshremoteExec.db.DatabaseHelper;
import net.mdrjr.sshremoteExec.db.Server;
import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class CommandDAO implements Serializable {

	private static final long serialVersionUID = 8637756758400490936L;
	
	private Dao<Command, Integer> daoCommand;
	private DatabaseHelper dbHelper;
	private ConnectionSource connectionSource;
	private Context context;
	
	public CommandDAO(Context context) {
		this.context = context;
		dbHelper = new DatabaseHelper(context);
		connectionSource = new AndroidConnectionSource(dbHelper);
		try {
			daoCommand = DaoManager.createDao(connectionSource, Command.class);
		} catch (Exception e) {
			Log.e("DB", e.getMessage());
		}
	}
	
	public List<Command> getAllCommands() {
		List<Command> lstCommands = null;
		try {
			lstCommands = daoCommand.queryForAll();
		} catch (SQLException e) {
			Log.e("DB", e.getMessage());
		}
		return lstCommands;
	}

}
