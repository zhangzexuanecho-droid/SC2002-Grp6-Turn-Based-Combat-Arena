package combat.statusEffect;
import combat.Combatant;

public class SmokeBombEffect implements StatusEffect {
    private int remainingTurns;

    public SmokeBombEffect() {
        this.remainingTurns = 2; // current and next turn
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
        return 0; // nullify all incoming damage while active
    }

    @Override
    public boolean isExpired() {
        return remainingTurns <= 0;
    }
}
