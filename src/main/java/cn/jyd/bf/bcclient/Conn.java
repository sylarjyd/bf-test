package cn.jyd.bf.bcclient;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

public class Conn {
	
	public static Web3j web3  = null;
	public static Parity parity  = null;
	public static String ip_port  = "http://172.16.20.174:8546/";
	
	
	static {
		//创建rpc请求
		web3 = Web3j.build(new HttpService(ip_port)); 
		parity = Parity.build(new HttpService(ip_port));
	}
}
