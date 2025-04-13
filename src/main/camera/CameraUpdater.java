package main.camera;

import main.entity.Entity;
import main.GameController;
import main.IUpdatable;

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
        //System.out.println(camera.getPositionToFocus());
    }
}
