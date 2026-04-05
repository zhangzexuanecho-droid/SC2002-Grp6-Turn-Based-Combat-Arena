package combat;

import java.util.ArrayList;
import java.util.List;
import item.Item;
import action.Action;

public abstract class Player extends Combatant {

    protected List<Item> inventory;
    protected int skillCooldown;

    public Player(String name, int hp, int attack, int defense, int speed) {
        super(name, hp, attack, defense, speed);
        this.inventory = new ArrayList<>();
        this.skillCooldown = 0;
    }

    @Override
    public void takeTurn() {
        super.takeTurn();
        if (isAlive() && skillCooldown > 0) {
            skillCooldown--;
        }
    }

    public abstract Action chooseAction();
}
