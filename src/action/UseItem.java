package action;

import combat.Combatant;
import item.Item;

public class UseItem implements Action 
{

    private Item item;

    public UseItem(Item item) 
    {
        this.item = item;
    }

    @Override
    public void execute(Combatant user, Combatant target) 
    {
        item.use(user);

        System.out.println(user.getName() + " uses an item.");
    }
}
