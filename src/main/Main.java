package main;

import javax.swing.*;


public class Main
{
    static private JFrame window;


    public static void main(String[] args)
    {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("RogueLike Overhauled");
        GameController gameController = GameController.getInstance();
        window.setSize(1200, 800);
        window.add(gameController);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
