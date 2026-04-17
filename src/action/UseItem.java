package action;
import java.util.Scanner;
import combat.Combatant;
import combat.Player;
import item.Item;

import java.util.List;

public class UseItem implements Action {

    @Override
    public void execute(Combatant user, Combatant target) {

        if (!(user instanceof Player)) return;

        Player player = (Player) user;

        List<Item> inv = player.getInventory();

        if (inv.isEmpty()) {
            System.out.println("No items available!");
            return;
        }

        System.out.println("Choose item:");

        for (int i = 0; i < inv.size(); i++) {
            System.out.println(i + ": " + inv.get(i).getName());
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice < 0 || choice >= inv.size()) {
            System.out.println("Invalid choice!");
            return;
        }

        Item item = player.removeItem(choice);

        if (item != null) {
            item.use(player);
        }
    }

    @Override
    public boolean requiresTarget() 
    {
        return false; 
    }
        
}
