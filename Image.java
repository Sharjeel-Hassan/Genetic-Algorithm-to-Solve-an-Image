import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static java.lang.StrictMath.abs;

public class Image {

    public Pixel [][] img;
    public int width;
    public int height;
    public double fitnessValue;

    Image(){            //default constructor
        width = 0;
        height = 0;
        fitnessValue = 0;
    }

    Image(String path) throws IOException {     //parameterized constructor
        Random rand = new Random();
        BufferedImage load_image = ImageIO.read(new File(path));
        width = load_image.getWidth();
        height = load_image.getHeight();
        int r,g,b;
        Color c;
        img = new Pixel[width][height];
        for (int i = 0;i < width; i++){
            for (int j = 0;j < height; j++){
                c = new Color(load_image.getRGB(i,j));
                r = c.getRed();
                g = c.getGreen();
                b = c.getBlue();
                img[i][j] = new Pixel (r,g,b);
            }
        }
        fitnessValue = 0;
    }

    public void showImage(){        //displays image in JFrame
        int r,g,b;
        Color c;
        BufferedImage output_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int i = 0;i < width; i++){
            for (int j = 0;j < height; j++) {
                r = img[i][j].r;
                g = img[i][j].g;
                b = img[i][j].b;
                //System.out.println(i+" "+j+":"+r+" "+g+" "+b);
                c = new Color(r,g,b);
                output_image.setRGB(i,j,c.getRGB());
            }
        }
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(200,200);
        JLabel lbl=new JLabel();
        ImageIcon icon = new ImageIcon(output_image);
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    void fitnessFunction(Image p){       //sum of difference between each corresponding pixel
//        double sum = 0;
//        int temp1, temp2, temp3;
//        for (int i = 0;i < width; i++){
//            for (int j = 0;j < height; j++) {
//                temp1 = abs(p.img[i][j].r - img[i][j].r);
//                temp2 = abs(p.img[i][j].g - img[i][j].g);
//                temp3 = abs(p.img[i][j].b - img[i][j].b);
//                sum = temp1 + temp2 + temp3;
//            }
//        }
//        fitnessValue = sum;
//    }


}
