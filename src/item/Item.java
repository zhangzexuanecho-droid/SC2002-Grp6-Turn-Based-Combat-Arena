package item;

import combat.Combatant;

public interface Item {
  void use(Combatant target);
  String getName();
}

  
