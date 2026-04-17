package combat;

import java.util.List;

import action.Action;
import action.BasicAttack;
import action.Defend;
import action.UseItem;
import action.ArcaneBlast;
import BattleEngine.GameUI;

public class Wizard extends Player {

    public Wizard() {
        super("Wizard", 200, 50, 10, 20);
    }

    
    @Override
    public Action chooseAction(GameUI ui) {

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
                	return new ArcaneBlast();
                } else {
                    System.out.println("Mana recovering... Skill not ready!");
                    return new BasicAttack();
                }
            default:
                return new BasicAttack();
        }
    }

    public void useSpecialSkill(List<Combatant> targets) {
        //this.skillCooldown = 3;
        System.out.println(this.name + " uses Arcane Blast！");
    }
}
