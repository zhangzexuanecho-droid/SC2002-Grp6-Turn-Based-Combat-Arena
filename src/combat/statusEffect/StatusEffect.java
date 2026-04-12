package combat.statusEffect;

import combat.Combatant;

public interface StatusEffect {

    void apply(Combatant target);

    boolean preventsAction();

    int modifyIncomingDamage(int damage);

    boolean isExpired();
}
