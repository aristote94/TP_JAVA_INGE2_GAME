import java.awt.*;

public class Things {
    protected int height,width;
    protected int x,y;
    protected Image image;

    public Things(int height, int width, int x, int y) {
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    public Things(int x, int y, Image image) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }
    public void draw(Graphics g){
        g.drawImage(image,(int) x,(int) y,null);
    }
}
