package combat;

import action.Action;

public class Warrior extends Player {

    public Warrior() {
        super("Warrior", 260, 40, 20, 30);
    }

    public Action chooseAction() {
        return null;
    }

    public void useSpecialSkill(Combatant target) {
        // to be implemented later
    }
}
