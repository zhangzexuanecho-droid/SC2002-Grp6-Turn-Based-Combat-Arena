package BattleEngine;

import combat.Combatant;

import java.util.List;

public interface TurnOrderStrategy {
    List<Combatant> determineOrder(List<Combatant> combatants);
}
