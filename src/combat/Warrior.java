package combat;

import BattleEngine.BattleEngine;
import BattleEngine.GameUI;
import action.Action;
import action.BasicAttack;
import action.Defend;
import action.ShieldBash;
import action.UseItem;

public class Warrior extends Player {

    public Warrior() {
        super("Warrior", 260, 40, 20, 30);
    }

    @Override
    public Action chooseAction(BattleEngine engine, GameUI ui) {
        ui.displayActionMenu();
        int choice = ui.getValidInput(1, 4);

        switch (choice) {
            case 1:
                return new BasicAttack();
            case 2:
                return new Defend();
            case 3:
                return new UseItem(ui);
            case 4:
                if (canUseSkill()) {
                    return new ShieldBash();
                } else {
                    System.out.println("Skill not ready!");
                    return new BasicAttack();
                }
            default:
                return new BasicAttack();
        }
    }

    public void useSpecialSkill(Combatant target) {
        System.out.println(this.name + " uses Shield Bash!");
    }
}
