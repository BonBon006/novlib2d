package novlib2d.graphics;

import com.badlogic.gdx.graphics.Texture;
import novlib2d.utils.Point;

public class Sprite {
    private Texture texture;
    private Point position;
    private SpriteAnimation animation;
    private int width;
    private int height;

    public Sprite(Texture texture, int width, int height, int actionCount, int frameCount) {
        this.texture = texture;
        this.width = width;
        this.height = height;
        position = Point.zero();
        animation = new SpriteAnimation(this, actionCount, frameCount);
    }

    public Texture getTexture() { return texture; }
    public Point getPosition() { return position; }
    public SpriteAnimation getAnimation() { return animation; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public void setTexture(Texture texture) { this.texture = texture; }
    public void setPosition(Point position) { this.position = position; }
    public void setAnimation(SpriteAnimation animation) { this.animation = animation; }
    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
}
