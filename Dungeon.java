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
    private char[][] dungeonArray;
    private int height;
    private int width;
    private TileManager tileManager;
    private ArrayList<Things> renderList = new ArrayList<>();

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<Things> getRenderList() {
        return renderList;
    }

    //renderList.clear();
    private void fillRenderList() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (dungeonArray[i][j]) {
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
        //this.height = 11;
        //this.width = 31;
        this.tileManager = tileManager;
        //this.dungeonArray = new char[this.height][this.width];
        initializeDungeon(filename,tileManager);
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
            String line=br.readLine();
            this.width=line.toCharArray().length;
            int i = 1;
            while((line = br.readLine()) != null) {
                i++;
            }
            i++;
            this.height=i;
            fr.close();
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            i=0;
            this.dungeonArray = new char[this.height][this.width];
            while((line = br.readLine()) != null)
            {
                // ajoute la ligne au buffer
                sb.append(line);
                sb.append("\n");
                this.dungeonArray[i++]=line.toCharArray();
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
                        //renderList.add(new DynamicThings(32, 32, tileX, tileY));
                        renderList.add(new DynamicThings(tileX, tileY, tileManager.getTile(1,0)));
                        break;
                    case 'W':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(0,6)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;

                    case 'M':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(0,14)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 'G':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(2,1)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;

                    case 'L':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(1,13)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 'R':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(1,12)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 'D':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(2,0)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 'T':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(0,13)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 't':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(1,14)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 'F':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(0,7)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 'Z':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(1,8)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 'V':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(2,10)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 'P':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(2,12)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                        case 'B':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(2,11)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                        case 'J':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(2,13)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 'i':
                        renderList.add(new SolidThings(tileX, tileY, tileManager.getTile(8,11)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 'a':
                        renderList.add(new BoostThings(tileX, tileY, tileManager.getTile(7,7)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 'p':
                        renderList.add(new TrapThings(tileX, tileY, tileManager.getTile(3,9)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;
                    case 'X':
                        renderList.add(new EndGameThings(tileX, tileY, tileManager.getTile(1,9)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;

                    case 'y':
                        renderList.add(new TimeThings(tileX, tileY, tileManager.getTile(3,0)));
                        //renderList.add(new SolidThings(32, 32, tileX, tileY));
                        break;



                    case ' ':
                        renderList.add(new Things(tileX, tileY, tileManager.getTile(1,9)));
                        //renderList.add(new Things(32, 32, tileX, tileY));
                        break;

                    case 'E':
                        renderList.add(new DynamicThings(tileX, tileY, tileManager.getTile(1,0)));
                        //renderList.add(new Things(32, 32, tileX, tileY));
                        break;
                    // Ajoutez d'autres cas selon vos besoins
                }
            }
        }

    }


}

