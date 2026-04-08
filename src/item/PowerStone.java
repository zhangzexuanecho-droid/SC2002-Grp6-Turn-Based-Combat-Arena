package item;

import combat.Combatant;

public class PowerStone implements Item {

    private int atkBoost;
    private int duration;

    public PowerStone(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "easy": atkBoost = 20; duration = 1; break;
            case "medium": atkBoost = 20; duration = 1; break;
            case "hard": atkBoost = 30; duration = 1; break;
            default: atkBoost = 20; duration = 1;
        }
    }

    @Override
    public void use(Combatant user) {
        
        System.out.println(user.getName() + " → Item → Power Stone used: "
                + "ATK increased by " + atkBoost + " for " + duration + " turn"
                + (duration > 1 ? "s" : ""));
    }

    @Override
    public String getName() {
        return "Power Stone";
    }

    public int getAtkBoost() {
        return atkBoost;
    }

    public int getDuration() {
        return duration;
    }
}
