package novlib2d.entity.character;

import com.badlogic.gdx.graphics.Texture;
import novlib2d.algorithm.Logic;
import novlib2d.base.GameCore;
import novlib2d.entity.Entity;
import novlib2d.graphics.Camera;
import novlib2d.graphics.Sprite;
import novlib2d.ui.Inventory;

import java.util.ArrayList;

public class Player extends Character {
    private final Inventory inventory;

    public Player(GameCore core, Logic logic, String name, Texture texture, int width, int height, int actionCount,
                  int frameCount, float health, float mana, float stamina, float speed,
                  boolean skillsEnabled, boolean cameraEnabled) {
        sprite = new Sprite(texture, width, height, actionCount, frameCount);
        this.name = name;
        this.health = health;
        this.mana = mana;
        this.stamina = stamina;
        this.speed = speed;
        type = Entity.PLAYER;
        if (skillsEnabled) {
            skills = new ArrayList<>();
        }
        if (cameraEnabled) {
            camera = new Camera(sprite);
        }

        inventory = new Inventory(this, core.getInventorySize());
        this.logic = logic;
        isVisible = true;
    }

    @Override
    public void update(float deltaTime) {
        logic.update(deltaTime);
    }

    public Inventory getInventory() { return inventory; }
    public void setInventory() {}
}
