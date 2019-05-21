package cn.jyd.bf.threadlocal;

public class ThreadLocalDemo {
		 
	    public static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	    public static ThreadLocal<User> threadLocalUser = new ThreadLocal<User>();
	 
		public static void main(String args[]) {
			threadLocal.set(100);	// 保存值
			System.out.println(threadLocal.get());	// 获取值
			
			User user = new User();
			user.setName("JoonWhee");
			user.setAge(25);
			threadLocalUser.set(user);	// 保存值
			System.out.println(threadLocalUser.get());	// 获取值
		}
		
		static class User{
			String name;
			Integer age;
	 
			public String getName() {
				return name;
			}
	 
			public void setName(String name) {
				this.name = name;
			}
	 
			public Integer getAge() {
				return age;
			}
	 
			public void setAge(Integer age) {
				this.age = age;
			}
	 
			@Override
			public String toString() {
				return "User [name=" + name + ", age=" + age + "]";
			}
			
		}
}
