import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;



public class Main {
    public static void main(String[] args) {

        // taille de tuile arbitraire

        TileManager tileManager = new TileManager(32, 32, "./img/tileSetTest.png");
        Image tile = tileManager.getTile(32, 32);


        // position spécifique
        HitBox heroHitBox = new HitBox(2, 2, 5, 5);

        //HitBox other = new HitBox(10, 10, 2, 5);
        Hero h = Hero.getInstance();


        // hauteur une largeur et le TileManager
        Dungeon dungeon = new Dungeon(15, 15, tileManager);



        dungeon.displayDungeonInConsole(heroHitBox);

        h.moveIfPossible(3,3,dungeon);
        dungeon.displayDungeonInConsole(h.hitBox);


    }
}
