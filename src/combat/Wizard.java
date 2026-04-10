package combat;

import java.util.List;
import action.Action;

public class Wizard extends Player {

    public Wizard() {
        super("Wizard", 200, 50, 10, 20);
    }

    @Override
    public Action chooseAction() {
        System.out.println("\n--- Wizard's Turn ---");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Item");

        if (this.canUseSkill()) {
            System.out.println("4. Special Skill (Arcane Blast - AoE)");
        } 
        else {
            System.out.println("4. [Skill Cooldown: " + getSkillCooldown() + " turns]");
        }

        int choice = scanner.nextInt();

        switch (choice) {
            case 1: return new BasicAttack();
            case 2: return new Defend();
            case 3: return UseItem(); // 调用 Player 类中的通用道具菜单
            case 4:
                if (this.canUseSkill()) return new ArcaneBlast();
                else {
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
