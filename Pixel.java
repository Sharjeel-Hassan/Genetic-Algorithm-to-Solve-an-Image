import java.util.Random;

public class Pixel {
    public int r;
    public int g;
    public int b;

    Pixel(int red, int green, int blue){
        r = red;
        g = green;
        b = blue;
    }

    public void generateRandomPixel(){
        Random rand = new Random();
        r = rand.nextInt();
        g = rand.nextInt();
        b = rand.nextInt();
    }
}
