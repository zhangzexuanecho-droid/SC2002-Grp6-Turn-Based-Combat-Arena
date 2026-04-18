package item;

import combat.Combatant;
import combat.statusEffect.SmokeBombEffect;

public class SmokeBomb implements Item {

    private int duration; 

    public SmokeBomb(String difficulty) {
        this.duration = difficulty.equalsIgnoreCase("hard") ? 3 : 2;
    
    }

    @Override
    public void use(Combatant user) {
        user.addStatusEffect(new SmokeBombEffect(duration));
        System.out.println(user.getName() + " → Item → Smoke Bomb used: "
                + "Enemy attacks deal 0 damage this turn + next");

    }

    @Override
    public String getName() {
        return "Smoke Bomb";
    }

    public int getDuration() {
        return duration;
    }
}
