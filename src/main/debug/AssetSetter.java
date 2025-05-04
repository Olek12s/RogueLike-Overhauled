package main.debug;

import main.GameController;
import main.Gamestate;
import main.item.armor.amulet.GelAmulet;
import main.item.armor.boots.DiamondBoots;
import main.item.armor.boots.IronBoots;
import main.item.armor.boots.RubyBoots;
import main.item.armor.boots.WoodenBoots;
import main.item.armor.chestplate.DiamondChestplate;
import main.item.armor.chestplate.IronChestplate;
import main.item.armor.chestplate.RubyChestplate;
import main.item.armor.chestplate.WoodenChestplate;
import main.item.armor.helmet.DiamondHelmet;
import main.item.armor.helmet.IronHelmet;
import main.item.armor.helmet.RubyHelmet;
import main.item.armor.helmet.WoodenHelmet;
import main.item.armor.pants.DiamondPants;
import main.item.armor.pants.IronPants;
import main.item.armor.pants.RubyPants;
import main.item.armor.pants.WoodenPants;
import main.item.armor.ring.GelRing;
import main.item.armor.shield.DiamondShield;
import main.item.armor.shield.IronShield;
import main.item.armor.shield.RubyShield;
import main.item.armor.shield.WoodenShield;
import main.item.ingredients.BlueFlower;
import main.item.ingredients.RedFlower;
import main.item.ingredients.YellowFlower;
import main.utilities.Position;

public class AssetSetter
{
    public AssetSetter()
    {
        Position source = GameController.getPlayer().getWorldPosition();

        for (int i = 0; i < 25; i++)
        {
            new BlueFlower(new Position(source.getX() - 400 + (i * 20), source.getY() - 300));
            new RedFlower(new Position(source.getX() - 300 + (i * 20), source.getY() - 200));
            new YellowFlower(new Position(source.getX() - 200 + (i * 20), source.getY() - 100));
        }


        // Gel
        new GelRing(new Position(source.getX() - 100, source.getY() + 0));
        new GelRing(new Position(source.getX() - 100, source.getY() + 20));
        new GelAmulet(new Position(source.getX() - 75, source.getY() + 0));

        // Wooden items (Y = 0)
        new WoodenBoots(new Position(source.getX() + 0, source.getY() + 0));
        new WoodenPants(new Position(source.getX() + 50, source.getY() + 0));
        new WoodenChestplate(new Position(source.getX() + 100, source.getY() + 0));
        new WoodenHelmet(new Position(source.getX() + 150, source.getY() + 0));
        new WoodenShield(new Position(source.getX() + 200, source.getY() + 0));

        // Iron items (Y = 100)
        new IronBoots(new Position(source.getX() + 0, source.getY() + 100));
        new IronPants(new Position(source.getX() + 50, source.getY() + 100));
        new IronChestplate(new Position(source.getX() + 100, source.getY() + 100));
        new IronHelmet(new Position(source.getX() + 150, source.getY() + 100));
        new IronShield(new Position(source.getX() + 200, source.getY() + 100));

        // Diamond items (Y = 200)
        new DiamondBoots(new Position(source.getX() + 0, source.getY() + 200));
        new DiamondPants(new Position(source.getX() + 50, source.getY() + 200));
        new DiamondChestplate(new Position(source.getX() + 100, source.getY() + 200));
        new DiamondHelmet(new Position(source.getX() + 150, source.getY() + 200));
        new DiamondShield(new Position(source.getX() + 200, source.getY() + 200));

        // Ruby items (Y = 300)
        new RubyBoots(new Position(source.getX() + 0, source.getY() + 300));
        new RubyPants(new Position(source.getX() + 50, source.getY() + 300));
        new RubyChestplate(new Position(source.getX() + 100, source.getY() + 300));
        new RubyHelmet(new Position(source.getX() + 150, source.getY() + 300));
        new RubyShield(new Position(source.getX() + 200, source.getY() + 300));
    }
}

