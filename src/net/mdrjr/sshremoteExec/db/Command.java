package net.mdrjr.sshremoteExec.db;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="commands")
public class Command implements Serializable {

	private static final long serialVersionUID = -4106369501243475595L;
	
	@DatabaseField(generatedId = true)
	private Integer id;
	@DatabaseField(canBeNull = false, columnName = "name")
	private String name;
	@DatabaseField(canBeNull = false, columnName = "command")
	private String command;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	

}
