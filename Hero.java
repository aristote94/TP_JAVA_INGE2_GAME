import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public final class Hero extends DynamicThings {
    private static volatile Hero instance = null;

    private Boolean isWalking = false;


    private Hero() {
        super(120,120, 48,52,40,3,150);
        try{this.setImage(ImageIO.read(new File("img/heroTileSheet.png")));}
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public Boolean getWalking() {
        return isWalking;
    }



    public void setWalking(boolean walking) {
        isWalking = walking;
    }

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



    // D'autres m´ethodes classiques et non "static".

}


