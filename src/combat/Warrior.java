package combat;

import action.Action;

public class Warrior extends Player {

    public Warrior() {
        super("Warrior", 260, 40, 20, 30);
    }

    @Override
    public Action chooseAction() {
        System.out.println("\n--- Choose your action ---");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Item");
        
   
        if (this.canUseSkill()) {
            System.out.println("4. Special Skill (Shield Bash)");
        } 
        else {
            System.out.println("4. [Skill Cooldown: " + getSkillCooldown() + " turns]");
        }

        int choice = scanner.nextInt();
        //ui.displayActionMenu();
        //int choice = ui.getValidInput(1, 4);
    
        switch (choice) {
            case 1: return new BasicAttack();
            case 2: return new Defend(); 
            case 3: return new UseItem(); 
            case 4: 
                if (this.canUseSkill()) return new ShieldBash();
                else System.out.println("Skill not ready!");
            default: 
                System.out.println("Invalid choice, defaulting to Attack.");
                return new BasicAttack();
        }
    }

    public void useSpecialSkill(Combatant target) {
        //this.skillCooldown = 3;
        System.out.println(this.name + " uses Shield Bash!");
    }
}
