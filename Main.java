public class Main {
    public static void main(String[] args) {
        // taille de tuile arbitraire
        TileManager tileManager = new TileManager(1, 1);

        // position spécifique
        HitBox heroHitBox = new HitBox(2, 2, 5, 5);

        //HitBox other = new HitBox(10, 10, 2, 5);
        Hero h = Hero.getInstance();
        Hero h1 = Hero.getInstance();
        Hero h2 = Hero.getInstance();
        Hero h3 = Hero.getInstance();

        // hauteur une largeur et le TileManager
        Dungeon dungeon = new Dungeon(15, 15, tileManager);

        dungeon.displayDungeonInConsole(heroHitBox);

    }
}
