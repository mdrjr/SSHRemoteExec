package net.mdrjr.sshremoteExec.ssh;

import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHConnection {

	private Session session;
	private InputStream in;
	private Channel chan;
	private Properties prop;
	private JSch jsch;
	
}
