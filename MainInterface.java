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
        this.setSize(new Dimension(400,600));
    }
    public static void main(String[]args){

        MainInterface mainInterface= new MainInterface();



    }

}
