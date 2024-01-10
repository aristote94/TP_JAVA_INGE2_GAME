public class Main {
    public static void main(String[] args) {
        // taille de tuile arbitraire
        TileManager tileManager = new TileManager(1, 1);

        // position spécifique
        HitBox heroHitBox = new HitBox(2, 2, 5, 5);

        //HitBox other = new HitBox(10, 10, 2, 5);

        // hauteur une largeur et le TileManager
        Dungeon dungeon = new Dungeon(15, 15, tileManager);

        dungeon.displayDungeonInConsole(heroHitBox);

    }
}
