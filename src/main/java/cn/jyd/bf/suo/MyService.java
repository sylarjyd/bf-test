/**
 * 
 */
/**
 * @author Administrator
 *
 */
package cn.jyd.bf.suo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 
 * @author Administrator
 *	Condition 类和 Object 类锁方法区别区别
 *1.  Condition 类的 awiat 方法和 Object 类的 wait 方法等效
  2.  Condition 类的 signal 方法和 Object 类的 notify 方法等效
  3.  Condition 类的 signalAll 方法和 Object 类的 notifyAll 方法等效
  4.  ReentrantLock 类可以唤醒指定条件的线程，而 object 的唤醒是随机的
  
  tryLock 和 lock 和 lockInterruptibly 的区别
	1.  tryLock 能获得锁就返回 true，不能就立即返回 false，tryLock(long timeout,TimeUnitunit)，可以增加时间限制，如果超过该时间段还没获得锁，返回 false
	2.  lock 能获得锁就返回 true，不能的话一直等待获得锁
	3.  lock 和 lockInterruptibly，如果两个线程分别执行这两个方法，但此时中断这两个线程，lock 不会抛出异常，而 lockInterruptibly 会抛出异常
 */
public class MyService{
	
	private Lock lock = new ReentrantLock();
	
	private Condition condition = lock.newCondition();
	
	
	public static void main(String[] args) {
		MyService myService = new MyService();
		
		for (int i = 0; i < 2; i++) {
			new Thread(()->{
				myService.testMethod();
			}).start();
		}
		
		
	}
	
	
	public void testMethod() {
		try {
			lock.lock();
			
//			condition.await();
//			
//			condition.signal();
			
			for (int i = 0; i < 5; i++) {
				System.out.println("ThreadName=" + Thread.currentThread().getName()+ (" " + (i + 1)));
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	
}