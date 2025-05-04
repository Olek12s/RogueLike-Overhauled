package main.gui;

import main.gui.components.*;
import main.inventory.Slot;
import main.utilities.Position;

import java.awt.*;
import java.util.ArrayList;

public class Gui
{
    private GuiRenderer guiRenderer;
    private GuiUpdater guiUpdater;

    private BeltInvGui beltInvGui;
    private CraftingGui craftingGui;
    private DebugInfoGui debugInfoGui;
    private EquippedInvGui equippedInvGui;
    private HealthBarGui healthBar;
    private MainInvGui mainInvGui;
    private StatisticsGui statisticsGui;

    private static ArrayList<ClickableSlots> GUIsWithClickableSlots;

    private static int scaleX;
    private static int scaleY;
    private static int scale;
    private static final int baseSlotSize = 45;
    private static int slotSize;
    private static Font font;
    private static int scaledFontSize;
    private static final int baseFontSize = 14;

    public GuiRenderer getGuiRenderer() {return guiRenderer;}
    public GuiUpdater getGuiUpdater() {return guiUpdater;}
    public HealthBarGui getHealthBar() {return healthBar;}
    public static int getScaleX() {return scaleX;}
    public static int getScaleY() {return scaleY;}
    public static int getScale() {return scale;}
    public void setGuiRenderer(GuiRenderer guiRenderer) {this.guiRenderer = guiRenderer;}
    public void setGuiUpdater(GuiUpdater guiUpdater) {this.guiUpdater = guiUpdater;}
    public void setHealthBar(HealthBarGui healthBar) {this.healthBar = healthBar;}
    public MainInvGui getMainInvGui() {return mainInvGui;}
    public static int getBaseSlotSize() {return baseSlotSize;}
    public static int getSlotSize() {return slotSize;}
    public static void setSlotSize(int slotSize) {Gui.slotSize = slotSize;}
    public static Font getFont() {return font;}
    public static void setFont(Font font) {Gui.font = font;}
    public static int getScaledFontSize() {return scaledFontSize;}
    public static void setScaledFontSize(int scaledFontSize) {Gui.scaledFontSize = scaledFontSize;}
    public static int getBaseFontSize() {return baseFontSize;}
    public BeltInvGui getBeltInvGui() {return beltInvGui;}
    public CraftingGui getCraftingGui() {return craftingGui;}
    public DebugInfoGui getDebugInfoGui() {return debugInfoGui;}
    public EquippedInvGui getEquippedInvGui() {return equippedInvGui;}
    public StatisticsGui getStatisticsGui() {return statisticsGui;}

    public static void setScaleX(int scaleX) {Gui.scaleX = scaleX;}
    public static void setScaleY(int scaleY) {Gui.scaleY = scaleY;}
    public static void setScale(int scale) {Gui.scale = scale;}

    public static ArrayList<ClickableSlots> getGUIsWithClickableSlots() {return GUIsWithClickableSlots;}

    public Gui()
    {

       this.beltInvGui = new BeltInvGui();
       this.craftingGui = new CraftingGui();
       this.debugInfoGui = new DebugInfoGui();
       this.equippedInvGui = new EquippedInvGui();
       this.healthBar = new HealthBarGui();
       this.mainInvGui = new MainInvGui();
       this.statisticsGui = new StatisticsGui();

       this.GUIsWithClickableSlots = new ArrayList<>();
       GUIsWithClickableSlots.add(beltInvGui);
       GUIsWithClickableSlots.add(equippedInvGui);
       GUIsWithClickableSlots.add(mainInvGui);

       guiRenderer = new GuiRenderer(this);
       guiUpdater = new GuiUpdater(this);
    }

    public static Slot getClickedSlot(Position clickPos)
    {
        for (ClickableSlots gui : Gui.getGUIsWithClickableSlots())
        {
            Slot slot = gui.getSlotAt(clickPos);
            if (slot != null)
            {
                System.out.println(slot.getSlotType());
                return slot;
            }
        }
        System.out.println("null");
        return null;
    }
}
