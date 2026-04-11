package action;

import combat.Combatant;
import combat.Player;
import combat.StatusEffect;

public class ShieldBash extends SpecialSkill 
{
    @Override
    protected void applySkill(Player user, Combatant target)
    {
        if (target == null) return;

        // Deal damage
        target.receiveDamage(user.getAttack());

        // Apply stun ONLY to this target
        target.addStatusEffect(new StatusEffect()
        {
            private int duration = 2;

            @Override
            public void apply(Combatant target)
            {
                target.setStunned(true);
                duration--;

                if (duration <= 0) {
                    target.setStunned(false);
                }
            }

            @Override
            public boolean isExpired()
            {
                return duration <= 0;
            }
        });

        System.out.println(user.getName() + " uses Shield Bash on " + target.getName() + "!");
    }

    @Override
    public boolean requiresTarget()
    {
        return true;
    }
}
