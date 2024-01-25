import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class TileManager {
    private int height, width;
    private Image[][] tiles;
    private BufferedImage tileSheet;

    public TileManager(int width, int height) {
        this.width = width;
        this.height= height;
        setTiles(width, height, "./img/tileSetTest.png");
    }


    public TileManager( int height, int width, String fileName ) {
        this.height = height;
        this.width = width;
        setTiles(width, height, fileName);

    }
    private void setTiles(int width, int height, String fileName){
        try{
            tileSheet = ImageIO.read(new File(fileName));}
        catch (Exception e){
            e.printStackTrace();
        }
        tiles = new Image[tileSheet.getWidth()/width][tileSheet.getHeight()/height];
        for(int y=0;y+height<=tileSheet.getHeight();y=y+height) {
            for (int x = 0; x +width <= tileSheet.getWidth(); x = x + width) {
                tiles[x / width][y / height] = tileSheet.getSubimage(x, y, width, height);
            }
        }
    }
    public Image getTile(int x, int y) {
        // Vérifie si les coordonnées sont valides
        if (x >= 0 && x < tiles.length && y >= 0 && y < tiles[0].length) {
            return tiles[x][y];
        } else {
            // Si les coordonnées sont en dehors des limites, retourne null ou une tuile par défaut
            return null;  // ou une image par défaut
        }
    }
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
