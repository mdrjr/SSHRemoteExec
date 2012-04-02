package net.mdrjr.sshremoteExec.db;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="Servers")
public class Server implements Serializable {

	private static final long serialVersionUID = 7370470069930484283L;
	
	public static final String ID_FIELD_NAME = "id";
	public static final String SERVERNAME_FIELD_NAME = "serverName";
	public static final String IP_FIELD_NAME = "ip"; 
	public static final String PORT_FIELD_NAME = "port";
	public static final String USERNAME_FIELD_NAME = "username";
	public static final String PASSWORD_FIELD_NAME = "password";
	

	@DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
	private Integer id;
	@DatabaseField(canBeNull = false, columnName = SERVERNAME_FIELD_NAME)
	private String serverName;
	@DatabaseField(canBeNull = false, columnName = IP_FIELD_NAME)
	private String ip;
	@DatabaseField(canBeNull = false, columnName = PORT_FIELD_NAME)
	private Integer port;
	@DatabaseField(canBeNull = false, columnName = USERNAME_FIELD_NAME)
	private String username;
	@DatabaseField(canBeNull = false, columnName = PASSWORD_FIELD_NAME)
	private String password;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
