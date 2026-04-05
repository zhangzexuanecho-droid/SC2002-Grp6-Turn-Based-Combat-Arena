package combat;

import java.util.List;
import action.Action;

public class Wizard extends Player {

    public Wizard() {
        super("Wizard", 200, 50, 10, 20);
    }

    public Action chooseAction() {
        // Wizard can choose among BasicAttack, Defend, UseItem, and SpecialSkill
        return null;
    }

    public void useSpecialSkill(List<Combatant> targets) {
        // to be implemented later
    }
}
