package combat.statusEffect;

import combat.Combatant;

public class StunEffect implements StatusEffect {

    private int remainingTurns;

    public StunEffect(int turns) {
        this.remainingTurns = turns;
    }

    @Override
    public void apply(Combatant target) {
        if (remainingTurns > 0) {
            System.out.println(target.getName() + " → STUNNED: Turn skipped");
            remainingTurns--;
        }
    }

    @Override
    public boolean preventsAction() {
        return remainingTurns > 0;
    }

    @Override
    public int modifyIncomingDamage(int damage) {
        return damage; 
    }

    @Override
    public boolean isExpired() {
        return remainingTurns <= 0;
    }
}
