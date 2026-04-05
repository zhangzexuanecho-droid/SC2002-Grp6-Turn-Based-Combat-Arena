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
        
   
        if (this.skillCooldown == 0) {
            System.out.println("4. Special Skill (Shield Bash)");
        } 
        else {
            System.out.println("4. [Skill Cooldown: " + skillCooldown + " turns]");
        }

        int choice = scanner.nextInt();
    
        switch (choice) {
            case 1: return new BasicAttack();
            case 2: return new Defend(); 
            case 3: return UseItem(); 
            case 4: 
                if (this.skillCooldown == 0) return new ShieldBash();
                else System.out.println("Skill not ready!");
            default: 
                System.out.println("Invalid choice, defaulting to Attack.");
                return new BasicAttack();
        }
    }

    public void useSpecialSkill(Combatant target) {
        int damage = Math.max(0, this.attack - target.getDefense());
        target.receiveDamage(damage);
        target.addStatusEffect(new StunEffect(2));
        this.skillCooldown = 3;
    }
}
