package item;

import combat.Combatant;
import java.util.List;

public class SmokeBomb implements Item {

    private int effectDuration;

    public SmokeBomb() {
        this.effectDuration = 1; // matches your sample: 1 turn remaining
    }

    @Override
    public void use(Combatant user) {
        // Apply Smoke Bomb effect to the battlefield
        user.activateSmokeBomb(effectDuration); // assume player class has a method to reduce damage from enemies

        System.out.println(user.getName() + " → Item → Smoke Bomb used: Enemy attacks deal 0 damage this turn + next");
    }

    @Override
    public String getName() {
        return "Smoke Bomb";
    }
}
