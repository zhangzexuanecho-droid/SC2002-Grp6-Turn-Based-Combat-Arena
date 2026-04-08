package item;

import combat.Combatant;

public class Potion implements Item {

    private int healAmount;

    public Potion(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "easy": healAmount = 100; break;
            case "medium": healAmount = 100; break;
            case "hard": healAmount = 150; break; // slightly higher for hard
            default: healAmount = 100;
        }
    }

    @Override
    public void use(Combatant target) {
        int beforeHP = target.getHp();
        target.heal(healAmount);
        int afterHP = target.getHp();

        System.out.println(target.getName() + " → Item → Potion used: HP: "
                + beforeHP + " → " + afterHP + " (+" + (afterHP - beforeHP) + ")");
    }

    @Override
    public String getName() {
        return "Potion";
    }
}
