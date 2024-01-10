public class HitBox extends SolidThings{
    private final double height, width, x, y;
    private String name;


    public HitBox(double height,double width,double x, double y){// Dispose
        super((int) height, (int) width, (int)x,(int) y);
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }

    public boolean intersect(HitBox anotherHitBox) {


        // estce que rectangles touchent sur x
        boolean xOverlap = this.x < anotherHitBox.x + anotherHitBox.width &&
                this.x + this.width > anotherHitBox.x;

        // idem sur y
        boolean yOverlap = this.y < anotherHitBox.y + anotherHitBox.height &&
                this.y + this.height > anotherHitBox.y;

        return xOverlap && yOverlap;
    }


    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

}
