package BattleEngine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SpeedBasedStrategy implements TurnOrderStrategy {

    @Override
    public List<Combatant> determineOrder(List<Combatant> combatants) {
        List<Combatant> order = new ArrayList<>();

        for (Combatant c : combatants) {
            if (c.isAlive()) {
                order.add(c);
            }
        }

        // Sort descending by speed; ties broken by name for determinism
        order.sort(Comparator.comparingInt(Combatant::getSpeed)
                             .reversed()
                             .thenComparing(Combatant::getName));

        return order;
    }
}
