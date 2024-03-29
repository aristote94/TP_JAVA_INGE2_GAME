import java.awt.*;

public class DynamicThings extends AnimatedThings{

    private double speedX,speedY;
    private Orientation orientation=Orientation.RIGHT;

    private Boolean endGame = false;
    private int substractTime=0;
    private Boolean bonusMessage = false;



    public DynamicThings(int height, int width, int x, int y, int numberOfAttitude, int numberOfFrames, double timeBetweenFrames) {
        super(height, width, x, y,numberOfAttitude,numberOfAttitude,timeBetweenFrames);
    }
    public DynamicThings(int x, int y, Image image) {
        super(x,y,image);

    }
    public void setImage(Image image){
        this.image=image;
    }
    public Orientation getOrientation(){
        return orientation;
    }
    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }


    public Boolean getBonusMessage() {
        return bonusMessage;
    }

    public int getSubstractTime() {
        return substractTime;
    }

    public Boolean getEndGame() {
        return endGame;
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
            if (things instanceof TrapThings){
                if(((TrapThings) things).hitBox.intersect(this.hitBox)){
                    Hero.getInstance().sortilege.slowZoneIn();
                    break;
                }

            }
            if(things instanceof EndGameThings){
                if(((EndGameThings)things).hitBox.intersect(this.hitBox)){
                    endGame = true;

                    break;
                }
            }
            if(things instanceof TimeThings){
                if(((TimeThings)things).hitBox.intersect(this.hitBox)){

                    substractTime = 10000;
                    bonusMessage =true;

                    break;
                }
            }
            /*if(things instanceof BoostThings){
                if(((BoostThings)things).hitBox.intersect(this.hitBox)){
                    Hero.getInstance().sortilege.speedZone();
                    break;
                }
            }

             */

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


