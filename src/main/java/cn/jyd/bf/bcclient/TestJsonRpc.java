package cn.jyd.bf.bcclient;

import java.net.URL;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;

public class TestJsonRpc {
	 public static void main(String[] args) {

	        // TODO 多个参数时使用例子
//	      String[] temp = new String[]{"0x12341234"};
//	      Object[] params = new Object[]{"0x1", "0x2", "0x8888f1f195afa192cfee860698584c030f4c9db1", temp};

	        // 密码为123456
//	        Object[] params = new Object[]{};
//	        String methodName = "personal_newAccount";
		 	Object[] params = new Object[]{};
		 	String methodName = "personal_listAccounts";
	        try {
	            JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://172.16.20.174:8546"));
	            String address = client.invoke(methodName, params, String.class);
	            System.out.println(address);
	        } catch (Throwable throwable) {
	            throwable.printStackTrace();
	        }
	    }
}
