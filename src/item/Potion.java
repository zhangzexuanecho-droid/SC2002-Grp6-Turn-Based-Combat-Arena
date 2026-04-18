package item;

import combat.Combatant;

public class Potion implements Item {
    private static final int HEAL_AMOUNT = 100;

    public Potion(String difficulty) {
    }

    @Override
    public void use(Combatant target) {
        int beforeHP = target.getHp();
        target.heal(HEAL_AMOUNT);
        int afterHP = target.getHp();
        System.out.println(target.getName() + " → Item → Potion used: HP: "
                + beforeHP + " → " + afterHP + " (+" + (afterHP - beforeHP) + ")");
    }

    @Override
    public String getName() {
        return "Potion";
    }
}
