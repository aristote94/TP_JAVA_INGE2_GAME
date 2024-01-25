import java.awt.*;

public class AnimatedThings extends SolidThings{
    public AnimatedThings(int height, int width, int x, int y) {
        super(height, width, x, y);
    }
    public AnimatedThings(int x, int y, Image image) {
        super(x,y,image);

    }
    @Override
    public void draw(Graphics g){
        g.drawImage(image,(int) x,(int) y,null);
    }
}
