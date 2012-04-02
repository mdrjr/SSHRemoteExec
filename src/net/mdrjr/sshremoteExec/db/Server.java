package net.mdrjr.sshremoteExec.db;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="Servers")
public class Server implements Serializable {

	private static final long serialVersionUID = 7370470069930484283L;

	@DatabaseField(generatedId = true)
	private Integer id;
	@DatabaseField(canBeNull = false, columnName = "serverName")
	private String serverName;
	@DatabaseField(canBeNull = false, columnName = "ip")
	private String ip;
	@DatabaseField(canBeNull = false, columnName = "port")
	private Integer port;
	@DatabaseField(canBeNull = false, columnName = "username")
	private String username;
	@DatabaseField(canBeNull = false, columnName = "password")
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
