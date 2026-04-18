package app;

import BattleEngine.BattleEngine;
import BattleEngine.GameUI;
import BattleEngine.SpeedBasedStrategy;
import BattleEngine.TurnOrderStrategy;
import combat.Combatant;
import combat.Goblin;
import combat.Player;
import combat.Warrior;
import combat.Wizard;
import combat.Wolf;
import item.Item;
import item.Potion;
import item.PowerStone;
import item.SmokeBomb;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private final GameUI ui;

    public GameController() {
        this.ui = new GameUI();
    }

    public void start() {
        boolean running = true;

        while (running) {
            ui.displayInitiationScreen();

            Player player = createPlayer();
            int difficulty = chooseDifficulty();
            String difficultyString = mapDifficultyToString(difficulty);
            chooseItems(player, difficultyString);

            TurnOrderStrategy strategy = new SpeedBasedStrategy();
            boolean cleared = true;
            int totalWaves = getTotalWaves(difficulty);
            int totalRoundsAcrossWaves = 0;

            for (int wave = 1; wave <= totalWaves; wave++) {
                if (wave == 1) {
                    ui.displayWaveStart(wave, totalWaves);
                } else {
                    ui.displayBackupWaveIncoming(wave);
                    ui.displayWaveStart(wave, totalWaves);
                }

                List<Combatant> combatants = createCombatants(player, difficulty, wave);
                BattleEngine engine = new BattleEngine(combatants, strategy, ui);
                engine.startBattle();

                totalRoundsAcrossWaves += engine.getCurrentRound();

                if (!player.isAlive()) {
                    cleared = false;
                    ui.displayPlayerDefeat(engine.getEnemiesRemaining(), totalRoundsAcrossWaves);
                    break;
                } else if (wave < totalWaves) {
                    ui.displayWaveCleared(wave, player.getHp());
                }
            }

            if (cleared) {
                ui.displayPlayerVictory(player.getHp(), totalRoundsAcrossWaves);
            }

            int choice = ui.promptPostGameOptions();

            if (choice == 3) {
                running = false;
                System.out.println("Thanks for playing!");
            }
        }
    }

    private Player createPlayer() {
        int choice = ui.promptPlayerSelection();

        if (choice == 1) {
            return new Warrior();
        } else {
            return new Wizard();
        }
    }

    private int chooseDifficulty() {
        ui.displayDifficultyAndEnemies();
        return ui.promptDifficultySelection();
    }

    private String mapDifficultyToString(int difficulty) {
        switch (difficulty) {
            case 1:
                return "easy";
            case 2:
                return "medium";
            case 3:
                return "hard";
            default:
                return "easy";
        }
    }

    private void chooseItems(Player player, String difficulty) {
        for (int i = 1; i <= 2; i++) {
            int choice = ui.promptItemSelection(i);
            Item item = createItem(choice, difficulty);
            player.getInventory().add(item);
        }
    }

    private List<Combatant> createCombatants(Player player, int difficulty, int wave) {
        List<Combatant> combatants = new ArrayList<>();
        combatants.add(player);

        switch (difficulty) {
            case 1:
                combatants.add(new Goblin());
                combatants.add(new Goblin());
                combatants.add(new Goblin());
                break;

            case 2:
                if (wave == 1) {
                    combatants.add(new Goblin());
                    combatants.add(new Wolf());
                } else if (wave == 2) {
                    combatants.add(new Wolf());
                    combatants.add(new Wolf());
                }
                break;

            case 3:
                if (wave == 1) {
                    combatants.add(new Goblin());
                    combatants.add(new Goblin());
                } else if (wave == 2) {
                    combatants.add(new Goblin());
                    combatants.add(new Wolf());
                    combatants.add(new Wolf());
                }
                break;

            default:
                combatants.add(new Goblin());
                combatants.add(new Goblin());
                combatants.add(new Goblin());
                break;
        }

        return combatants;
    }

    private Item createItem(int choice, String difficulty) {
        switch (choice) {
            case 1:
                return new Potion(difficulty);
            case 2:
                return new PowerStone(difficulty);
            case 3:
                return new SmokeBomb(difficulty);
            default:
                return new Potion(difficulty);
        }
    }

    private int getTotalWaves(int difficulty) {
        switch (difficulty) {
            case 1:
                return 1;
            case 2:
            case 3:
                return 2;
            default:
                return 1;
        }
    }
}
