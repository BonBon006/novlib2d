package novlib2d.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import novlib2d.graphics.Sprite;

public class Entity {
    public static final int BACKGROUND = 0;
    public static final int OBJECT = 1;
    public static final int NPC = 2;
    public static final int PLAYER = 3;
    public static final int SPECIAL_NPC = 4;
    public static final int ENEMY_MOB = 5;
    public static final int ENEMY_BOSS = 6;

    protected Sprite sprite;
    protected int type;
    protected boolean isVisible = false;

    public int getType() { return type; }
    public boolean getIsVisible() { return isVisible; }

    public void setIsVisible(boolean isVisible) { this.isVisible = isVisible; }

    public void draw(SpriteBatch spriteBatch) {
        if (isVisible) {
            spriteBatch.draw(sprite.getAnimation().getCurrentFrame(), sprite.getPosition().getX(), sprite.getPosition().getY());
        }
    }
    public void update(float deltaTime) {}
}
