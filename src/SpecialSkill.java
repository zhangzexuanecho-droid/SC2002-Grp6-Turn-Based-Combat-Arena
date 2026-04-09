package action;

import combat.Combatant;
import combat.Player;

public class SpecialSkill implements Action 
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

        player.useSpecialSkill(target);
        player.resetCooldown();

        //System.out.println(user.getName() + " uses their special skill!");
    }
}
