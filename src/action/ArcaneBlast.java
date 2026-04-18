package action;

import BattleEngine.BattleEngine;
import combat.Combatant;
import combat.Player;
import java.util.List;

public class ArcaneBlast extends SpecialSkill {

    private BattleEngine engine;

    public ArcaneBlast(BattleEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void applySkill(Player user, Combatant target) {
        List<Combatant> enemies = engine.getAliveEnemiesOf(user);
        int kills = 0;

        System.out.println(user.getName() + " unleashes Arcane Blast!");

        for (Combatant enemy : enemies) {
            if (!enemy.isAlive()) continue;

            enemy.receiveDamage(user.getAttack());

            if (!enemy.isAlive()) {
                kills++;
            }
        }

        user.increaseAttack(kills * 10);
    }

    @Override
    public boolean requiresTarget() {
        return false;
    }
}
