public class SolidThings extends Things{

    public SolidThings(int height, int width, int x, int y) {
        super(height, width, x, y);
        hitBox = new HitBox(height,width,x,y);
    }

    protected HitBox hitBox;


}
