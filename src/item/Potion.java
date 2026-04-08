package item;

import combat.Combatant;

public class Potion implements Item{
  private int healAmount;

  public Potion(){
    this.healAmount = 100;
  }
  @Override
  public void use(Combatant user){
    int beforeHp = user.getHp();
    int afterHp = beforeHp + healAmount;

    if (afterHp > user.getMaxHp()){
      afterHo = user.getMaxHp();
    }
    user.setHp(afterHp);

    System.out.println(user.getName() + " -> Item -> Potion used: HP: " + beforeHp + " -> " + afterHp + " (+" + (afterHp - beforeHp) + ")");
                      
  
  @Override
  public String getName(){
    return "Potion";

  }
}
