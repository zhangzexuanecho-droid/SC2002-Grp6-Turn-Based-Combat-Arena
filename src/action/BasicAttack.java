package action;

import combat.Combatant;

public class BasicAttack implements Action 
{

    @Override
    public void execute(Combatant user, Combatant target) 
    {

        int damage = user.getAttack(); 

        target.receiveDamage(damage);

        //System.out.println(user.getName() + " attacks " + target.getName() + "!");
    }
}
