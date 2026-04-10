package action;

import combat.Combatant;
import combat.Player;
import combat.StatusEffect;

public class ShieldBash extends SpecialSkill 
{
    @Override
    protected void applySkill (Player user, Combatant target)
    {
      target.recieveDamage(user.getAttack());
      target.addStatusEffect(new StatusEffect()
      {
        private int duration = 2;

        @Override 
        public boolean isExpired()
        {
          return duration <=0;
        }
      });
      
      System.out.println(user.getName() + " uses Shield Bash!");
    }
}
          
                               
                
