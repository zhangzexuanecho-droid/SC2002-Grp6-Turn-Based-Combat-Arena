package combat.statusEffect;
public interface StatusEffect{
  void apply(Combatant target);
  boolean preventsAction();
  int modifyIncomingDamage(int damage);
  boolean isExpired();
}
