package BattleEngine;
import java.util.Scanner;

public class GameUI {
    
    private Scanner scanner;

    public GameUI() {
        this.scanner = new Scanner(System.in);
    }

    // ==========================================
    // 1. LOADING SCREEN - INITIATION
    // ==========================================

    public void displayInitiationScreen() {
        System.out.println("\n=================================================");
        System.out.println("      WELCOME TO THE TURN-BASED COMBAT ARENA     ");
        System.out.println("=================================================");
        
        System.out.println("\n--- Available Classes ---");
        System.out.println("1. Warrior");
        System.out.println("   Attributes -> HP: 260 | ATK: 40 | DEF: 20 | SPD: 30");
        System.out.println("   Skill: Shield Bash (Deals BasicAttack damage, Stuns target for 2 turns)");
        
        System.out.println("\n2. Wizard");
        System.out.println("   Attributes -> HP: 200 | ATK: 50 | DEF: 10 | SPD: 20");
        System.out.println("   Skill: Arcane Blast (Deals BasicAttack damage to ALL enemies. +10 ATK per kill)");
    }

    public int promptPlayerSelection() {
        System.out.print("\nChoose your class (1 for Warrior, 2 for Wizard): ");
        return getValidInput(1, 2);
    }

    public int promptItemSelection(int itemSlotIndex) {
        System.out.println("\n--- Available Items ---");
        System.out.println("1. Potion (Heal 100 HP)");
        System.out.println("2. Power Stone (Free special skill use without triggering cooldown)");
        System.out.println("3. Smoke Bomb (Enemy attacks do 0 damage for current and next turn)");
        System.out.print("Select Item " + itemSlotIndex + " (Duplicates allowed, enter 1-3): ");
        return getValidInput(1, 3);
    }

    public void displayDifficultyAndEnemies() {
        System.out.println("\n--- Enemy Compendium ---");
        System.out.println("Goblin -> HP: 55 | ATK: 35 | DEF: 15 | SPD: 25");
        System.out.println("Wolf   -> HP: 40 | ATK: 45 | DEF:  5 | SPD: 35");

        System.out.println("\n--- Difficulty Levels ---");
        System.out.println("1. Easy   -> 3 Goblins");
        System.out.println("2. Medium -> 1 Goblin, 1 Wolf (Backup Spawn: 2 Wolves)");
        System.out.println("3. Hard   -> 2 Goblins    (Backup Spawn: 1 Goblin, 2 Wolves)");
    }

    public int promptDifficultySelection() {
        System.out.print("\nSelect Difficulty (1-Easy, 2-Medium, 3-Hard): ");
        return getValidInput(1, 3);
    }

    // ==========================================
    // 2. DURING GAMEPLAY
    // ==========================================

    public void displayRoundStart(int roundNumber) {
        System.out.println("\n=================================================");
        System.out.println("                   ROUND " + roundNumber + "                   ");
        System.out.println("=================================================");
    }

    public void displayBackupSpawnTriggered() {
        System.out.println("\n[!] WARNING: Initial enemies defeated. BACKUP SPAWN has entered the arena!");
    }

    public void displayStatusEffectApplied(String combatantName, String effectName) {
        System.out.println("[-] Status Effect applied: " + combatantName + " is affected by " + effectName + ".");
    }

    public void displayActionMenu() {
        System.out.println("\n--- Choose your action ---");
        System.out.println("1. Basic Attack");
        System.out.println("2. Defend");
        System.out.println("3. Use Item");
        System.out.println("4. Special Skill");
        System.out.print("Enter choice (1-4): ");
    }

    public void displayActionExecution(String attacker, String actionName, String target, int damage) {
        if (damage > 0) {
            System.out.println("[>>] " + attacker + " uses " + actionName + " on " + target + " for " + damage + " damage!");
        } else {
            System.out.println("[>>] " + attacker + " uses " + actionName + ".");
        }
    }

    public void displayCombatantEliminated(String name) {
        System.out.println("[X] " + name + "'s HP reached 0. " + name + " is ELIMINATED!");
    }

    // ==========================================
    // 3. GAME COMPLETION SCREEN
    // ==========================================

    public void displayPlayerVictory(int remainingHP, int totalRounds) {
        System.out.println("\n*************************************************");
        System.out.println("                  VICTORY!                       ");
        System.out.println("*************************************************");
        System.out.println("Congratulations, you have defeated all your enemies.");
        System.out.println("Statistics: Remaining HP: " + remainingHP + " | Total Rounds: " + totalRounds);
    }

    public void displayPlayerDefeat(int enemiesRemaining, int totalRoundsSurvived) {
        System.out.println("\n*************************************************");
        System.out.println("                  DEFEAT...                      ");
        System.out.println("*************************************************");
        System.out.println("Defeated. Don't give up, try again!");
        System.out.println("Statistics: Enemies remaining: " + enemiesRemaining + " | Total Rounds Survived: " + totalRoundsSurvived);
    }

    public int promptPostGameOptions() {
        System.out.println("\n--- Game Over Menu ---");
        System.out.println("1. Replay with the same settings");
        System.out.println("2. Start a new game (Return to Home Screen)");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        return getValidInput(1, 3);
    }

    // ==========================================
    // UTILITY METHODS
    // ==========================================

    /**
     * Helper method to ensure valid integer input within a range.
     */
    public int getValidInput(int min, int max) {
        int choice = -1;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= min && choice <= max) {
                    break;
                } else {
                    System.out.print("Invalid choice. Please enter a number between " + min + " and " + max + ": ");
                }
            } else {
                System.out.print("Invalid input format. Please enter a number: ");
                scanner.next(); // Consume invalid input
            }
        }
        return choice;
    }
}