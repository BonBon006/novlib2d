package novlib2d.ui;

import novlib2d.entity.character.Npc;
import novlib2d.entity.character.Player;
import novlib2d.entity.item.Consumable;
import novlib2d.entity.item.Item;
import novlib2d.entity.item.QuestItem;
import novlib2d.entity.item.equipment.Accessory;
import novlib2d.entity.item.equipment.Armor;
import novlib2d.entity.item.equipment.Weapon;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> itemList;
    private int inventorySize;

    public Inventory(Player player, int inventorySize) {
        itemList = new ArrayList<>();
        this.inventorySize = inventorySize;
    }
    public Inventory(Npc npc, int inventorySize) {
        itemList = new ArrayList<>();
        this.inventorySize = inventorySize;
    }

    public void addWeapon(Weapon weapon) {
        if (itemList.size() <= inventorySize) itemList.add(weapon);
    }

    public void addArmor(Armor armor) {
        if (itemList.size() <= inventorySize) itemList.add(armor);
    }

    public void addAccessory(Accessory accessory) {
        if (itemList.size() <= inventorySize) itemList.add(accessory);
    }

    public void addConsumable(Consumable consumable) {
        if (itemList.size() <= inventorySize) itemList.add(consumable);
    }

    public void addQuesItem(QuestItem questItem) {
        if (itemList.size() <= inventorySize) itemList.add(questItem);
    }

    public ArrayList<Item> getItemList() { return itemList; }
}
