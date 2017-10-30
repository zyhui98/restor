package com.cditie.restor.restor_client;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by zhuyunhui on 10/16/2017.
 */
public class TelnetTest
{
	public static void main(String[] args) {
		Socket server = null;
		try {
			server = new Socket();
			InetSocketAddress address = new InetSocketAddress("127.0.0.1",20880);
			server.connect(address, 5000);
			//invoke queryById(10)
			server.getOutputStream().write("invoke queryById(10)  \n".getBytes());
			//byte[] b =new byte[2048];
			//server.getInputStream().read(b);

			InputStreamReader inputStreamReader = new InputStreamReader(server.getInputStream(),System.getProperty("sun.jnu.encoding"));
			String line = "";
			char[] b = new char[1];
			while (inputStreamReader.read(b) != -1){
				line += new String(b);
				if(line.endsWith("dubbo>")){
					line = line.substring(0,line.lastIndexOf("dubbo>"));
					break;
				}
			}
			System.out.println(line);

		} catch (Exception e) {
			System.out.println("telnet失败" + e.getMessage());
		}finally{
			if(server!=null)
				try {
					server.close();
				} catch (IOException e) {
				}
		}
	}
}
