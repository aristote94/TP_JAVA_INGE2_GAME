import java.awt.*;

public class SolidThings extends Things{

    public SolidThings(int height, int width, int x, int y) {
        super(height, width, x, y);
        hitBox = new HitBox(height,width,x,y);
    }
    public SolidThings(int x, int y, Image image) {
        super(x,y,image);

    }

    protected HitBox hitBox;


}
