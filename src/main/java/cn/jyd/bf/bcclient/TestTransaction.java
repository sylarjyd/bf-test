package cn.jyd.bf.bcclient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlock.TransactionResult;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthMining;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.parity.Parity;
import org.web3j.protocol.parity.methods.response.PersonalUnlockAccount;
import org.web3j.tx.Contract;


public class TestTransaction {
	
	public static final Web3j web3 =Conn.web3;
	public static final Parity parity =Conn.parity;
	public static final Parity ethClient =Conn.parity;
	public static final BigInteger covert = new BigInteger("1000000000000000000");

	
	@Test
	public void miner() throws Exception {
		EthMining ethMining = parity.ethMining().sendAsync().get();
		System.out.println(ethMining.isMining());
		
	}
	
	@Test
	public void test01() throws Exception {
		
		List<EthBlock.TransactionResult> txs = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, true).send().getBlock().getTransactions();
		txs.forEach(tx -> {
		  EthBlock.TransactionObject transaction = (EthBlock.TransactionObject) tx.get();

		  System.out.println(transaction.getFrom());
		});
		
	}
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
	
	
	public static String hexStringToString(String s) {
	    if (s == null || s.equals("")) {
	        return null;
	    }
	    s = s.replace(" ", "");
	    byte[] baKeyword = new byte[s.length() / 2];
	    for (int i = 0; i < baKeyword.length; i++) {
	        try {
	            baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    try {
	        s = new String(baKeyword, "UTF-8");
	        new String();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	    return s;
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		
//		String data = "hello world i am jyd !";
//		byte[] bytes = data.getBytes("utf-8");
//		System.out.println(new String(data.getBytes("utf-8"), "utf-8"));	
//		System.out.println(bytesToHex(bytes).toLowerCase());
//		System.out.println(bytesToHex(bytes).toLowerCase().equals("68656c6c6f20776f726c64206920616d206a79642021"));
		
//		System.out.println(hexStringToString("e68891e698afe5a4a7e6b5b7efbc81"));
		String transaction = getTransaction("0x4b18e3a6a7d54abaefcee0c65b031e9b206e4939297883a6145ecda58832a44d");
		
		System.out.println(transaction);
		
		System.out.println(hexStringToString(transaction.substring(2)));
		
//		testSaveData("我是大海！");
		
		
//		String hexInput = "hello world i am jyd !";
//		String data  = Hash.sha3(hexInput);
//		System.out.println(data);
//		System.out.println("0x9130e314755365b86866f55a9fcb64dc1780137a614e0418bdeb38b2b8e21ff4".equals(data));
		
//		
//		EthAccounts ea = parity.ethAccounts().send();
//		List<String> accounts = ea.getAccounts();
//		accounts.forEach(ac->{
//			System.out.println(ac);
//		});
//		
//		String ac1 = accounts.get(0);
//		String ac2 = accounts.get(1);
//		String password = "123";
//		BigInteger va = new BigInteger("5000000000000000000");
//		transferEthWith(ac1,password,ac2,va);
//		EthGetBalance ethGetBalance = ethClient.ethGetBalance(ac1,
//				DefaultBlockParameterName.LATEST).sendAsync().get();
//		BigInteger balance = ethGetBalance.getBalance();
//		System.out.println(balance);
//		System.out.println(balance.divide(covert));
//		System.out.println(Convert.fromWei(balance+"", Convert.Unit.ETHER));
		
	}
	
	
	public static String transferEthWith(String from,String password,String to,BigInteger value) throws  Exception
    {
        EthGetTransactionCount ethGetTransactionCount = ethClient.ethGetTransactionCount(
                from, DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        BigInteger gNoce = null;
		if(gNoce == null)
            gNoce = nonce;
        PersonalUnlockAccount personalUnlockAccount = ethClient.personalUnlockAccount(from,password).send();
        if (personalUnlockAccount.accountUnlocked())
        {
            BigInteger gasPrice = Contract.GAS_PRICE;
            BigInteger gasLimit = Contract.GAS_LIMIT.divide(new BigInteger("2"));
            synchronized(TestTransaction.class) {
                Transaction transaction = Transaction.createEtherTransaction(from,gNoce,gasPrice,gasLimit,to,value);
                gNoce = gNoce.add(new BigInteger("1"));
                EthSendTransaction transactionResponse = ethClient.ethSendTransaction(transaction).sendAsync().get();
                if(transactionResponse.hasError()){
                    String message=transactionResponse.getError().getMessage();
                    System.out.println("transaction failed,info:"+message);
                    return message;
                }else{
                    String hash=transactionResponse.getTransactionHash();
                    System.out.println("transaction from "+from+" to "+to+" amount:"+value);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return  hash;
                }
            }
 
        }
        return null;
    }

	@Test
	public void deployContract() {
//		MetaCoin contract = MetaCoin.deploy(web3, credentials, GAS_PRICE, GAS_LIMIT).send();
//		System.out.println(contract.getContractAddress());

	}
	
	
	public static String getTransaction(String transactionHash) throws IOException {
		
		EthTransaction ethTransaction = parity.ethGetTransactionByHash(transactionHash).send();
		String input = ethTransaction.getResult().getInput();
		return input;
	}
	
	public static void testSaveData(String input) throws Exception {
		EthAccounts ea = parity.ethAccounts().send();
		List<String> accounts = ea.getAccounts();
		accounts.forEach(ac->{
			System.out.println(ac);
		});
		
		String ac1 = accounts.get(0);
		String ac2 = accounts.get(1);
		String password = "123";
		byte[] bytes = input.getBytes("utf-8");
		String data = bytesToHex(bytes).toLowerCase();
		System.out.println(data);
		BigInteger va = new BigInteger("1000000000000000000");
		String hash = saveData(ac1,password,ac2,va,data);
		System.out.println(hash);
//		EthTransaction ethTransaction = parity.ethGetTransactionByHash(hash).sendAsync().get();
//		org.web3j.protocol.core.methods.response.Transaction result = ethTransaction.getResult();
//		System.out.println(JSON.toJSON(result));
	}
	
	

	public static String saveData(String from,String password,String to,BigInteger value,String data)throws  Exception {
		 EthGetTransactionCount ethGetTransactionCount = ethClient.ethGetTransactionCount(
	                from, DefaultBlockParameterName.LATEST).sendAsync().get();
	        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
	        BigInteger gNoce = null;
			if(gNoce == null)
	            gNoce = nonce;
	        PersonalUnlockAccount personalUnlockAccount = ethClient.personalUnlockAccount(from,password).send();
	        if (personalUnlockAccount.accountUnlocked())
	        {
	            BigInteger gasPrice = Contract.GAS_PRICE;
	            BigInteger gasLimit = Contract.GAS_LIMIT.divide(new BigInteger("2"));
	            synchronized(TestTransaction.class) {
	            	Transaction transaction = Transaction.createEthCallTransaction(from,to,data);
	                EthSendTransaction transactionResponse = ethClient.ethSendTransaction(transaction).sendAsync().get();
	                if(transactionResponse.hasError()){
	                    String message=transactionResponse.getError().getMessage();
	                    System.out.println("transaction failed,info:"+message);
	                    return message;
	                }else{
	                    String hash=transactionResponse.getTransactionHash();
	                    String result = transactionResponse.getResult();
	                    System.out.println("transaction from "+from+" to "+to+" amount:"+value+" result:"+result);      
	                    return hash;
	                }
	            }
	        }
	        return null;
	}

	
	
	
	private static void writeFile(String string, String string2) {
		File file = new File(string);
		FileWriter fw = null;
		if (file.exists()&&file.isFile()) {
			try {
				new FileWriter(file);
				fw.write(string2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null!=fw) {
					try {
						fw.flush();
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
			}	
		}else{
			
			try {
				
				boolean createNewFile = file.createNewFile();
				if (createNewFile) {
					System.out.println("文件创建成功！");
				}
			
				
				fw = new FileWriter(file);
				fw.write(string2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				if(null!=fw) {
					try {
						fw.flush();
						fw.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
			}
			
		}
		
	}
	
	
	
	
		
		
		
		
		
		
		

}
