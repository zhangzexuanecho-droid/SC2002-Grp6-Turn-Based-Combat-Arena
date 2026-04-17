package BattleEngine;

import java.util.List;
import java.util.ArrayList;

import combat.Combatant;
import combat.Player;
import combat.Enemy;

public class BattleEngine {
    
    private List<Combatant> combatants;
    private TurnOrderStrategy turnStrategy;
    private GameUI ui;

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

    public List<Combatant> getAliveEnemiesOf(Combatant self) {
        List<Combatant> aliveEnemies = new ArrayList<>();

        for (Combatant c : combatants) {
            if (c.isAlive()) {
                if (self instanceof Player && c instanceof Enemy) {
                    aliveEnemies.add(c);
                } else if (self instanceof Enemy && c instanceof Player) {
                    aliveEnemies.add(c);
                }
            }
        }
        return aliveEnemies;
    }
    
    public void processTurn() {
        combatants.removeIf(c -> !c.isAlive());
    }
}
