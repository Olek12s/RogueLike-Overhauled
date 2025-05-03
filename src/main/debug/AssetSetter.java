package main.debug;

import main.item.armor.chestplate.WoodenChestplate;
import main.item.ingredients.BlueFlower;
import main.utilities.Position;

public class AssetSetter
{
   public AssetSetter()
   {

       for (int i = 0; i < 00; i++)
       {
           for (int j = 0; j < 000; j++)
           {
               new BlueFlower(new Position(i*8, j*8));
           }
       }




       new BlueFlower(new Position(-538, 1018));
       new BlueFlower(new Position(-300, 1018));
       new WoodenChestplate(new Position(-500, 1000));
   }
}
