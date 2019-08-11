package com.PIC;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;


import javax.imageio.ImageIO;

public class Render {
	public static String downloadImage(String topText, String botText, String imgURL) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		BufferedImage image = null;
		BufferedImage img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		String binaryData = null;

		/*--- TRYING TO DOWNLOAD IMAGE INTO IMAGE FILE ---*/
		try {
			URL url = new URL(imgURL);
			image = ImageIO.read(url);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		/*--- CHECK IF IMAGE DOWNLOAD WAS SUCCESFOUL IF NOT DOWNLOAD SAFE IMAGE---*/
		if (image == null) {
			try {
				image = ImageIO.read(new File("safeIMG.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/*--- WRITES DOWNLOAD IMAGE ON PREPEARED FRAME---*/
		g2d.drawImage(image, 0, 200, 1000, 600, null);

		/*--- WRITES TOP TEXT ON PREPEARED FRAME---*/
		g2d.setPaint(Color.white);
		g2d.setFont(new Font("Serif", Font.BOLD, 55));
		FontMetrics fm = g2d.getFontMetrics();
		int x = img.getWidth() / 2 - fm.stringWidth(topText) / 2;
		g2d.drawString(topText, x, 100);

		/*--- WRITES BOTTOM TEXT ON PREPEARED FRAME---*/
		g2d.setFont(new Font("Serif", Font.BOLD, 30));
		int curLoc = -1;
		int asd = 850;
		for (int i = 0; i < botText.length(); i++) {
			if (i + 65 < botText.length()) {
				if (botText.charAt(i + 65) == ' ') {
					g2d.drawString(botText.substring(curLoc + 1, i + 65), 15, asd);
					curLoc = i + 65;
					i = i + 65;
					asd = asd + 27;
				}
			} else {
				g2d.drawString(botText.substring(curLoc + 1, botText.length()), 15, asd);
				break;
			}
		}
		
		/*--- CONVERTING BUFFERED IMAGE FILE TO BASE64 FILE---*/
		try {
			ImageIO.write(img, "jpg", os);
			binaryData = Base64.getEncoder().encodeToString(os.toByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return binaryData; 
		 
		
	}
	
}
