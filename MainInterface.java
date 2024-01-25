import javax.swing.*;
import com.sun.tools.javac.Main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class MainInterface extends JFrame {
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
            }
        };
        Timer animationTimer = new Timer(50,rendering);
        animationTimer.start();
    }
    public static void main(String[]args){

        MainInterface mainInterface= new MainInterface();



    }

}
