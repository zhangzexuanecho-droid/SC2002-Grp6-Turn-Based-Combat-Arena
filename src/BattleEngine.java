import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BattleEngine {
    private List<Player> players;
    private List<Enemy> enemies;
    private Queue<List<Enemy>> backupSpawns; 
    private int currentRound;
    private TurnOrderStrategy turnStrategy;

    public BattleEngine(List<Player> players, List<Enemy> initialEnemies, Queue<List<Enemy>> backupSpawns, TurnOrderStrategy turnStrategy) {
        this.players = new ArrayList<>(players);
        this.enemies = new ArrayList<>(initialEnemies);
        this.backupSpawns = backupSpawns != null ? backupSpawns : new LinkedList<>();
        this.turnStrategy = turnStrategy;
        this.currentRound = 1;
    }

    public List<Combatant> startRound() {
        checkBackupSpawns();

        List<Combatant> allCombatants = new ArrayList<>();
        allCombatants.addAll(players);
        allCombatants.addAll(enemies);

        return turnStrategy.determineOrder(allCombatants);
    }

    public void processAction(IAction action, Combatant user, Combatant target) {
        if (user.isAlive() && user.canAct()) {
            action.execute(user, target, this);
        }
        
        cleanUpDefeated();
    }

    private void checkBackupSpawns() {
        if (enemies.isEmpty() && !backupSpawns.isEmpty()) {
            List<Enemy> reinforcements = backupSpawns.poll();
            enemies.addAll(reinforcements);
        }
    }

    private void cleanUpDefeated() {
        players.removeIf(p -> !p.isAlive());
        enemies.removeIf(e -> !e.isAlive());
    }

    public boolean checkGameEnd() {
        return isPlayerDefeated() || isVictory();
    }

    public boolean isPlayerDefeated() {
        return players.isEmpty();
    }

    public boolean isVictory() {
        return enemies.isEmpty() && backupSpawns.isEmpty();
    }

    public void advanceRound() {
        this.currentRound++;
    }

    public List<Player> getPlayers() { return players; }
    public List<Enemy> getEnemies() { return enemies; }
    public int getCurrentRound() { return currentRound; }
}