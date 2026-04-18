package BattleEngine;

import combat.Combatant;
import combat.Enemy;
import combat.Player;
import java.util.ArrayList;
import java.util.List;

public class BattleEngine {

    private List<Combatant> combatants;
    private TurnOrderStrategy turnStrategy;
    private GameUI ui;
    private int currentRound = 1;

    public BattleEngine(List<Combatant> combatants, TurnOrderStrategy turnStrategy, GameUI ui) {
        this.combatants = combatants;
        this.turnStrategy = turnStrategy;
        this.ui = ui;
    }

    public void startBattle() {
        while (!checkGameEnd()) {
            processRound();
        }
    }

    public void processRound() {
        ui.displayRoundStart(currentRound);

        List<Combatant> turnOrder = turnStrategy.determineOrder(combatants);

        for (Combatant currentCombatant : turnOrder) {
            if (checkGameEnd()) {
                break;
            }

            if (!currentCombatant.isAlive()) {
                continue;
            }

            boolean canAct = currentCombatant.updateStatusEffects();

            if (!currentCombatant.isAlive()) {
                processTurn();
                continue;
            }

            if (canAct) {
                currentCombatant.takeTurn(this, ui);
                processTurn();
            } else {
                ui.showMessage(currentCombatant.getName() + " cannot act this turn!");
                processTurn();
            }
        }

        currentRound++;
    }

    public boolean checkGameEnd() {
        return !hasPlayerAlive() || !hasEnemyAlive();
    }

    private boolean hasPlayerAlive() {
        for (Combatant c : combatants) {
            if (c.isAlive() && c instanceof Player) {
                return true;
            }
        }
        return false;
    }

    private boolean hasEnemyAlive() {
        for (Combatant c : combatants) {
            if (c.isAlive() && c instanceof Enemy) {
                return true;
            }
        }
        return false;
    }

    public void processTurn() {
        List<Combatant> defeated = new ArrayList<>();

        for (Combatant c : combatants) {
            if (!c.isAlive()) {
                defeated.add(c);
            }
        }

        Player player = getPlayer();

        for (Combatant defeatedCombatant : defeated) {
            if (defeatedCombatant instanceof Enemy) {
                int remainingHP = (player != null && player.isAlive()) ? player.getHp() : 0;
                ui.displayEnemyDefeated(defeatedCombatant.getName(), remainingHP);
            } else {
                ui.displayCombatantEliminated(defeatedCombatant.getName());
            }
        }

        combatants.removeIf(c -> !c.isAlive());
    }

    public List<Combatant> getAliveEnemiesOf(Combatant self) {
        List<Combatant> result = new ArrayList<>();

        if (self instanceof Player) {
            for (Combatant c : combatants) {
                if (c instanceof Enemy && c.isAlive()) {
                    result.add(c);
                }
            }
        } else if (self instanceof Enemy) {
            for (Combatant c : combatants) {
                if (c instanceof Player && c.isAlive()) {
                    result.add(c);
                }
            }
        }

        return result;
    }

    public List<Combatant> getCombatants() {
        return combatants;
    }

    public int getCurrentRound() {
        return currentRound - 1;
    }

    public int getEnemiesRemaining() {
        int count = 0;
        for (Combatant c : combatants) {
            if (c instanceof Enemy && c.isAlive()) {
                count++;
            }
        }
        return count;
    }

    public Player getPlayer() {
        for (Combatant c : combatants) {
            if (c instanceof Player) {
                return (Player) c;
            }
        }
        return null;
    }
}
