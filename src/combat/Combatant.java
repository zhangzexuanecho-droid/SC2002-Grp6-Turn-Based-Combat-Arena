import java.util.ArrayList;
import java.util.List;

public abstract class Combatant {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attack;
    protected int defense;
    protected int speed;
    protected List<IStatusEffect> statusEffects;

    public Combatant(String name, int hp, int attack, int defense, int speed) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.statusEffects = new ArrayList<>();
    }

    public void receiveDamage(int amount) {
        int actualDamage = Math.max(0, amount - defense);
        hp = Math.max(0, hp - actualDamage);
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void addStatusEffect(IStatusEffect effect) {
        statusEffects.add(effect);
    }

    public void applyStatusEffects() {
        for (IStatusEffect effect : statusEffects) {
            effect.applyEffect(this);
        }
        statusEffects.removeIf(IStatusEffect::isExpired);
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }
}
