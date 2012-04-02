package net.mdrjr.sshremoteExec.ssh;

import java.io.InputStream;
import java.util.Properties;

import android.util.Log;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHConnection {

	private Session session;
	private InputStream in;
	private Channel channel;
	private Properties prop;
	private JSch jsch;

	private String username;
	private String password;
	private String host;
	private Integer port;

	public SSHConnection(String username, String password, String host,
			Integer port) {
		super();
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
		connect();
	}

	private void connect() {
		jsch = new JSch();
		prop = new Properties();

		prop.put("StrictHostKeyChecking", "no");

		try {
			session = jsch.getSession(username, host, port);
			session.setPassword(password);
			session.setConfig(prop);
			session.connect();
		} catch (Exception e) {
			Log.e("SSH-Connection", e.getMessage());
		}
	}

	public String execCMD(String cmd) {
		StringBuilder sb = new StringBuilder();
		try {
			
			if (!session.isConnected()) session.connect();
			
			channel = session.openChannel("exec");
			
			((ChannelExec)channel).setCommand(cmd);
			
			channel.connect();
			
			in = channel.getInputStream();
			
			byte[] tmp = new byte[4096];
			
			Thread.sleep(300);
			
			while(true) {
				
				while(in.available() > 0) {
					int i = in.read(tmp, 0, 4096);
					if(i<0) break;
					sb.append(new String(tmp, 0, i));
				}
				if(channel.isEOF()) break;
				Thread.sleep(300);
			}
		} catch (Exception e) {
			Log.e("SSH-Connection", e.getMessage());
		}
		
		return sb.toString();
		
	}

}
