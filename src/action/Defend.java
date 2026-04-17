package action;

import combat.Combatant;
import combat.statusEffect.StatusEffect;

public class Defend implements Action 
{

    @Override
    public void execute(Combatant user, Combatant target) 
    {

        user.addStatusEffect(new StatusEffect() {

            private int duration = 2;
            private boolean applied = false;
        
            @Override
            public void apply(Combatant target) {
                if (!applied) {
                    target.setDefense(target.getDefense() + 10);
                    applied = true;
                }
                duration--;
        
                if (duration <= 0) {
                    target.setDefense(target.getDefense() - 10); // remove buff
                }
            }
        
            @Override
            public boolean isExpired() {
                return duration <= 0;
            }

            @Override
            public boolean preventsAction() {
                return false; 
            }

            @Override
            public int modifyIncomingDamage(int damage) {
                return damage; 
            }
        });

        System.out.println(user.getName() + " is defending!");
    }

    @Override
    public boolean requiresTarget() 
    {
        return false;
    }
}
