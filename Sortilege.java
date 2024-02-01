import java.awt.*;

public class Sortilege extends AnimatedThings{

    protected Boolean Slowzone =false;

    protected boolean checkRun = false;
    protected long timerRunning =0;





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
        Hero.getInstance().speed = 20;
        timerRunning=System.currentTimeMillis();
        checkRun=true;
    }

    public void checkEndRun(){
        if (System.currentTimeMillis()-timerRunning>1500){
           Hero.getInstance().speed =10;
            checkRun = false;
        }
    }





}
