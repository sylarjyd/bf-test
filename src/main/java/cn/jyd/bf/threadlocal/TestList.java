package cn.jyd.bf.threadlocal;

import java.util.ArrayList;

public class TestList {

	
	public static void main(String[] args) throws InterruptedException {
		
		ArrayList<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < 1000; i++) {
			list.add(i);
		}
		
		Thread.currentThread().sleep(3000);
		
		new Thread(()->{
			for (int i = 0; i < 1000; i++) {
				System.out.println(list.get(i));
			}
		}).start();
		
		
		new Thread(()->{
			for (int i = 0; i < 1000; i++) {
				list.set(i, 1);
			}
			
		}).start();
		
		
	
	}
	
	
}
