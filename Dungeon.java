import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Dungeon {
    private final char[][] dungeonArray;
    private int height;
    private int width;
    private TileManager tileManager;
    private ArrayList<Things> renderList = new ArrayList<>();

    public ArrayList<Things> getRenderList() {
        return renderList;
    }

    //renderList.clear();
    private void fillRenderList() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (dungeonArray[i][j]) {
                    case 'H':
                        renderList.add(new DynamicThings(1, 1, j * tileManager.getWidth(), i * tileManager.getHeight()));
                        break;
                    case 'W':
                        renderList.add(new SolidThings(1, 1, j * tileManager.getWidth(), i * tileManager.getHeight()));
                        break;
                    case ' ':
                        renderList.add(new Things(1, 1, j * tileManager.getWidth(), i * tileManager.getHeight()));
                        break;

                }

            }
        }

    }

    public Dungeon(int height, int width, TileManager tileManager) {
        this.height = height;
        this.width = width;
        this.tileManager = tileManager;
        this.dungeonArray = new char[height][width];
        initializeDungeon();
        respawnListOfThings();

    }
    public Dungeon(String filename, TileManager tileManager) {
        this.tileManager = tileManager;
        this.dungeonArray = new char[11][31];
        initializeDungeon();
        respawnListOfThings();

    }


    private void initializeDungeon() {
        // Initialisation du tableau du donjon avec ' ' à l'intérieur et 'W' sur les bords
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    dungeonArray[i][j] = 'W'; // Wall sur les bords
                } else {
                    dungeonArray[i][j] = ' '; // Espace à l'intérieur
                }

            }
        }
        this.fillRenderList();
    }

    private void initializeDungeon(String filename,TileManager tileManager){

        this.tileManager= tileManager;

        try {
            FileReader fr = new FileReader(filename);
            // Créer l'objet BufferedReader

            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            while((line = br.readLine()) != null)
            {
                // ajoute la ligne au buffer
                sb.append(line);
                sb.append("\n");
            }
            fr.close();
            System.out.println("Contenu du fichier: ");
            System.out.println(sb.toString());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }


    public void displayDungeonInConsole(HitBox hero) {
        // Affichage du tableau du donjon dans la console avec 'H' à la position du héros
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int tileX = j * tileManager.getWidth();
                int tileY = i * tileManager.getHeight();

                if (hero != null && (tileX == hero.getX() && tileY == hero.getY()) && this.dungeonArray[i][j] == ' ') {
                    System.out.print("H ");
                } else {
                    System.out.print(dungeonArray[i][j] + " ");
                }
            }
            System.out.println();


        }
    }

    public void respawnListOfThings() {
        renderList.clear(); // Vide la liste existante

        // Scan la map et génère de nouveaux objets dans la liste
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int tileX = j * tileManager.getWidth();
                int tileY = i * tileManager.getHeight();

                switch (dungeonArray[i][j]) {
                    case 'H':
                        renderList.add(new DynamicThings(1, 1, tileX, tileY));
                        break;
                    case 'W':
                        renderList.add(new SolidThings(1, 1, tileX, tileY));
                        break;
                    case ' ':
                        renderList.add(new Things(1, 1, tileX, tileY));
                        break;
                    // Ajoutez d'autres cas selon vos besoins
                }
            }
        }

    }


}

