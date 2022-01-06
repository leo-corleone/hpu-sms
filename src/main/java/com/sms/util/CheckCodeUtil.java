package com.sms.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CheckCodeUtil {

	
	private static final Integer Width = 80;
	private static final Integer Height = 30;
	private static Integer CheckCode;
		
	
	public static Integer getCheckCode() {
		return CheckCode;
	}

	public static void setCheckCode(Integer checkCode) {
		CheckCode = checkCode;
	}

	public static Color getColor() {
		
		Random random = new Random();
		
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		
		return new Color(r, g, b);
	}
	
	public static String getNumber() {
		
		 int ran = (int)(Math.random()*9000)+1000;
		 setCheckCode(ran);
		 return new String(String.valueOf(ran));
	}
	
	public static void drawLine(Graphics graphics) {
		
		
		for (int i = 0; i < 30; i++) {
			
			Random random = new Random();
			
			int x1 = random.nextInt(Width);
			int y1 = random.nextInt(Height);
			
			int x2 = random.nextInt(Width+30);
			int y2 = random.nextInt(Height+80);
			
			graphics.setColor(getColor());
			graphics.drawLine(x1, y1, x2, y2);
			
		}
		
		
		
		
	}
	
	public static BufferedImage getBufferedImage() {
	  

		BufferedImage bufferedImage = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_BGR);

		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(Color.WHITE);
       
		

		graphics.draw3DRect(0, 0, 80, 30,true);
		
		String checkcode = getNumber();
		
		StringBuffer stringBuffer = new StringBuffer();
		
		for (int i = 0; i < checkcode.toCharArray().length; i++) {
			stringBuffer.append(checkcode.charAt(i)+" ");
		}
		
		graphics.setFont(new Font("serif",Font.BOLD,20));
		graphics.setColor(getColor());
		
		graphics.drawString(stringBuffer.toString(), 15, 20);
		drawLine(graphics);
		
		graphics.dispose();
		
		return bufferedImage;
	}
	
}
