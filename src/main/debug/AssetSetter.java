package main.debug;

import main.item.ingredients.BlueFlower;
import main.utilities.Position;

public class AssetSetter
{
   public AssetSetter()
   {

       for (int i = 0; i < 100; i++)
       {
           for (int j = 0; j < 100; j++)
           {
               new BlueFlower(new Position(i*8, j*8));
           }
       }




       new BlueFlower(new Position(-538, 1018));
       new BlueFlower(new Position(-300, 1018));
   }
}
