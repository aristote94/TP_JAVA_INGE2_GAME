import java.awt.*;

public class AnimatedThings extends SolidThings{

    protected int currentAttitude;
    protected boolean animationRunning;

    private int numberOfAttitude;
    private int numberOfFrames;
    private double timeBetweenFrames;
    public AnimatedThings(int height, int width, int x, int y, int numberOfAttitude, int numberOfFrames, double timeBetweenFrames) {
        super(height, width, x, y);
        this.numberOfAttitude = numberOfAttitude;
        this.numberOfFrames = numberOfFrames;
        this.timeBetweenFrames = timeBetweenFrames;
    }
    public AnimatedThings(int x, int y, Image image) {
        super(x,y,image);
    }

    public void setCurrentAttitude(int attitude) {
        this.currentAttitude = attitude;
    }

    @Override
    public void draw(Graphics g) {
        int index = (int) ((System.currentTimeMillis() / timeBetweenFrames) % numberOfFrames);
        index = animationRunning ? index : 0;
        g.drawImage(
                image,
                (int) x, (int) y,
                (int) x + 48, (int) y + 52,
                index * 96, 100 * currentAttitude,
                (index + 1) * 96, 100 * (currentAttitude + 1),
                null, null
        );
    }
}
