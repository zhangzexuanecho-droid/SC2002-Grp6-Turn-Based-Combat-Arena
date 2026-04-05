package combat;

import action.Action;
import action.BasicAttack;

public abstract class Enemy extends Combatant {

    public Enemy(String name, int hp, int attack, int defense, int speed) {
        super(name, hp, attack, defense, speed);
    }

    public Action decideAction() {
        return new BasicAttack();
    }
}
