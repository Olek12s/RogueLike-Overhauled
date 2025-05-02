package main.item;

public class ItemStatistics
{
    private String itemName;

    private int physicalDamage;
    private int magicalDamage;
    private int inevitableDamage;
    private int armor;
    private int magicalArmor;
    private float movementSpeedPenalty = 1;   // multipied by this value. 1 - no penalty. greater than 1 - penalty. lower than 1 - negative penalty


    public ItemStatistics()
    {

    }

    public String getItemName() {return itemName;}
    public void setItemName(String itemName) {this.itemName = itemName;}
    public int getPhysicalDamage() {return physicalDamage;}
    public void setPhysicalDamage(int physicalDamage) {this.physicalDamage = physicalDamage;}
    public int getMagicalDamage() {return magicalDamage;}
    public void setMagicalDamage(int magicalDamage) {this.magicalDamage = magicalDamage;}
    public int getInevitableDamage() {return inevitableDamage;}
    public void setInevitableDamage(int inevitableDamage) {this.inevitableDamage = inevitableDamage;}
    public int getArmor() {return armor;}
    public void setArmor(int armor) {this.armor = armor;}
    public int getMagicalArmor() {return magicalArmor;}
    public void setMagicalArmor(int magicalArmor) {this.magicalArmor = magicalArmor;}
    public float getMovementSpeedPenalty() {return movementSpeedPenalty;}
    public void setMovementSpeedPenalty(float movementSpeedPenalty)
    {
        if (movementSpeedPenalty < 0) this.movementSpeedPenalty = 0;
        else this.movementSpeedPenalty = movementSpeedPenalty;
    }
}
