package net.mdrjr.sshremoteExec.db;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="commands")
public class Command implements Serializable {

	private static final long serialVersionUID = -4106369501243475595L;
	
	public static final String ID_FIELD_NAME = "id";
	public static final String NAME_FIELD_NAME = "name";
	public static final String COMMAND_FIELD_NAME = "command";
	
	
	@DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
	private Integer id;
	@DatabaseField(canBeNull = false, columnName = NAME_FIELD_NAME)
	private String name;
	@DatabaseField(canBeNull = false, columnName = COMMAND_FIELD_NAME)
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
