package combat;

public class DefendEffect implements StatusEffect {

    private int remainingTurns;
    private int damageReduction;

    public DefendEffect(int turns, int damageReduction) {
        this.remainingTurns = turns;
        this.damageReduction = damageReduction;
    }

    @Override
    public void apply(Combatant target) {
        remainingTurns--;
    }

    @Override
    public boolean isExpired() {
        return remainingTurns <= 0;
    }

    public int reduceDamage(int damage) {
        return Math.max(0, damage - damageReduction);
    }

    public boolean isActive() {
        return remainingTurns > 0;
    }
}
