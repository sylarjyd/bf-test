package cn.jyd.bf.suo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
/**
 * 在 JAVA 环境下 ReentrantLock 和 synchronized 都是 可重入锁。
 * @author Administrator
 *公 平锁（ Fair ）
	加锁前检查是否有排队等待的线程，优先排队等待的线程，先来先得
	非公平锁（ Nonfair ）
	加锁时不考虑排队等待问题，直接尝试获取锁，获取不到自动到队尾等待
	1.  非公平锁性能比公平锁高 5~10 倍，因为公平锁需要在多核的情况下维护一个队列
	2.  Java 中的 synchronized 是非公平锁，ReentrantLock 默认的 lock()方法采用的是非公平锁。
 */
public class AtomicTest {
	
	
	
	public static void main(String[] args) {
		AtomicInteger atomicInteger = new AtomicInteger();
		atomicInteger.set(10);
		
		AtomicLong atomicLong = new AtomicLong();
		AtomicBoolean atomicBoolean = new AtomicBoolean();
		
		AtomicReference<Map<String,Object>> atomicReference = new AtomicReference<Map<String,Object>>();
		Map<String, Object> map = new HashMap<>();
		map.put("dad", "fsfsf");
		map.put("dasdd", "fsfsdsdsdssf");
		atomicReference.set(map);
		
		Map<String, Object> map2 = atomicReference.get();
		
		Set<String> keySet = map2.keySet();
		for (String key : keySet) {
			System.out.println(key+"============"+map2.get(key));
		}
		
		
		
		
	}
}
