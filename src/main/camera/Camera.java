package main.camera;

import main.entity.Entity;
import main.GameController;
import main.utilities.Position;

public class Camera
{
    private CameraUpdater cameraUpdater;
    private static double scaleFactor = 0.3f;
    private static int renderDistance = 1;
    private static int simulationDistance = 999;        // TODO: implement logic for simulation distance
    private static Position positionToFocus;
    private static final double MIN_ZOOM = 0.01;
    private static final double MAX_ZOOM = 2.0;
    private static final double ZOOM_STEP = 0.03;

    public CameraUpdater getCameraUpdater() { return cameraUpdater; }
    public static double getScaleFactor() { return scaleFactor; }
    public static void increaseScaleFactor() {scaleFactor += 0.05f;}
    public static void decreaseScaleFactor() {scaleFactor -= 0.05f;}
    public Position getPositionToFocus() { return positionToFocus; }
    public static int getRenderDistance() {return renderDistance;}
    public static int getSimulationDistance() {return simulationDistance;}
    public static void setRenderDistance(int renderDistance) {Camera.renderDistance = renderDistance;}

    public Camera()
    {
        this.cameraUpdater = new CameraUpdater(this);
        setPositionToFocus(GameController.getPlayer().getWorldPosition());
    }

    public static Position toScreenPosition(int worldX, int worldY)
    {
        GameController gc = GameController.getInstance();

        // object's world cooridanets - camera position"
        int screenX = (int)((worldX - positionToFocus.getX()) * scaleFactor + gc.getWidth() / 2);
        int screenY = (int)((worldY - positionToFocus.getY()) * scaleFactor + gc.getHeight() / 2);

        return new Position(screenX, screenY);
    }
    public static Position toScreenPosition(Position worldPosition)
    {
        return toScreenPosition(worldPosition.getX(), worldPosition.getY());
    }

    public void setPositionToFocus(Position positionToFocus) {
        setPositionToFocus(positionToFocus, 0, 0);
    }

    public void setPositionToFocus(Position positionToFocus, int offsetX, int offsetY)
    {
        if (Camera.positionToFocus == null) {
            Camera.positionToFocus = new Position(0, 0);
        }
        Camera.positionToFocus.setX(positionToFocus.getX() + offsetX);
        Camera.positionToFocus.setY(positionToFocus.getY() + offsetY);
    }

    public void setPositionToFocus(Entity entity)
    {
        int offsetX = entity.getWidth() / 2;
        int offsetY = entity.getHeight() / 2;
        setPositionToFocus(entity.getWorldPosition(), offsetX, offsetY);
    }

    public void setPositionToFocus(Entity entity, int offsetX, int offsetY)
    {
        setPositionToFocus(entity.getWorldPosition(), offsetX, offsetY);
    }

    public static void changeCameraZoom(int scroll)
    {
        if (scroll > 0)         // zoom out
        {
            scaleFactor = Math.max(MIN_ZOOM, scaleFactor - ZOOM_STEP);
        }
        else if (scroll < 0)    // zoom in
        {
            scaleFactor = Math.min(MAX_ZOOM, scaleFactor + ZOOM_STEP);
        }
    }
}
