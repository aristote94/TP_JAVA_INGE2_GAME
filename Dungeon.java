import java.util.ArrayList;

public class Dungeon {
    private final char[][] dungeonArray;
    private final int height;
    private final int width;
    private final TileManager tileManager;
    private ArrayList<Things> renderList = new ArrayList<>();

    public ArrayList<Things> getRenderList() {
        return renderList;
    }

    private void fillRenderList(){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                switch (dungeonArray[i][j]) {
                    case 'H' : renderList.add(new DynamicThings(1,1,j * tileManager.getWidth(),i * tileManager.getHeight()));
                                 break;
                    case 'W' :  renderList.add(new SolidThings(1,1,j * tileManager.getWidth(),i * tileManager.getHeight()));
                                break;
                    case ' ' :  renderList.add(new Things(1,1,j * tileManager.getWidth(),i * tileManager.getHeight()));
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


    public void displayDungeonInConsole(HitBox hero) {
        // Affichage du tableau du donjon dans la console avec 'H' à la position du héros
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int tileX = j * tileManager.getWidth();
                int tileY = i * tileManager.getHeight();

                if (hero!=null && (tileX== hero.getX() && tileY== hero.getY()) && this.dungeonArray[i][j] ==' ') {
                    System.out.print("H ");
                } else {
                    System.out.print(dungeonArray[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}

