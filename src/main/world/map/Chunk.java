package main.world.map;

import main.entity.Entity;
import main.item.Item;
import main.utilities.Position;
import main.world.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class Chunk
{
    private static final int chunkSize = 8;
    private Tile[][] tiles;
    private Position chunkWorldPosition;
    private List<Entity> entities;
    private List<Item> items;
    private int xIndex;
    private int yIndex;

    public Tile[][] getTiles() {return tiles;}
    public Position getChunkWorldPosition() {return chunkWorldPosition;}
    public int getxIndex() {return xIndex;}
    public int getyIndex() {return yIndex;}
    public List<Entity> getEntities() {return entities;}
    public List<Item> getItems() {return items;}

    public static int getChunkSize() {return chunkSize;}

    public Chunk(Tile[][] tiles, int xIndex, int yIndex)
    {
        this.entities = new ArrayList<>();
        this.items = new ArrayList<>();
        this.tiles = tiles;
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        chunkWorldPosition = tiles[0][0].getTileWorldPosition();
    }

    public void addEntity(Entity entity)
    {
        if (!entities.contains(entity))
        {
            entities.add(entity);
        }
    }
    public void removeEntity(Entity entity) {entities.remove(entity);}

    public void addItem(Item item)
    {
        if (!items.contains(item))
        {
            items.add(item);
        }
    }
    public void removeItem(Item item) {items.remove(item);}
}
