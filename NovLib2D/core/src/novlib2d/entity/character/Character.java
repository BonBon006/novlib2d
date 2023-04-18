package novlib2d.entity.character;

import novlib2d.algorithm.Logic;
import novlib2d.entity.Entity;
import novlib2d.graphics.Camera;
import novlib2d.skill.Skill;

import java.util.ArrayList;

public class Character extends Entity {

    protected Logic logic;
    protected Camera camera;
    protected String name;
    protected float speed;
    protected float health;
    protected float mana;
    protected float stamina;
    protected ArrayList<Skill> skills;

    public String getName() { return name; }
    public float getSpeed() { return speed; }
    public float getHealth() { return health; }
    public float getMana() { return mana; }
    public float getStamina() { return stamina; }

    public void setName(String name) { this.name = name; }
    public void setSpeed(float speed) { this.speed = speed; }
    public void setHealth(float health) { this.health = health; }
    public void setMana(float mana) { this.mana = mana; }
    public void setStamina(float stamina) { this.speed = stamina; }
}
