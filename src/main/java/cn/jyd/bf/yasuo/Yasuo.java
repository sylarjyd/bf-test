package cn.jyd.bf.yasuo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGImageEncoder;
public class Yasuo {
	
	public static void main(String[] args) {
		String oldsrc = "C:\\Users\\Administrator\\Desktop\\aa";
		String newsrc = "C:\\Users\\Administrator\\Desktop\\aa\\ss";
//		Integer widthdist = null;
//		Integer heightdist = null;
		reduceImgAll(oldsrc,newsrc);
	}
	public static void reduceImgAll(String oldsrc, String newsrc) {
		int widthdist = 1251;
		int heightdist = 700;
			  try {
			   File file = new File(oldsrc);
			   if (!file.exists()) {
			    return;
			   }
			   File[] srcfile = file.listFiles();
			   if (srcfile != null) {
			    for (int i = 0; i < srcfile.length; i++) {
			     if (srcfile[i].isFile()
			       && (srcfile[i].getName().endsWith(".jpg")
			         || srcfile[i].getName().endsWith(".JPG")
			         || srcfile[i].getName().endsWith(".gif") 
			         || srcfile[i].getName().endsWith(".png")
			         || srcfile[i]
			         .getName().endsWith(".gif"))) {
			      Image src = javax.imageio.ImageIO.read(srcfile[i]);
			      BufferedImage image = ImageIO.read(srcfile[i]);
//			      widthdist = image.getWidth(); 
//			      heightdist = image.getHeight();
			      BufferedImage tag = new BufferedImage((int) widthdist,
			        (int) heightdist, BufferedImage.TYPE_INT_RGB); 
			      tag.getGraphics().drawImage(
			        src.getScaledInstance(widthdist, heightdist,
			          Image.SCALE_SMOOTH), 0, 0, null);
			      FileOutputStream out = new FileOutputStream(newsrc
			        + srcfile[i].getName());
			      JPEGImageEncoder encoder = com.sun.image.codec.jpeg.JPEGCodec
			        .createJPEGEncoder(out);
			      System.out.println(oldsrc + "/" + srcfile[i].getName());
			      encoder.encode(tag);
			      out.close();
			     } else {
			      reduceImgAll(oldsrc + srcfile[i].getName(), newsrc);
			     }
			    }
			   }
			  } catch (IOException ex) {
			   ex.printStackTrace();
			  }
			 }
}
