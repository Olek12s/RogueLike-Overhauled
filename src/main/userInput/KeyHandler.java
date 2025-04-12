package main.userInput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    private static boolean W_PRESSED = false;
    private static boolean A_PRESSED = false;
    private static boolean S_PRESSED = false;
    private static boolean D_PRESSED = false;

    private static boolean UP_PRESSED = false;
    private static boolean DOWN_PRESSED = false;
    private static boolean LEFT_PRESSED = false;
    private static boolean RIGHT_PRESSED = false;


    private static boolean I_PRESSED = false;
    private static boolean E_PRESSED = false;
    private static boolean F_PRESSED = false;
    private static boolean C_PRESSED = false;
    private static boolean SPACE_PRESSED = false;

    private static boolean CTRL_PRESSED = false;

    private static boolean ONE_PRESSED = false;
    private static boolean TWO_PRESSED = false;
    private static boolean THREE_PRESSED = false;
    private static boolean FOUR_PRESSED = false;
    private static boolean FIVE_PRESSED = false;
    private static boolean SIX_PRESSED = false;
    private static boolean SEVEN_PRESSED = false;
    private static boolean EIGHT_PRESSED = false;
    private static boolean NINE_PRESSED = false;
    private static boolean ZERO_PRESSED = false;

    public static boolean isW_PRESSED() {return W_PRESSED;}
    public static boolean isA_PRESSED() {return A_PRESSED;}
    public static boolean isS_PRESSED() {return S_PRESSED;}
    public static boolean isD_PRESSED() {return D_PRESSED;}
    public static boolean isUP_PRESSED() {return UP_PRESSED;}
    public static boolean isDOWN_PRESSED() {return DOWN_PRESSED;}
    public static boolean isLEFT_PRESSED() {return LEFT_PRESSED;}
    public static boolean isRIGHT_PRESSED() {return RIGHT_PRESSED;}
    public static boolean isI_PRESSED() {return I_PRESSED;}
    public static boolean isE_PRESSED() {return E_PRESSED;}
    public static boolean isF_PRESSED() {return F_PRESSED;}
    public static boolean isC_PRESSED() {return C_PRESSED;}
    public static boolean isSPACE_PRESSED() {return SPACE_PRESSED;}
    public static boolean isCTRL_PRESSED() {return CTRL_PRESSED;}
    public static boolean isONE_PRESSED() {return ONE_PRESSED;}
    public static boolean isTWO_PRESSED() {return TWO_PRESSED;}
    public static boolean isTHREE_PRESSED() {return THREE_PRESSED;}
    public static boolean isFOUR_PRESSED() {return FOUR_PRESSED;}
    public static boolean isFIVE_PRESSED() {return FIVE_PRESSED;}
    public static boolean isSIX_PRESSED() {return SIX_PRESSED;}
    public static boolean isSEVEN_PRESSED() {return SEVEN_PRESSED;}
    public static boolean isEIGHT_PRESSED() {return EIGHT_PRESSED;}
    public static boolean isNINE_PRESSED() {return NINE_PRESSED;}
    public static boolean isZERO_PRESSED() {return ZERO_PRESSED;}

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        switch (code)
        {
            case KeyEvent.VK_W: W_PRESSED = true; break;
            case KeyEvent.VK_A: A_PRESSED = true; break;
            case KeyEvent.VK_S: S_PRESSED = true; break;
            case KeyEvent.VK_D: D_PRESSED = true; break;

            case KeyEvent.VK_UP: UP_PRESSED = true; break;
            case KeyEvent.VK_LEFT: LEFT_PRESSED = true; break;
            case KeyEvent.VK_DOWN: DOWN_PRESSED = true; break;
            case KeyEvent.VK_RIGHT: RIGHT_PRESSED = true; break;

            case KeyEvent.VK_I: I_PRESSED = true; break;
            case KeyEvent.VK_E: E_PRESSED = true; break;
            case KeyEvent.VK_F: F_PRESSED = true; break;
            case KeyEvent.VK_C: C_PRESSED = true; break;
            case KeyEvent.VK_SPACE: SPACE_PRESSED = true; break;

            case KeyEvent.VK_CONTROL: CTRL_PRESSED = true; break;

            case KeyEvent.VK_1: ONE_PRESSED = true; break;
            case KeyEvent.VK_2: TWO_PRESSED = true; break;
            case KeyEvent.VK_3: THREE_PRESSED = true; break;
            case KeyEvent.VK_4: FOUR_PRESSED = true; break;
            case KeyEvent.VK_5: FIVE_PRESSED = true; break;
            case KeyEvent.VK_6: SIX_PRESSED = true; break;
            case KeyEvent.VK_7: SEVEN_PRESSED = true; break;
            case KeyEvent.VK_8: EIGHT_PRESSED = true; break;
            case KeyEvent.VK_9: NINE_PRESSED = true; break;
            case KeyEvent.VK_0: ZERO_PRESSED = true; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        switch (code)
        {
            case KeyEvent.VK_W: W_PRESSED = false; break;
            case KeyEvent.VK_A: A_PRESSED = false; break;
            case KeyEvent.VK_S: S_PRESSED = false; break;
            case KeyEvent.VK_D: D_PRESSED = false; break;

            case KeyEvent.VK_UP: UP_PRESSED = false; break;
            case KeyEvent.VK_LEFT: LEFT_PRESSED = false; break;
            case KeyEvent.VK_DOWN: DOWN_PRESSED = false; break;
            case KeyEvent.VK_RIGHT: RIGHT_PRESSED = false; break;

            case KeyEvent.VK_I: I_PRESSED = false; break;
            case KeyEvent.VK_E: E_PRESSED = false; break;
            case KeyEvent.VK_F: F_PRESSED = false; break;
            case KeyEvent.VK_C: C_PRESSED = false; break;
            case KeyEvent.VK_SPACE: SPACE_PRESSED = false; break;

            case KeyEvent.VK_CONTROL: CTRL_PRESSED = false; break;

            case KeyEvent.VK_1: ONE_PRESSED = false; break;
            case KeyEvent.VK_2: TWO_PRESSED = false; break;
            case KeyEvent.VK_3: THREE_PRESSED = false; break;
            case KeyEvent.VK_4: FOUR_PRESSED = false; break;
            case KeyEvent.VK_5: FIVE_PRESSED = false; break;
            case KeyEvent.VK_6: SIX_PRESSED = false; break;
            case KeyEvent.VK_7: SEVEN_PRESSED = false; break;
            case KeyEvent.VK_8: EIGHT_PRESSED = false; break;
            case KeyEvent.VK_9: NINE_PRESSED = false; break;
            case KeyEvent.VK_0: ZERO_PRESSED = false; break;
        }
    }
    }
