package novlib2d.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteAnimation {
    public static final int DEFAULT_FRAME_COUNT = 12;

    // for background - should add if needed
    public static final int  BACKGROUND = 0;

    // for characters
    public static final int IDLE = 0;
    public static final int WALKING_LEFT = 1;
    public static final int WALKING_RIGHT = 2;

    private Sprite sprite;
    private TextureRegion currentFrame;
    private int action;
    private int frameCount;
    private int currentFrameIndex;
    private float elapsedTime;

    public SpriteAnimation(Sprite sprite, int actionCount, int frameCount) {
        this.sprite = sprite;
        action = 0;
        this.frameCount = frameCount;
        currentFrameIndex = 0;
        elapsedTime = 0;
        currentFrame = new TextureRegion(
                this.sprite.getTexture(),
                0,0,
                this.sprite.getWidth(),
                this.sprite.getHeight());
    }

    public void updateFrame(float deltaTime) {
        elapsedTime += deltaTime;
        if (elapsedTime >= 1) {
            elapsedTime -= 1;
            currentFrame.setRegion(
                    currentFrameIndex * sprite.getWidth(),
                    action * sprite.getHeight(),
                    sprite.getWidth(),
                    sprite.getHeight());
            currentFrameIndex = (currentFrameIndex < frameCount - 1) ? currentFrameIndex + 1 : 0;
        }
    }

    public void reset() {
        action = 0;
        currentFrameIndex = 0;
        elapsedTime = 0;
        currentFrame.setRegion(0,0, sprite.getWidth(), sprite.getHeight());
    }

    public TextureRegion getCurrentFrame() { return currentFrame; }
}
