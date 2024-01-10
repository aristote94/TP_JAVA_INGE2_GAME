public class HitBox {
    private final double height, width, x, y;
    private String name;


    public HitBox(double height,double width,double x, double y){  // Dispose
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
        return 0;
    }
    public double getY(){
        return 0;
    }

}
