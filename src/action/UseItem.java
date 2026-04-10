public class UseItem implements Action {

    @Override
    public void execute(Player player, Combatant target) {

        List<Item> inv = player.getInventory();

        if (inv.isEmpty()) {
            System.out.println("No items available!");
            return;
        }

        System.out.println("Choose item:");

        for (int i = 0; i < inv.size(); i++) {
            System.out.println(i + ": " + inv.get(i).getName());
        }

        int choice = Player.scanner.nextInt();

        Item item = player.removeItem(choice);

        if (item != null) {
            item.use(player, target);
        }
    }
}
