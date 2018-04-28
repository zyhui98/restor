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
			InetSocketAddress address = new InetSocketAddress("192.168.25.85",20880);
			server.connect(address, 5000);

			//invoke queryById(10)
			String cmd = "count MyService \n";
			server.getOutputStream().write( cmd.getBytes());
			//byte[] b =new byte[2048];
			//server.getInputStream().read(b);

			InputStreamReader inputStreamReader = new InputStreamReader(server.getInputStream(),"GBK");
			String result = "";
			char[] b = new char[1];
			while (inputStreamReader.read(b) != -1){
				result += new String(b);
				if(cmd.toLowerCase().startsWith("count")){
					if(result.endsWith("telnet>")){
						result = result.substring(0,result.lastIndexOf("telnet>"));
						break;
					}
				}else{
					if(result.endsWith("dubbo>")){
						result = result.substring(0,result.lastIndexOf("dubbo>"));
						break;
					}
				}
			}
			System.out.println(result);

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
