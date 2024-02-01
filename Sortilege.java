import java.awt.*;

public class Sortilege extends AnimatedThings{

    protected Boolean Slowzone =false;

    protected boolean checkSortilege = false;


    protected long timerSortilege=0;;






    public Sortilege(int height, int width, int x, int y,int numberOfAttitude, int numberOfFrames, double timeBetweenFrames ) {
        super(height, width, x, y,numberOfAttitude,numberOfFrames,timeBetweenFrames);
    }
    public void useShrinkagePotion() {
        scaleFactor = 0.5;
        potionTimeStart=System.currentTimeMillis();
        shrinkagePotion=true;
        Hero.getInstance().getHitBox().setWidth(width*scaleFactor);
        Hero.getInstance().getHitBox().setHeight(height*scaleFactor);
    }
    public void checkEndOfPotion() {
        if (System.currentTimeMillis()-potionTimeStart>1500){
            scaleFactor=0.8;
            shrinkagePotion=false;
            Hero.getInstance().getHitBox().setWidth(width*scaleFactor);
            Hero.getInstance().getHitBox().setHeight(height*scaleFactor);
        }
    }
    public void running(){
        Hero.getInstance().speed = 17;//vitesse run
        timerSortilege=System.currentTimeMillis();
        checkSortilege=true;
    }
    public void speedZone(){
        Hero.getInstance().speed =22;
        timerSortilege=System.currentTimeMillis();
        checkSortilege=true;
    }



    public void slowZoneIn(){
        Hero.getInstance().speed = 4; //vitesse ralentit
        timerSortilege=System.currentTimeMillis();
        checkSortilege=true;
    }

    public void checkEndSortilge(){
        if (System.currentTimeMillis()-timerSortilege>500){
            Hero.getInstance().speed =10; // remise à défault
            checkSortilege = false;
        }
    }

}
