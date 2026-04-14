package action;

import combat.Combatant;
import combat.Player;
import combat.statusEffect.StunEffect;

public class ShieldBash extends SpecialSkill 
{
    @Override
    protected void applySkill(Player user, Combatant target)
    {
        if (target == null) return;

        // Deal damage
        target.receiveDamage(user.getAttack());

        // Apply stun for 2 turns
        target.addStatusEffect(new StunEffect(2));

        System.out.println(user.getName() + " uses Shield Bash on " + target.getName() + "!");
    }

    @Override
    public boolean requiresTarget()
    {
        return true;
    }
}
