import java.util.List;
import java.util.ArrayList;

public class BattleEngine {
    
    private List<Combatant> combatants;
    private TurnOrderStrategy turnStrategy;

    public BattleEngine(List<Combatant> combatants, TurnOrderStrategy turnStrategy) {
        this.combatants = combatants;
        this.turnStrategy = turnStrategy;
    }

    public void startBattle() {
        while (!checkGameEnd()) {
            processRound();
        }
    }

    public void processRound() {
        List<Combatant> turnOrder = turnStrategy.determineOrder(combatants);

        for (Combatant currentCombatant : turnOrder) {
            if (checkGameEnd()) {
                break;
            }

            if (currentCombatant.isAlive()) {

                currentCombatant.applyStatusEffects();
                
                if (currentCombatant.isAlive()) {
                    currentCombatant.takeTurn();
                    processTurn();
                }
            }
        }
    }

    public boolean checkGameEnd() {
        boolean hasPlayerAlive = false;
        boolean hasEnemyAlive = false;

        for (Combatant c : combatants) {
            if (c.isAlive()) {
                if (c instanceof Player) {
                    hasPlayerAlive = true;
                } else if (c instanceof Enemy) {
                    hasEnemyAlive = true;
                }
            }
        }
        return !hasPlayerAlive || !hasEnemyAlive;
    }

    public void processTurn() {
        combatants.removeIf(c -> !c.isAlive());
    }
}