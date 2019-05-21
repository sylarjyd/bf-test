package cn.jyd.bf.suo;

import java.util.ArrayList;
import java.util.List;

public class ReadWrite {

	private static List<Object> list = new ArrayList<>();
		
	static{
		
		for (int i = 0; i < 100; i++) {
			
			list.add(i);
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadWrite readWrite = new ReadWrite();
		
		for (int i = 0; i < 5; i++) {
			new Thread(()->{
				readWrite.updateData();
			}).start();
		}
		
		
		for (int i = 0; i < 5; i++) {
			new Thread(()->{
				readWrite.getData();
			}).start();
		}
		
		
		readWrite.getData();
		
	}
	
	public void getData() {
		
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
	public void updateData() {
		
		for (int i = 0; i < 100; i++) {
			list.set(i, i*2);
		}
	}
	

}
