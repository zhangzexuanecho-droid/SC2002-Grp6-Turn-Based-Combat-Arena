package combat;


import BattleEngine.BattleEngine;
import BattleEngine.GameUI;
import action.Action;
import action.BasicAttack;

import java.util.List;

public abstract class Enemy extends Combatant {

    public Enemy(String name, int hp, int attack, int defense, int speed) {
        super(name, hp, attack, defense, speed);
    }

    public Action decideAction() {
        return new BasicAttack();
    }
    
   @Override
    public void takeTurn(BattleEngine engine, GameUI ui) {

        List<Combatant> targets = engine.getAliveEnemiesOf(this);
        if (targets.isEmpty()) return;
       
       
        boolean canAct = updateStatusEffects();
        if (!canAct) {
            System.out.println(getName() + " cannot act this turn!");
            return;
        }
       

        Combatant target = targets.get(0); // simplest

        Action action = decideAction(); 

        if (action.requiresTarget()) {
            action.execute(this, target);
        } 
        else {
            action.execute(this, null);
        }
    }
}
