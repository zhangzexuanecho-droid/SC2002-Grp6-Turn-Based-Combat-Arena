package BattleEngine;

import java.util.List;

import combat.Combatant;
import combat.Enemy;
import combat.Player;

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

        int remainingHP = 0;
        int enemiesRemaining = 0;

        for (Combatant c : combatants) {
            if (c.isAlive()) {
                if (c instanceof Player) {
                    remainingHP = c.getHp();
                } else if (c instanceof Enemy) {
                    enemiesRemaining++;
                }
            }
        }

        if (hasPlayerAlive()) {
            this.ui.displayPlayerVictory(remainingHP, currentRound) ;
        } else {
            this.ui.displayPlayerDefeat(enemiesRemaining, currentRound);
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
                    currentCombatant.takeTurn(this, ui);
                    processTurn();
                    this.currentRound++;
                }
            }
        }
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
        combatants.removeIf(c -> !c.isAlive());
    }

    public List<Combatant> getAliveEnemiesOf(Combatant self) {
        List<Combatant> result = new java.util.ArrayList<>();

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
}
