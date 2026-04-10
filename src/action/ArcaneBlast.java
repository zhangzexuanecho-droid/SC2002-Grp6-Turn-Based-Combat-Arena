package action;

import combat.Combatant;
import combat.Player;

import java.util.List;

public class ArcaneBlast extends SpecialSkill {

    private List<Combatant> enemies;

    public ArcaneBlast(List<Combatant> enemies) {
        this.enemies = enemies;
    }

    @Override
    protected void applySkill(Player user, Combatant target) {

        int kills = 0;

        for (Combatant enemy : enemies) {
            if (!enemy.isAlive()) continue;

            enemy.receiveDamage(user.getAttack());

            if (!enemy.isAlive()) {
                kills++;
            }
        }

        // permanent attack buff for this level
        user.attack += kills * 10;

        System.out.println(user.getName() + " unleashes Arcane Blast!");
    }
}
