package main.camera;

import main.entity.Entity;
import main.GameController;
import main.IUpdatable;
import main.world.map.Chunk;
import main.world.map.Tile;

public class CameraUpdater implements IUpdatable
{
    private Camera camera;

    public CameraUpdater(Camera camera)
    {
        this.camera = camera;
        GameController.addUpdatable(this);
    }

    @Override
    public void update()
    {
        Entity player = GameController.getPlayer();
        camera.setPositionToFocus(player);
        updateRenderDistance();
        //System.out.println(camera.getPositionToFocus());
    }

    private void updateRenderDistance()
    {
        int windowWidth = GameController.getInstance().getWidth();
        int windowHeight = GameController.getInstance().getHeight();
        double scaleFactor = Camera.getScaleFactor();

        double visibleWidth = windowWidth / scaleFactor;
        double visibleHeight = windowHeight / scaleFactor;

        int chunkPixelSize = Chunk.getChunkSize() * Tile.getTileSize();

        int chunksX = (int) Math.ceil(visibleWidth / chunkPixelSize);
        int chunksY = (int) Math.ceil(visibleHeight / chunkPixelSize);

        int calculatedRenderDistance = Math.max(chunksX, chunksY) / 2 + 1;
        Camera.setRenderDistance(calculatedRenderDistance);
    }
}
