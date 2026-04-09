
package action;

import combat.Combatant;
import combat.StatusEffect;

public class Defend implements Action 
{

    public void execute(Combatant user, Combatant target) 
    {

        user.addStatusEffect(new StatusEffect() 
        {

            private int duration = 2;

            public void apply(Combatant target) 
            {
                // temporary boost
                target.defense += 10;
                duration--;
            }

            public boolean isExpired() 
            {
                return duration <= 0;
            }
        });

        System.out.println(user.getName() + " is defending!");
    }
}
