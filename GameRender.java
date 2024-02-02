import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameRender extends JPanel {
    private Dungeon dungeon;
    private Hero hero;
    public boolean showStartMessage = true;
    public boolean showEndMessage = false;
    public boolean showTimer = false;
    public boolean showBonus = false;
    private String timeString = "00.000s";

    private boolean bonusMessage =false;
    private long bonusMessageTimer=0;
    private long sec;
    public GameRender(Dungeon dungeon, DynamicThings hero) {
        this.dungeon = dungeon;
        this.hero = Hero.getInstance();
    }
    private void drawStartMessage(Graphics g) {
        g.setFont(new Font("SansSerif", Font.BOLD, 60));
        g.setColor(Color.black);
        String message = "MOVE TO START";
        String message2 = "MOVE TO START";
        int x = (getWidth() - g.getFontMetrics().stringWidth(message)) / 2;
        int y = getHeight() / 2;
        g.drawString(message2, x+3, y+3);
        g.setColor(Color.red);
        g.drawString(message, x, y);
    }
    private void drawEndMessage(Graphics g) {
        g.setFont(new Font("SansSerif", Font.BOLD, 60));
        g.setColor(Color.white);
        String message;
        //condition d'affichage du succès
        if (sec<18)
            message = "PRETTY NICE MY BOY !!";
        else if (sec>21)
            message = "REALLY SHITTY TIME";
        else message = "YEAH...PRETTY AVERAGE PERFORMANCE";
        //affichage du message de fin
        int x = (getWidth() - g.getFontMetrics().stringWidth(message)) / 2;
        int y = getHeight() / 2;
        g.drawString(message, x, y);
    }
    private void printBonusMessage(Graphics g) {
        g.setFont(new Font("SansSerif", Font.BOLD, 60));
        g.setColor(Color.GREEN);
        String message=null;
        //condition d'affichage du succès
        if (Hero.getInstance().getBonusMessage())
            message = "Bonus : -10sec";

        //affichage du message de fin
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
        Hero.getInstance().draw(g);
        if (showStartMessage) {
            drawStartMessage(g);
        }
        if (showEndMessage) {
            drawEndMessage(g);
        }
        if (showTimer) {
            drawTime(g);
        }
        if(showBonus){
            printBonusMessage(g);
        }
    }

}