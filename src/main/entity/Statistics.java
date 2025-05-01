package main.entity;

public class Statistics
{
    private int currentHealth;
    private int maxHealth;
    private int regeneration;

    private int maxMana;                      // current mana - needed to cast spells or hold specific items
    private int mana;                         // max mana - how much spells you can cast at short amount of time

    private int armour;                       // how resistant entity is against physical damage
    private int magicArmour;                  // how resistant entity is against magical damage

    private int strength;                     // statistic exclusive for melee weapons - how strong you hit
    private int dexterity;                    // statistic exclusive for bows and actions needing dexterity - how powerful you can shoot
    private int intellect;                    // statistic exclusive for magic - how strong your spells are

    private int maxStamina;
    private int stamina;                      //

    private int exp;
    private int expReward;
    private int nextLevelExp;




    public int getCurrentHealth() {return currentHealth;}
    public void setCurrentHealth(int currentHealth) {this.currentHealth = currentHealth;}
    public int getMaxHealth() {return maxHealth;}
    public void setMaxHealth(int maxHealth) {this.maxHealth = maxHealth;}
    public int getRegeneration() {return regeneration;}
    public void setRegeneration(int regeneration) {this.regeneration = regeneration;}
    public int getMaxMana() {return maxMana;}
    public void setMaxMana(int maxMana) {this.maxMana = maxMana;}
    public int getMana() {return mana;}
    public void setMana(int mana) {this.mana = mana;}
    public int getArmour() {return armour;}
    public void setArmour(int armour) {this.armour = armour;}
    public int getMagicArmour() {return magicArmour;}
    public void setMagicArmour(int magicArmour) {this.magicArmour = magicArmour;}
    public int getStrength() {return strength;}
    public void setStrength(int strength) {this.strength = strength;}
    public int getDexterity() {return dexterity;}
    public void setDexterity(int dexterity) {this.dexterity = dexterity;}
    public int getIntellect() {return intellect;}
    public void setIntellect(int intellect) {this.intellect = intellect;}
    public int getMaxStamina() {return maxStamina;}
    public void setMaxStamina(int maxStamina) {this.maxStamina = maxStamina;}
    public int getStamina() {return stamina;}
    public void setStamina(int stamina) {this.stamina = stamina;}
    public int getExp() {return exp;}
    public void setExp(int exp) {this.exp = exp;}
    public int getExpReward() {return expReward;}
    public void setExpReward(int expReward) {this.expReward = expReward;}
    public int getNextLevelExp() {return nextLevelExp;}
    public void setNextLevelExp(int nextLevelExp) {this.nextLevelExp = nextLevelExp;}

    public Statistics(int maxHealth)
    {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }
}
