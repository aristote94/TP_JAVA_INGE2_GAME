import java.awt.*;

public class TrapThings extends Things{


    public TrapThings(int height, int width, int x, int y) {
        super(height, width, x, y);
        hitBox = new HitBox(height,width,x,y);

    }

    public TrapThings(int x, int y, Image image) {
        super(x, y, image);
        this.hitBox=new HitBox(image.getWidth(null), image.getHeight(null),x,y);
    }
    protected HitBox hitBox;
    public HitBox getHitBox() {
        return hitBox;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        /*g.setColor(Color.WHITE);
        g.drawRect((int) hitBox.getX(), (int) hitBox.getY(),
                (int) hitBox.getWidth(),(int) hitBox.getHeight());*/
    }
}
