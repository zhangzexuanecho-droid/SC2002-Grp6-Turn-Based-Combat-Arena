package action;

import combat.Combatant;
import combat.Player;

public abstract class SpecialSkill implements Action 
{

    @Override
    public void execute(Combatant user, Combatant target)
    {

        if (!(user instanceof Player)) 
        {
            return;
        }

        Player player = (Player) user;

        if (!player.canUseSkill()) 
        {
            System.out.println("Skill is on cooldown!");
            return;
        }

        applySkill(player, target);
        player.setCooldown(3);

        //System.out.println(user.getName() + " uses their special skill!");
    }
    @Override
    public boolean requiresTarget() 
    {
        return true; // default
    }
    protected abstract void applySkill( Player user, Combatant target);
}
