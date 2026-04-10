package combat.statusEffect;

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
    public boolean isExpired() {
        return remainingTurns <= 0;
    }

    public boolean isActive() {
        return remainingTurns > 0;
    }
}
