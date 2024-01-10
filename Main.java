public class Main {
    public static void main(String[] args) {


        HitBox a = new HitBox(20,30,3,4);
        HitBox b = new HitBox(40,12,2,14);

        a.intersect(b);

    }
}