package action;

import combat.Combatant;
import combat.Player;
import item.Item;
import BattleEngine.GameUI;

import java.util.List;

public class UseItem implements Action {

    private GameUI ui;

    public UseItem(GameUI ui) {
        this.ui = ui;
    }

    @Override
    public void execute(Combatant user, Combatant target) {

        if (!(user instanceof Player)) return;

        Player player = (Player) user;

        List<Item> inv = player.getInventory();

        if (inv.isEmpty()) {
            System.out.println("No items available!");
            return;
        }

        int choice = ui.promptUseItemSelection(inv);

        Item selectedItem = inv.get(choice - 1);

        selectedItem.use(player);

        inv.remove(choice - 1);
    }

    @Override
    public boolean requiresTarget() 
    {
        return false; 
    }
        
}
