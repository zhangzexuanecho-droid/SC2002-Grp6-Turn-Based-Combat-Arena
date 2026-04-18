package combat;

import BattleEngine.BattleEngine;
import BattleEngine.GameUI;
import action.Action;
import action.ArcaneBlast;
import action.BasicAttack;
import action.Defend;
import action.UseItem;

public class Wizard extends Player {

    public Wizard() {
        super("Wizard", 200, 50, 10, 20);
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
                    return new ArcaneBlast(engine);
                } else {
                    System.out.println("Mana recovering... Skill not ready!");
                    return new BasicAttack();
                }
            default:
                return new BasicAttack();
        }
    }
}
