package cn.jyd.bf.threadlocal;

import java.util.HashMap;

public class BfTest {
	
	
	
	public static void main(String[] args) {
		

		
		HashMap<User,String> hashMap = new HashMap<>();
		
		User user = new User(12,"jyd");
		
		
		hashMap.put(user, "sylar");
		
		System.out.println(hashMap.get(user));
		
		
		
		
	}
	
	

	 static class User{
		private Integer id;
		private String name;
		
		public User(Integer id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public User() {
			super();
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
		
		
	}
	
	
}
