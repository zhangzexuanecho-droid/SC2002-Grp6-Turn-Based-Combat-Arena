package combat;

import java.util.ArrayList;
import java.util.List;
import item.Item;
import action.Action;

public abstract class Player extends Combatant {

    protected List<Item> inventory;
    protected int skillCooldown;

    public Player(String name, int hp, int attack, int defense, int speed) {
        super(name, hp, attack, defense, speed);
        this.inventory = new ArrayList<>();
        this.skillCooldown = 0;
    }

    public Combatant selectTarget(BattleEngine engine) {
        List<Combatant> enemies = engine.getAliveEnemiesOf(this);

        if (enemies.isEmpty()) {
            System.out.println("No targets available.");
            return null;
        }

        System.out.println("Choose target:");
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println((i + 1) + ". " + enemies.get(i).getName());
        }

        int choice = scanner.nextInt();

        if (choice < 1 || choice > enemies.size()) {
            System.out.println("Invalid choice, defaulting to first target.");
            return enemies.get(0);
        }

        return enemies.get(choice - 1);
    }

    @Override
    public void takeTurn(BattleEngine engine, GameUI ui) {
        super.takeTurn();
        if (!isAlive()) return;

        if (skillCooldown > 0) {
            skillCooldown--;
        }

        Action a = chooseAction(ui);
        if (a == null) return;

        Combatant target = null;

        if (a.requiresTarget()) {
            target = selectTarget(engine);
        }

        a.execute(this, target);
    }

    public abstract Action chooseAction(GameUI ui);

    public boolean canUseSkill() {
        return skillCooldown == 0;
    }

    public void setCooldown(int turns) {
        this.skillCooldown = turns;
    }

    public int getSkillCooldown() {
        return skillCooldown;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public Item removeItem(int index) {
        if (index >= 0 && index < inventory.size()) {
            return inventory.remove(index);
        }
        return null;
    }
}
