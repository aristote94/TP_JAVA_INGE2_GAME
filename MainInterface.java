package TP2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainInterface extends JFrame implements KeyListener {
    private TileManager tileManager;
    private Dungeon dungeon;
    private Hero hero;
    private GameRender panel;

    public MainInterface() {
        super("Dungeon Game");
        tileManager = new TileManager(48, 48, "./tileSet.png");
        dungeon = new Dungeon("./level1.txt", tileManager);
        hero = Hero.getInstance();
        panel = new GameRender(dungeon, hero);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panel);
        setSize(dungeon.getWidth() * tileManager.getWidth(), dungeon.getHeight() * tileManager.getHeight());
        addKeyListener(this);
        setVisible(true);

        initializeAnimationTimer();
    }

    private void initializeAnimationTimer() {
        final int speed = 10;
        ActionListener animationTimer = e -> {
            repaint();
            if (hero.isWalking()) {
                moveHero(speed);
            }
        };

        Timer timer = new Timer(50, animationTimer);
        timer.start();
    }

    private void moveHero(int speed) {
        switch (hero.getOrientation()) {
            case LEFT:
                hero.moveIfPossible(-speed, 0, dungeon);
                break;
            case RIGHT:
                hero.moveIfPossible(speed, 0, dungeon);
                break;
            case UP:
                hero.moveIfPossible(0, -speed, dungeon);
                break;
            case DOWN:
                hero.moveIfPossible(0, speed, dungeon);
                break;
        }
    }

    public static void main(String[] args) {
        new MainInterface();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not implemented
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                hero.setOrientation(Orientation.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setOrientation(Orientation.RIGHT);
                break;
            case KeyEvent.VK_UP:
                hero.setOrientation(Orientation.UP);
                break;
            case KeyEvent.VK_DOWN:
                hero.setOrientation(Orientation.DOWN);
                break;
        }
        hero.setWalking(true);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        hero.setWalking(false);
    }
}
