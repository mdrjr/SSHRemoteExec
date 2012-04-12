package net.mdrjr.sshremoteExec.db.DAO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import net.mdrjr.sshremoteExec.db.DBHelper;
import net.mdrjr.sshremoteExec.db.Server;
import android.content.Context;
import android.util.Log;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

public class ServerDAO implements Serializable {

	private static final long serialVersionUID = 8637756758400490936L;

	private Dao<Server, Integer> daoServer;
	private DBHelper dbHelper;
	private ConnectionSource connectionSource;
	@SuppressWarnings("unused")
	private Context context;

	public ServerDAO(Context context) {
		this.context = context;
		dbHelper = new DBHelper(context);
		connectionSource = new AndroidConnectionSource(dbHelper);
		try {
			daoServer = DaoManager.createDao(connectionSource, Server.class);
		} catch (Exception e) {
			Log.e("DB", e.getMessage());
		}
	}

	public List<Server> getAllServers() {
		List<Server> lstServers = null;
		try {
			lstServers = daoServer.queryForAll();
		} catch (SQLException e) {
			Log.e("DB", e.getMessage());
		}
		return lstServers;
	}

	public void create(Server s) {
		try {
			daoServer.create(s);
		} catch (Exception e) {
			Log.e("DB", e.getMessage());
		}
	}

	public Server getServerByName(String serverName) {
		Server s = new Server();
		try {
			QueryBuilder<Server, Integer> builder = daoServer.queryBuilder();
			builder.where().eq(Server.SERVERNAME_FIELD_NAME, serverName);
			
			PreparedQuery<Server> preparedQuery = builder.prepare();
			s = daoServer.queryForFirst(preparedQuery);
		} catch (Exception e) {
			Log.e("DB", e.getMessage());
		}
		return s;
	}

}
