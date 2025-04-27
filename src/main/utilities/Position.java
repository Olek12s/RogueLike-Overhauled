package main.utilities;

import main.world.map.*;
import main.world.tile.Tile;

import java.util.Objects;

public class Position
{
    private int x;
    private int y;


    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}
    public int getY() {return y;}
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setXY(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public static Position center(Position rawPosition, MapID mapID)
    {
        PrefferedMapSize prefMapSize = MapManager.getPrefferedMapSize();
        int mapTiles = PrefferedMapSize.mapSizeToInteger(prefMapSize, mapID);

        int fullPx = mapTiles * Tile.getTileSize();
        int halfPx = fullPx / 2;

        int centeredX = rawPosition.getX() - halfPx;
        int centeredY = rawPosition.getY() - halfPx;

        return new Position(centeredX, centeredY);
    }

    public Position copy()
    {
        return new Position(this.x, this.y);
    }

    public void incrementX()
    {
        x++;
    }
    public void incrementY()
    {
        y++;
    }

    public void decrementX()
    {
        x--;
    }

    public void decrementY()
    {
        y--;
    }


    @Override
    public String toString()
    {
        return "["+ x + " " + y + "]";
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Position pos = (Position) obj;
        return x == pos.x && y == pos.y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x, y);
    }
}
