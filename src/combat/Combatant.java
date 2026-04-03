import java.util.ArrayList;
import java.util.List;

public abstract class Combatant {
    protected String name;
    protected int hp;
    protected int mp;
    protected int attack;
    protected int defense;
    protected int speed;
    protected List<IStatusEffect> statusEffects;

    public Combatant(String name, int hp, int mp, int attack, int defense, int speed) {
        this.name = name;
        this.hp = hp;
        this.mp = mp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.statusEffects = new ArrayList<>();
    }

    public int takeDamage(int amount) {
        int actualDamage = Math.max(0, amount - defense);
        hp = Math.max(0, hp - actualDamage);
        return actualDamage;
    }

    public void heal(int amount) {
        hp += amount;
    }

    public void addStatusEffect(IStatusEffect effect) {
        statusEffects.add(effect);
    }

    public void updateStatusEffects() {
        for (IStatusEffect effect : statusEffects) {
            effect.applyEffect(this);
            effect.decreaseDuration();
        }
        statusEffects.removeIf(IStatusEffect::isExpired);
    }

    public boolean isAlive() {
        return hp > 0;
    }
}
