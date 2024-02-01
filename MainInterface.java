import javax.swing.*;
import com.sun.tools.javac.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class MainInterface extends JFrame implements KeyListener{
    //private GameRender gameRender;
    GameRender gameRender;
    private Timer gameTimer;
    private long startTime;
    private JLabel timerLabel = new JLabel("Time : 0");
    private boolean gameStarted;
    TileManager tileManager = new TileManager(48,48,"./img/tileSet2.png");
    Dungeon dungeon = new Dungeon("./gameData/level1.txt",tileManager);
    Hero hero = Hero.getInstance();

    public MainInterface() throws HeadlessException {

        super();
        gameRender = new GameRender(dungeon,hero);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.getContentPane().add(panel);
        this.setVisible(true);
        this.setSize(new Dimension(tileManager.getWidth()*dungeon.getWidth(),
                tileManager.getHeight()*dungeon.getHeight()));
        dungeon.displayDungeonInConsole(hero.hitBox);
        this.add(gameRender);
        getContentPane().add(timerLabel, BorderLayout.NORTH);
        gameStarted = false;
        /*while (true)
           gameRender.paintComponent(getGraphics());*/
        ActionListener rendering = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Hero.getInstance().getEndGame()){
                    gameTimer.stop();
                    gameRender.showEndMessage = true;
                    gameRender.showTimer = true;
                }
                repaint();
                hero.sortilege.checkEndOfPotion();
                hero.sortilege.checkEndSortilge();


                if (hero.isWalking()){
                    switch (hero.getOrientation()){
                        case LEFT:  hero.moveIfPossible(-hero.speed,0,dungeon);
                            break;
                        case RIGHT: hero.moveIfPossible(hero.speed,0,dungeon);
                            break;
                        case UP:    hero.moveIfPossible(0,-hero.speed,dungeon);
                            break;
                        case DOWN:  hero.moveIfPossible(0,hero.speed,dungeon);
                            break;

                    }

                }
            }
        };
        Timer animationTimer = new Timer(50,rendering);
        animationTimer.start();
        this.addKeyListener(this);


    }

    private void startGameTimer() {
        startTime = System.currentTimeMillis();
        gameTimer = new Timer(1, e -> updateTimer()); // Déclenche toutes les millisecondes
        gameTimer.start();
    }


    private void updateTimer() {
        long elapsedTimeMillis = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTimeMillis / 1000;
        long elapsedMillis = elapsedTimeMillis % 1000;

        timerLabel.setText(String.format("Time: %d.%03d", elapsedSeconds, elapsedMillis));
        String time = String.format("%02d.%03ds", elapsedSeconds % 60, elapsedMillis);
        gameRender.sendsec(elapsedSeconds);
        gameRender.setTimeString(time);
    }
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (!gameStarted) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                    startGameTimer();
                    gameStarted = true;
                    gameRender.setShowStartMessage(false);
                    gameRender.repaint();
                    break;
            }
        }
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                handleDirectionKey(keyCode);
                break;
            case KeyEvent.VK_P:
                hero.sortilege.useShrinkagePotion();
                break;
            case KeyEvent.VK_SHIFT:
                hero.sortilege.running();
                break;

        }

        this.repaint();
    }

    private void handleDirectionKey(int keyCode) {
        Orientation orientation = getOrientationFromKeyCode(keyCode);
        hero.setOrientation(orientation);
        hero.setAttitude();
        hero.setWalking(true);
    }

    private Orientation getOrientationFromKeyCode(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                return Orientation.LEFT;
            case KeyEvent.VK_RIGHT:
                return Orientation.RIGHT;
            case KeyEvent.VK_UP:
                return Orientation.UP;
            case KeyEvent.VK_DOWN:
                return Orientation.DOWN;
            default:
                throw new IllegalArgumentException("Invalid key code for direction: " + keyCode);
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                hero.setWalking(false);
                break;
        }

        this.repaint();
    }

    public static void main(String[]args){

        MainInterface mainInterface= new MainInterface();



    }

}
