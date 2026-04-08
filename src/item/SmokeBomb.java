package item;

import combat.Combatant;

public class SmokeBomb implements Item {

    private int duration; // how many rounds the effect lasts

    public SmokeBomb(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "easy": duration = 2; break;
            case "medium": duration = 2; break;
            case "hard": duration = 3; break;
            default: duration = 2;
        }
    }

    @Override
    public void use(Combatant user) {
        
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
