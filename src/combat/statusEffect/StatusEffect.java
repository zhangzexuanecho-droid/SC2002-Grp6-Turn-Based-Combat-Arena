package combat;
public interface StatusEffect{
  void apply(Combatant target);
  boolean isExpired();
}
