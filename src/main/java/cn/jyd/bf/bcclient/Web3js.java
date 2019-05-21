package cn.jyd.bf.bcclient;

import java.math.BigInteger;
import java.util.List;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthAccounts;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlock.Block;
import org.web3j.protocol.core.methods.response.EthBlock.TransactionResult;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthCoinbase;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByHash;
import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByNumber;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.parity.Parity;

import com.alibaba.fastjson.JSON;



public class Web3js {
	
	public static final Web3j web3 =Conn.web3;
	public static final Parity parity =Conn.parity;
	
		public static void main(String[] args) throws Exception{
		
			
			
			//获取Geth以太坊版本
			Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println("clientVersion======"+clientVersion);
			
			//获取账户列表
			EthAccounts ethAccounts = web3.ethAccounts().send();
			List<String> accounts = ethAccounts.getAccounts();
//			accounts.forEach((String accountId)->{
//				System.out.println(accountId);
//			});
			accounts.forEach(System.out::println);
			
			//获取区块个数
			EthBlockNumber ethBlockNumber = web3.ethBlockNumber().send();
			BigInteger blockNumber = ethBlockNumber.getBlockNumber();
			System.out.println("blockNumber==========="+blockNumber);
		
			//默认收入账户
			EthCoinbase ethCoinbase = web3.ethCoinbase().send();
			String result = ethCoinbase.getResult();
			String address = ethCoinbase.getAddress();
			System.out.println("result==========="+result);
			System.out.println("address==========="+address);
						
			boolean returnFullTransactionObjects = true;
			DefaultBlockParameter defaultBlockParameter = new DefaultBlockParameterNumber(474);
			EthBlock ethBlock = web3.ethGetBlockByNumber(defaultBlockParameter, returnFullTransactionObjects).send();
			Block block = ethBlock.getBlock();
			System.out.println(JSON.toJSON(block));			
			
	
			EthGetBlockTransactionCountByHash ebtcb = web3.ethGetBlockTransactionCountByHash("0xa238331fa0f3be8ba58d7e00fa5c19e65286b5275c62bdaa6d1814f642631308").send();		
			String result2 = ebtcb.getResult();
			System.out.println("transactionCount========"+result2);
			
			
			//根据节点id获取交易信息
			DefaultBlockParameter defaultBlockParameter2 = new DefaultBlockParameterNumber(474);
			EthGetBlockTransactionCountByNumber ebtcbn = web3.ethGetBlockTransactionCountByNumber(defaultBlockParameter2).send();
			System.out.println(ebtcbn.getTransactionCount());
			
			//根据节点id和交易id获取交易信息
			BigInteger transactionIndex = new BigInteger("0");
			EthTransaction et = web3.ethGetTransactionByBlockNumberAndIndex(defaultBlockParameter2, transactionIndex).send();
			Transaction transaction = et.getTransaction().get();
			System.out.println(JSON.toJSON(block));
			
			//根据节点hash获取交易列表
			String blockHash = "0x8e9f915ef98f1780482290d8b33e63a123757053136bd50f5e91b0a8b0286f0f";
			EthBlock eb = web3.ethGetBlockByHash(blockHash, returnFullTransactionObjects).send();
			Block b = ethBlock.getBlock();
			System.out.println(JSON.toJSON(b));
			List<TransactionResult> transactions = b.getTransactions();
			transactions.forEach(t ->{
				System.out.println(JSON.toJSON(t));
			});
			
			//账户余额
			EthGetBalance egb = web3.ethGetBalance("0x03f519f9d065e48c2e5c022a4c2a4d07b82a43c2",
					DefaultBlockParameterName.LATEST).send();
			BigInteger balance = egb.getBalance();
			System.out.println("账户的余额为============"+balance);
			
			//账户id
			List<String> accountIds = parity.personalListAccounts().send().getAccountIds();
			accountIds.forEach(id->{
				System.out.println(id);
			});
			//解锁账户
			Boolean result3 = parity.personalUnlockAccount("0x03f519f9d065e48c2e5c022a4c2a4d07b82a43c2", "123").send().getResult();
			System.out.println(result3);

			
			BigInteger balance2 = parity.ethGetBalance("0x03f519f9d065e48c2e5c022a4c2a4d07b82a43c2", 
					DefaultBlockParameterName.LATEST).send().getBalance();
			System.out.println(balance2);
			
		}
}
