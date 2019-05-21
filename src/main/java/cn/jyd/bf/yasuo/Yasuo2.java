package cn.jyd.bf.yasuo;

import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class Yasuo2 {
	public static void main(String[] args) throws IOException {
		
		Thumbnails.of("C:\\Users\\Administrator\\Desktop\\d1.png") 
        .scale(1f) 
        .outputQuality(0.25f) 
        .toFile("C:\\Users\\Administrator\\Desktop\\d2.png");
		
	}
}
