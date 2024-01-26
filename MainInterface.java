import javax.swing.*;
import com.sun.tools.javac.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class MainInterface extends JFrame implements KeyListener{
    TileManager tileManager = new TileManager(32,32,"./img/tileSetTest.png");
    Dungeon dungeon = new Dungeon("./gameData/level1.txt",tileManager);
    Hero hero = Hero.getInstance();
    public MainInterface() throws HeadlessException {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.getContentPane().add(panel);
        this.setVisible(true);
        this.setSize(new Dimension(tileManager.getWidth()*dungeon.getWidth(),
                tileManager.getHeight()*dungeon.getHeight()));
        dungeon.displayDungeonInConsole(hero.hitBox);
        GameRender gameRender = new GameRender(dungeon,hero);
        this.add(gameRender);
        /*while (true)
           gameRender.paintComponent(getGraphics());*/
        ActionListener rendering = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                repaint();
                hero.checkEndOfPotion();

                final int speed=10;
                if (hero.isWalking()){
                    switch (hero.getOrientation()){
                        case LEFT:  hero.moveIfPossible(-speed,0,dungeon);
                            break;
                        case RIGHT: hero.moveIfPossible(speed,0,dungeon);
                            break;
                        case UP:    hero.moveIfPossible(0,-speed,dungeon);
                            break;
                        case DOWN:  hero.moveIfPossible(0,speed,dungeon);
                            break;

                    }
                }
            }
        };
        Timer animationTimer = new Timer(50,rendering);
        animationTimer.start();
        this.addKeyListener(this);


    }

    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                handleDirectionKey(keyCode);
                break;
            case KeyEvent.VK_P:
                hero.useShrinkagePotion();
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
