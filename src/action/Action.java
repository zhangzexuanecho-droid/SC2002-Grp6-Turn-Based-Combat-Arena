package action;

import combat.Combatant;

public interface Action 
{
    void execute(Combatant user, Combatant target);

    boolean requiresTarget(); 
}
