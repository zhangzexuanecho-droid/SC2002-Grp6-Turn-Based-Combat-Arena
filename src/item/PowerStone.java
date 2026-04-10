package item;

import combat.Combatant;

public class PowerStone implements Item {

    public PowerStone(String difficulty) {
        
    }

    @Override
    public void use(Combatant user) {
        System.out.println(user.getName() + " → Item → Power Stone used");
    }

    @Override
    public String getName() {
        return "Power Stone";
    }
}
