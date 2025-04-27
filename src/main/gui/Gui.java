package main.gui;

public class Gui
{
    private GuiRenderer guiRenderer;
    private GuiUpdater guiUpdater;
    private HealthBar healthBar;

    private static int scaleX;
    private static int scaleY;
    private static int scale;

    public GuiRenderer getGuiRenderer() {return guiRenderer;}
    public GuiUpdater getGuiUpdater() {return guiUpdater;}
    public HealthBar getHealthBar() {return healthBar;}
    public static int getScaleX() {return scaleX;}
    public static int getScaleY() {return scaleY;}
    public static int getScale() {return scale;}
    public void setGuiRenderer(GuiRenderer guiRenderer) {this.guiRenderer = guiRenderer;}
    public void setGuiUpdater(GuiUpdater guiUpdater) {this.guiUpdater = guiUpdater;}
    public void setHealthBar(HealthBar healthBar) {this.healthBar = healthBar;}

    public static void setScaleX(int scaleX) {Gui.scaleX = scaleX;}
    public static void setScaleY(int scaleY) {Gui.scaleY = scaleY;}
    public static void setScale(int scale) {Gui.scale = scale;}

    public Gui()
    {
       guiRenderer = new GuiRenderer(this);
       guiUpdater = new GuiUpdater(this);

        this.healthBar = new HealthBar();
    }
}
