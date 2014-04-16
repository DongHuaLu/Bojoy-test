package image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class DrawChar {
	Random random = new Random();
	static int width = 200;
	static int height = 100;
	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',  
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
            'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };  
	
	public static void main(String[] args) throws IOException {
		DrawChar draw = new DrawChar();
        BufferedImage tag = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        draw.fillImage(tag);
        String str = draw.getWordRandom();
        draw.addWord(tag, str);
        draw.drawDisturbLine(tag);
        BufferedImage twistImage = draw.twistImage(tag);
        ImageIO.write(twistImage, "jpg", new File("C:\\a.jpg"));
	}
	
	public String getWordRandom(){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<5;i++){
			sb.append(codeSequence[random.nextInt(codeSequence.length-1)]);
		}
		return sb.toString();
	}
	
	public void fillImage(BufferedImage tag){
        Graphics2D g = tag.createGraphics();
        g.setColor(Color.orange);
        g.fill(new Rectangle(0, 0, 200, 100));
	}
	/**
	 * 添加字符
	 * @param tag
	 * @param word
	 */
	public void addWord(BufferedImage tag,String word){
		Graphics2D g = tag.createGraphics();
        Font font = new Font(Font.SANS_SERIF,Font.BOLD, 60);
        g.setFont(font);
        int x = 20;
        int y = 65;
        g.setBackground(Color.WHITE);
        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        g.drawString(word, x, y);
        g.dispose();
	}
	/**
	 * 画干扰线
	 * @param tag
	 */
    private void drawDisturbLine(BufferedImage tag) {
    	Graphics2D graphics = tag.createGraphics();
        graphics.setColor(Color.BLACK);  
        int x = 0;  
        int y = 0;  
        int xl = 0;  
        int yl = 0;  
        for (int i = 0; i < 50; i++) {  
            x = random.nextInt(width);  
            y = random.nextInt(height);  
            xl = random.nextInt(40);  
            yl = random.nextInt(20);  
            graphics.drawLine(x, y, x + xl, y + yl);  
        }  
    }  
    
    private BufferedImage twistImage(BufferedImage tag) {  
        double dMultValue = random.nextInt(7) + 3;// 波形的幅度倍数，越大扭曲的程序越高，一般为3  
        double dPhase = random.nextInt(6);// 波形的起始相位，取值区间（0-2＊PI）  

        BufferedImage destBi = new BufferedImage(tag.getWidth(),  
        		tag.getHeight(), BufferedImage.TYPE_INT_RGB);  

        for (int i = 0; i < destBi.getWidth(); i++) {  
            for (int j = 0; j < destBi.getHeight(); j++) {  
                int nOldX = getXPosition4Twist(dPhase, dMultValue,  
                        destBi.getHeight(), i, j);  
                int nOldY = j;  
                if (nOldX >= 0 && nOldX < destBi.getWidth() && nOldY >= 0  
                        && nOldY < destBi.getHeight()) {  
                    destBi.setRGB(nOldX, nOldY, tag.getRGB(i, j));  
                }  
            }  
        }  
        return destBi;  
    } 
    
    private int getXPosition4Twist(double dPhase, double dMultValue,  
            int height, int xPosition, int yPosition) {  
        double PI = 9.1415926535897932384626433832799; // 此值越大，扭曲程度越大  
        double dx = (double) (PI * yPosition) / height + dPhase;  
        double dy = Math.sin(dx);  
        return xPosition + (int) (dy * dMultValue);  
    } 
}
