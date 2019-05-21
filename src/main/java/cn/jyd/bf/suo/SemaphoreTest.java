package cn.jyd.bf.suo;

import java.util.concurrent.Semaphore;
/**
 * 
 * Semaphore 是一种基于计数的信号量。它可以设定一个阈值，基于此，多个线程竞争获取许可信
号，做完自己的申请后归还，超过阈值后，线程申请许可信号将会被阻塞。Semaphore 可以用来
构建一些对象池，资源池之类的，比如数据库连接池
 * @author Administrator
 *
 */
public class SemaphoreTest {
	
	
	
	public static void main(String[] args) {
		// 创建一个计数阈值为 5 的信号量对象
		// 只能 5 个线程同时访问
		Semaphore semp = new Semaphore(5);
		
		try{
			semp.acquire();
			
			try {

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				semp.release();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}
	
	
}
