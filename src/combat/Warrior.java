package combat;

import action.Action;

public class Warrior extends Player {

    public Warrior() {
        super("Warrior", 260, 40, 20, 30);
    }

    public Action chooseAction() {
        // to be inplemented later
        return null
    }

    public void useSpecialSkill(Combatant target) {
        int damage = Math.max(0, this.attack - target.getDefense());
        target.receiveDamage(damage);
        target.addStatusEffect(new StunEffect(2));
        this.skillCooldown = 3;
    }
}
