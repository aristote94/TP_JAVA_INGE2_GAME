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
    private Timer gameTimer;
    private long startTime;
    private JLabel timerLabel;
    private boolean gameStarted;
    private Rectangle slowZone = new Rectangle(2*48+1, 6*48+1, 48, 48);
    private int speed = 10;
    private boolean isShiftPressed = false;

    public MainInterface() {
        super("Dungeon Game");
        tileManager = new TileManager(48, 48, "./tileSet.png");
        dungeon = new Dungeon("./level1.txt", tileManager);
        hero = Hero.getInstance();
        panel = new GameRender(dungeon, hero);
        timerLabel = new JLabel("Time: 0");
        getContentPane().add(timerLabel, BorderLayout.NORTH);
        gameStarted = false;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panel);
        setSize(dungeon.getWidth() * tileManager.getWidth(), dungeon.getHeight() * tileManager.getHeight());
        addKeyListener(this);
        setVisible(true);
        initializeAnimationTimer();
    }

    private void initializeAnimationTimer() {
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
        checkForSlowZone();

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
        StopTimer();
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
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            isShiftPressed = true;
        }
        if (!gameStarted) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                    startGameTimer();
                    gameStarted = true;
                    panel.setShowStartMessage(false);
                    panel.repaint();
                    break;
            }
        }

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

    private void startGameTimer() {
        startTime = System.currentTimeMillis();
        gameTimer = new Timer(1, e -> updateTimer()); // Déclenche toutes les millisecondes
        gameTimer.start();
    }
    private void StopTimer() {
        int lastColumnX = (dungeon.getWidth() - 1) * tileManager.getWidth();
        if (hero.getX() >= lastColumnX) {
            gameTimer.stop();
            panel.showEndMessage = true;
            panel.showTimer = true;
        }
    }

    private void updateTimer() {
        long elapsedTimeMillis = System.currentTimeMillis() - startTime;
        long elapsedSeconds = elapsedTimeMillis / 1000;
        long elapsedMillis = elapsedTimeMillis % 1000;

        timerLabel.setText(String.format("Time: %d.%03d", elapsedSeconds, elapsedMillis));
        String time = String.format("%02d.%03ds", elapsedSeconds % 60, elapsedMillis);
        panel.sendsec(elapsedSeconds);
        panel.setTimeString(time);
    }

    private void checkForSlowZone() {
        if (slowZone.contains(hero.getX(), hero.getY())) {
            applySlowEffect();
        }
        else if (isShiftPressed) {
            speed = 17;
        }
        else speed = 10;
    }

    private void applySlowEffect() {
        setSpeed(2); // Définis une vitesse réduite
        Timer slowTimer = new Timer(1000, e -> resetHeroSpeed()); // Durée de 1000 ms (1 seconde)
        slowTimer.setRepeats(false);
        slowTimer.start();
    }

    private void resetHeroSpeed() {
        setSpeed(10); // Réinitialise la vitesse à la normale
    }

    private void setSpeed(int speedy){
        speed = speedy;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            isShiftPressed = false;
        }
        hero.setWalking(false);
    }
}