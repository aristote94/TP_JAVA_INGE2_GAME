package TP2D;

import javax.swing.*;
import java.awt.*;

public class GameRender extends JPanel {
    private Dungeon dungeon;
    private Hero hero;
    public boolean showStartMessage = true;
    public boolean showEndMessage = false;
    public boolean showTimer = false;
    private String timeString = "00.000s";
    private long sec;
    public GameRender(Dungeon dungeon, DynamicThings hero) {
        this.dungeon = dungeon;
        this.hero = Hero.getInstance();
    }

    private void drawStartMessage(Graphics g) {
        g.setFont(new Font("SansSerif", Font.BOLD, 60));
        g.setColor(Color.red);
        String message = "MOVE TO START";
        int x = (getWidth() - g.getFontMetrics().stringWidth(message)) / 2;
        int y = getHeight() / 2;
        g.drawString(message, x, y);
    }
    private void drawEndMessage(Graphics g) {
        g.setFont(new Font("SansSerif", Font.BOLD, 60));
        g.setColor(Color.red);
        String message;

        if (sec<18)
            message = "PRETTY NICE MY BOY !!";
        else if (sec>21)
            message = "REALLY SHITTY TIME";
        else message = "YEAH...PRETTY AVERAGE PERFORMANCE";
        int x = (getWidth() - g.getFontMetrics().stringWidth(message)) / 2;
        int y = getHeight() / 2;
        g.drawString(message, x, y);
    }
    private void drawTime(Graphics g) {
        g.setFont(new Font("SansSerif", Font.BOLD, 40));
        g.setColor(Color.green);
        int x = (getWidth() - g.getFontMetrics().stringWidth(timeString)) / 2;
        int y = getHeight() / 2;
        g.drawString(timeString, x, y+70);
    }

    public void setTimeString(String time) {
        this.timeString = time;
        repaint(); // Rafraîchit le panneau pour afficher le nouveau temps
    }
    public void sendsec(long sec){
        this.sec = sec;
    }
    public void setShowStartMessage(boolean show) {
        this.showStartMessage = show;
        repaint(); // Rafraîchit le panneau pour montrer ou cacher le message
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Things t : dungeon.getRenderList()){
            t.draw(g);
        }
        hero.draw(g);
        if (showStartMessage) {
            drawStartMessage(g);
        }
        if (showEndMessage) {
            drawEndMessage(g);
        }
        if (showTimer) {
            drawTime(g);
        }
    }
}
