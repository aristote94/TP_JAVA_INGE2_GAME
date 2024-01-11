import java.awt.*;

public class DynamicThings extends AnimatedThings{

    private double speedX,speedY;


    public DynamicThings(int height, int width, int x, int y) {
        super(height, width, x, y);
    }
    public DynamicThings(int x, int y, Image image) {
        super(x,y,image);

    }
    public void moveIfPossible(double dx, double dy, Dungeon dungeon) {

        Boolean movePossible = true;

        this.hitBox.move(dx,dy);
        // Vérifie si la nouvelle position du héros ne provoque pas de collision avec les murs du donjon
        for (Things things : dungeon.getRenderList()){
            if (things instanceof SolidThings){
                if(((SolidThings) things).hitBox.intersect(this.hitBox)){
                    movePossible=false;
                    break;
                }
            }
        }

        if(movePossible){
            this.x=(int) (this.x+dx);
            this.y=(int) (this.y+dy);
        }
        else {
            hitBox.move(-dx,-dy);

        }
    }

}
