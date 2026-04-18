package combat.statusEffect;
import combat.Combatant;

public class SmokeBombEffect implements StatusEffect {
    private int remainingTurns;
    private boolean triggered = false; 
    
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
        if (damage > 0) {
            triggered = true; // absorb one hit then expire
            return 0;
        }
        return damage;
    }

    @Override
    public boolean isExpired() {
        return remainingTurns <= 0 || triggered;
    }
}
