package com.run;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.server.Server;
import com.utils.Tools;

/**
 * @author Boris
 * @description 
 * 2016Äê9ÔÂ18ÈÕ
 */
public class RunServer {
	private ServerSocket serverSocket = null;
	private Tools tools;
	
	public RunServer(){
		tools = Tools.getTools();
		int port = Integer.parseInt(tools.getProperty("service.port"));
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listenClientRequest(){
		while(true){
			try {
				Socket socket = serverSocket.accept();
				Server server = new Server(socket);
				new Thread(server, server.LISTEN_CLIENT).start();
				new Thread(server, server.SEARCH_COMMAND).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		RunServer runServer = new RunServer();
		runServer.listenClientRequest();
	}
}
