public class Dungeon {
    private final char[][] dungeonArray;
    private final int height;
    private final int width;
    private final TileManager tileManager;

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

