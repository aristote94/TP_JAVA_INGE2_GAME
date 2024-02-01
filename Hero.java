import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public final class Hero extends DynamicThings {
    private static volatile Hero instance = null;
    protected Sortilege sortilege = null;

    private Boolean isWalking = false;
    private Boolean shrinkagePotion = false;


    private Hero() {
        super(50,48, 60,80,4,10,150);
        this.sortilege=new Sortilege(50,48, 60,80,4,10,150);
        try{this.setImage(ImageIO.read(new File("img/heroTileSheetLowRes.png")));}
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public Boolean isWalking() {
        return isWalking;
    }



    public void setWalking(boolean walking) {
        isWalking = walking;
        animationRunning = walking;
    }

  /*  private long potionTimeStart=0;
    public void useShrinkagePotion() {
        scaleFactor = 0.5;
        potionTimeStart=System.currentTimeMillis();
        shrinkagePotion=true;
        this.getHitBox().setWidth(width*scaleFactor);
        this.getHitBox().setHeight(height*scaleFactor);
    }

    public void checkEndOfPotion() {
        if (System.currentTimeMillis()-potionTimeStart>1500){
            scaleFactor=0.8;
            shrinkagePotion=false;
            this.getHitBox().setWidth(width*scaleFactor);
            this.getHitBox().setHeight(height*scaleFactor);
        }
    }

   */

    /**
     * Constructeur de l'objet.
     */
  /*  private Hero(){
        super(32,32,70,100);
        try{
            BufferedImage localImage = ImageIO.read(new File("./img/tileSetTest.png"));
            this.image= localImage.getSubimage(32,32,32,32);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    };
    */

    /*public Hero(int x, int y, Image image) {
        super(x,y,image);

    }*/


    /**
     * M´ethode permettant de renvoyer une instance de la classe Singleton
     *
     * @return Retourne l'instance du singleton.
     */
    public final static Hero getInstance() {

        if (Hero.instance == null) {
            synchronized (Hero.class) {
                if (Hero.instance == null) {
                    Hero.instance = new Hero();
                }
            }
        }
        return Hero.instance;

    }

    public void setAttitude(){
        currentAttitude= getOrientation().getI();
    }




    // D'autres m´ethodes classiques et non "static".

}


