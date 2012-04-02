package net.mdrjr.sshremoteExec;

import java.util.List;

import net.mdrjr.sshremoteExec.db.Command;
import net.mdrjr.sshremoteExec.db.Server;

public class Utils {

	public final String[] lstServerToStringArray(List<Server> lstServers) { 
		String[] aServers = new String[lstServers.size()];
		for(int i = 0; i < lstServers.size() ; i++) {
			aServers[i] = lstServers.get(i).getServerName();
		}
		return aServers;
	}
	
	public final String[] lstCommandToStringArray(List<Command> lstCommands) {
		String[] aCommands = new String[lstCommands.size()];
		for(int i = 0; i < lstCommands.size(); i++) {
			aCommands[i] = lstCommands.get(i).getName();
		}
		return aCommands;
	}
	
	
	
}
