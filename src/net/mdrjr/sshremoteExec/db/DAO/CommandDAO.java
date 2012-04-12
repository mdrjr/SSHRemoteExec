package net.mdrjr.sshremoteExec.db.DAO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import net.mdrjr.sshremoteExec.db.Command;
import net.mdrjr.sshremoteExec.db.DBHelper;
import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

public class CommandDAO implements Serializable {

	private static final long serialVersionUID = 8637756758400490936L;
	
	private Dao<Command, Integer> daoCommand;
	private DBHelper dbHelper;
	private ConnectionSource connectionSource;
	@SuppressWarnings("unused")
	private Context context;
	
	public CommandDAO(Context context) {
		this.context = context;
		dbHelper = new DBHelper(context);
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
	
	public void create(Command c) {
		try {
			daoCommand.create(c);
		} catch (Exception e) {
			Log.e("DB", e.getMessage());
		}
	}
	
	public Command getCommandByName(String commandAlias) { 
		Command c = new Command();
		
		try {
			QueryBuilder<Command, Integer> builder = daoCommand.queryBuilder();
			builder.where().eq(Command.NAME_FIELD_NAME, commandAlias);
			
			PreparedQuery<Command> preparedQuery = builder.prepare();
			c = daoCommand.queryForFirst(preparedQuery);
			
		} catch(Exception e) {
			Log.e("DB", e.getMessage());
		}
		
		return c;
	}

}
