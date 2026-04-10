package action;

import combat.Combatant;
import combat.StatusEffect;

public class Defend implements Action 
{

    @Override
    public void execute(Combatant user, Combatant target) 
    {

        user.addStatusEffect(new StatusEffect() 
        {

            private int duration = 2;

            @Override
            public void apply(Combatant target) 
            {
                target.defense += 10; // increase defense
                duration--;
            }

            @Override
            public boolean isExpired() 
            {
                return duration <= 0;
            }
        });

        System.out.println(user.getName() + " is defending!");
    }
}
