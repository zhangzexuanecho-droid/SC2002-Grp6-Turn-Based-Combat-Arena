package combat.statusEffect;
public interface StatusEffect{
  void apply(Combatant target);
  boolean isExpired();
}
