public class HitBox{
    private double height, width;
    private double x, y;
    private String name;

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public HitBox(double height, double width, double x, double y){// Dispose
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

    public void move(double dx, double dy){
        x=x+dx;
        y=y+dy;
    }

}
