package cn.jyd.bf.seckill;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class SecKill {

	public static void main(String[] args) throws IOException {
		long st = System.currentTimeMillis();
		int i = 1;
		Random random = new Random();
		File file = new File("D:\\shuju.txt");
		if(!file.exists()) {
			boolean createNewFile = file.createNewFile();
		}
		FileOutputStream out = new FileOutputStream(file);
		StringBuilder sb = new StringBuilder();
		
		StringBuilder s = new StringBuilder();
		while (i < 4000000 || i == 4000000) {
			
			for (int j = 0; j < 11; j++) {
				s.append(String.valueOf(random.nextInt(10)));
			}

			sb.append(s + "\r\n");
			s.setLength(0);
			if (i % 50 == 0) {
				out.write(sb.toString().getBytes("utf-8"));// 注意需要转换对应的字符集
				out.flush();
				sb.setLength(0);
				System.out.println(i);
			}
			i++;
		}
		out.close();
		long et = System.currentTimeMillis();
		System.out.println(et-st);
	}
}
