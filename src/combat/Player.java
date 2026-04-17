package combat;

import java.util.ArrayList;
import java.util.List;
import item.Item;
import action.Action;
import BattleEngine.BattleEngine;
import BattleEngine.GameUI;
import action.ArcaneBlast;

public abstract class Player extends Combatant {

    protected List<Item> inventory;
    protected int skillCooldown;

    public Player(String name, int hp, int attack, int defense, int speed) {
        super(name, hp, attack, defense, speed);
        this.inventory = new ArrayList<>();
        this.skillCooldown = 0;
    }

    public Combatant selectTarget(BattleEngine engine, GameUI ui) {
        List<Combatant> enemies = engine.getAliveEnemiesOf(this);

        if (enemies.isEmpty()) {
            System.out.println("No targets available.");
            return null;
        }
    return ui.chooseTarget(enemies);
    public Combatant selectTarget(BattleEngine engine, GameUI ui) {
          List<Combatant> enemies = engine.getAliveEnemiesOf(this);
          if (enemies.isEmpty()) {
              System.out.println("No targets available.");
              return null;
          }
          return ui.chooseTarget(enemies);
      }
        
    }

    @Override
    public void takeTurn(BattleEngine engine, GameUI ui) {
        if (!isAlive()) return;
      
        
        boolean canAct = updateStatusEffects();
        if (!canAct) {
            System.out.println(getName() + " cannot act this turn!");
            return;
        }
    

        if (skillCooldown > 0) {
            skillCooldown--;
        }

        Action a = chooseAction(ui);
        if (a instanceof ArcaneBlast ) {
            ((ArcaneBlast) a).setEngine(engine);

        Combatant target = null;

        if (a.requiresTarget()) {
            target = selectTarget(engine,ui);
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
