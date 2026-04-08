package combat;

public class SmokeBombEffect implements StatusEffect {

    private int remainingTurns;

    public SmokeBombEffect(int turns) {
        this.remainingTurns = turns;
    }

    @Override
    public void apply(Combatant target) {
        // Just decrease duration each round
        remainingTurns--;
    }

    @Override
    public boolean isExpired() {
        return remainingTurns <= 0;
    }

    public boolean isActive() {
        return remainingTurns > 0;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }
}
