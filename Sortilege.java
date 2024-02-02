import java.awt.*;

public class Sortilege extends AnimatedThings{


    protected boolean checkSortilege = false;


    protected long timerSortilege=0;







    public Sortilege(int height, int width, int x, int y,int numberOfAttitude, int numberOfFrames, double timeBetweenFrames ) {
        super(height, width, x, y,numberOfAttitude,numberOfFrames,timeBetweenFrames);
    }
    public void useShrinkagePotion() {                 // POTION  DE  SHRINK
        Hero.getInstance().scaleFactor = 0.5; // rétréssissement du sprite
        //potionTimeStart=System.currentTimeMillis();
        timerSortilege=System.currentTimeMillis();

        //shrinkagePotion=true;
        checkSortilege = true;
        Hero.getInstance().getHitBox().setWidth(width*Hero.getInstance().scaleFactor);  // rétréssissement de la hitbox en largeur
        Hero.getInstance().getHitBox().setHeight(height*Hero.getInstance().scaleFactor);  // rétréssissement de la hitbox en hauteur
    }
   public void checkEndOfPotion() {
        if (System.currentTimeMillis()-potionTimeStart>1500){
            Hero.getInstance().scaleFactor=0.8;
            shrinkagePotion=false;
            Hero.getInstance().getHitBox().setWidth(width*scaleFactor);
            Hero.getInstance().getHitBox().setHeight(height*scaleFactor);
        }
    }




    public void running(){                    //POTION DE RUN
        Hero.getInstance().speed = 17;//vitesse run
        timerSortilege=System.currentTimeMillis();
        checkSortilege=true;
    }
    public void speedZone(){             //ZONE DE SPEED
        Hero.getInstance().speed =22;// Future speed zone(pas finalisée)
        timerSortilege=System.currentTimeMillis();
        checkSortilege=true;
    }



    public void slowZoneIn(){           //ZONE DE SLOW
        Hero.getInstance().speed = 2; //vitesse ralentit
        timerSortilege=System.currentTimeMillis();
        checkSortilege=true;
    }



    public void checkEndSortilge(){    // Fonction de fin de sortilege
        if (System.currentTimeMillis()-timerSortilege>1000){
            Hero.getInstance().speed =10; // remise vitesse défault
            checkSortilege = false;
            Hero.getInstance().scaleFactor=0.8; // remise taille sprite
            //shrinkagePotion=false;
            Hero.getInstance().getHitBox().setWidth(width*scaleFactor); // remise taille hitbox largeur
            Hero.getInstance().getHitBox().setHeight(height*scaleFactor);// remise taille hitbox hauteur
        }
    }

}
