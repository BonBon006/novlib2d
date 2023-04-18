package novlib2d.template;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import novlib2d.algorithm.Logic;
import novlib2d.base.Game;
import novlib2d.graphics.SpriteAnimation;

public class GameTest extends Game {

    @Override
    public void initialize() {
        gameHelper.enable();

        gameHelper.core.addPlayerData(new Logic(), "player", 256, 256, 3,
                SpriteAnimation.DEFAULT_FRAME_COUNT, 150, 50, 70, 2.5f,
                false, false);

        gameHelper.apply();
    }

    @Override
    public void load() {
        gameHelper.load("player.png", Texture.class, true);
        gameHelper.load("player.jpg", Texture.class, false);
    }

    @Override
    public void update(float deltaTime) {
        gameHelper.update(deltaTime);
    }

    @Override
    public void draw() {
        ScreenUtils.clear(Color.LIGHT_GRAY);
        gameHelper.draw();
    }

    @Override
    public void dispose() {
        gameHelper.dispose();
    }
}
