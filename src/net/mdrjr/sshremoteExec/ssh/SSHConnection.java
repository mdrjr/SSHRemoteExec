package net.mdrjr.sshremoteExec.ssh;

import java.io.InputStream;
import java.util.Properties;

import android.util.Log;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
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
		
	}

	

	public String execCMD(String cmd) {
		StringBuilder sb = new StringBuilder();
		
		jsch = new JSch();
		prop = new Properties();

		prop.put("StrictHostKeyChecking", "no");

		try {
			session = jsch.getSession(username, host, port);
			session.setPassword(password);
			session.setConfig(prop);
			session.setTimeout(10000);
			
			session.connect();
			
		} catch (Exception e) {
			Log.e("SSH-Connection", e.getMessage());
			sb.append("Could not connect to server.\n");
			sb.append(e.getMessage());
			return sb.toString();
		}
	
		
		if (!session.isConnected())
			try {
				session.connect();
			} catch (JSchException e1) {
				sb.append("Could not connect to the Server\n");
				Log.e("SSH-Connection", e1.getMessage());
				sb.append(e1.getMessage());
				return sb.toString();
			}
		
		try {
			channel = session.openChannel("exec");
		} catch (JSchException e1) {
			sb.append("Could not open SSH Channel"); 
			Log.e("SSH-Connection", e1.getMessage());
			sb.append(e1.getMessage());
			return sb.toString();
		}

		
		
		
		
		try {
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
			sb.append(e.getMessage());
		}
		
		return sb.toString();
		
	}

}
