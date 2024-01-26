import java.awt.*;

public class AnimatedThings extends SolidThings{

    protected int currentAttitude;
    protected boolean animationRunning;

    protected double scaleFactor = 1;

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
                (int) (x + width*scaleFactor), (int) (y + height*scaleFactor),
                index * width, height * currentAttitude,
                (index + 1) * width, height * (currentAttitude + 1),
                null, null
        );
        g.setColor(Color.RED);
        g.drawRect((int) hitBox.getX(),(int) hitBox.getY(),(int) hitBox.getWidth(),(int) hitBox.getHeight());
    }
}
