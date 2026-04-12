package combat.statusEffect;

import combat.Combatant;

public class DefendEffect implements StatusEffect {

    private int remainingTurns;
    private int reduction;

    public DefendEffect(int turns, int reduction) {
        this.remainingTurns = turns;
        this.reduction = reduction;
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
        return Math.max(0, damage - reduction);
    }

    @Override
    public boolean isExpired() {
        return remainingTurns <= 0;
    }
}
