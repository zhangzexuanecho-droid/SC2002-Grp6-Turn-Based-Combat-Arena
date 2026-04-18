package combat.statusEffect;
import combat.Combatant;

public class SmokeBombEffect implements StatusEffect {
    private int remainingTurns;

    public SmokeBombEffect(int turns) {
        this.remainingTurns = turns;
    }

    @Override
    public void apply(Combatant target) {
        remainingTurns--;
    }

    @Override
    public boolean preventsAction() {
        return false;
    }

    @Override
    public int modifyIncomingDamage(int damage) {
        return 0; 
    }

    @Override
    public boolean isExpired() {
        return remainingTurns <= 0;
    }
}
