package item;

import combat.Combatant;

public class PowerStone implements Item {

    public PowerStone(String difficulty) {
        
    }

    @Override
    public void use(Combatant user) {
        if (user instanceof Player) {
            ((Player) user).setCooldown(0);
        }
        System.out.println(user.getName() + " → Item → Power Stone used");
    }

    @Override
    public String getName() {
        return "Power Stone";
    }
}
