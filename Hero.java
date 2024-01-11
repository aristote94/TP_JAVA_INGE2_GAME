public final class Hero extends DynamicThings {
    private static volatile Hero instance = null;

    /**
     * Constructeur de l'objet.
     */
    private Hero(){
        super(32,32,50,50);
    };


    /**
     * M´ethode permettant de renvoyer une instance de la classe Singleton
     *
     * @return Retourne l'instance du singleton.
     */
    public final static Hero getInstance() {

        if (Hero.instance == null) {
            synchronized (Hero.class) {
                if (Hero.instance == null) {
                    Hero.instance = new Hero();
                }
            }
        }
        return Hero.instance;


    }



    // D'autres m´ethodes classiques et non "static".

}


