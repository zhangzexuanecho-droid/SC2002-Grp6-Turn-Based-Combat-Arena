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
            chooseItems(player);

            int difficulty = chooseDifficulty();
            List<Combatant> combatants = createCombatants(player, difficulty);

            TurnOrderStrategy strategy = new SpeedBasedStrategy();

            // 这里要看你们 BattleEngine 的 constructor
            BattleEngine engine = new BattleEngine(combatants, strategy, ui);

            engine.startBattle();

            int choice = ui.promptPostGameOptions();
            if (choice == 1) {
                // replay same settings
                continue;
            } else if (choice == 2) {
                // new game
                continue;
            } else {
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

    private void chooseItems(Player player) {
        for (int i = 1; i <= 2; i++) {
            int choice = ui.promptItemSelection(i);
            Item item = createItem(choice);
            player.addItem(item); 
        }
    }

    private int chooseDifficulty() {
        ui.displayDifficultyAndEnemies();
        return ui.promptDifficultySelection();
    }

    private List<Combatant> createCombatants(Player player, int difficulty) {
        List<Combatant> combatants = new ArrayList<>();
        combatants.add(player);

        switch (difficulty) {
            case 1:
                combatants.add(new Goblin());
                combatants.add(new Goblin());
                combatants.add(new Goblin());
                break;
            case 2:
                combatants.add(new Goblin());
                combatants.add(new Wolf());
                break;
            case 3:
                combatants.add(new Goblin());
                combatants.add(new Goblin());
                break;
            default:
                combatants.add(new Goblin());
                combatants.add(new Goblin());
                combatants.add(new Goblin());
                break;
        }

        return combatants;
    }

    private Item createItem(int choice) {
        switch (choice) {
            case 1:
                return new Potion();
            case 2:
                return new PowerStone();
            case 3:
                return new SmokeBomb();
            default:
                return new Potion();
        }
    }
}
