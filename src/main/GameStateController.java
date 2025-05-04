package main;

import main.userInput.KeyHandler;

import java.util.HashSet;

public class GameStateController implements IUpdatable
{
    private HashSet<Gamestate> gamestates;


    public GameStateController()
    {
        gamestates = new HashSet<>();
        GameController.addUpdatable(this);
    }


    @Override
    public void update()
    {
        checkPendingState();
        checkInventoryState();
        checkCraftingState();
    }

    public boolean isInState(Gamestate gamestate)
    {
        return gamestates.contains(gamestate);
    }

    private void checkPendingState()
    {

    }

    private void checkInventoryState()
    {
        if (KeyHandler.isE_JustPressed())
        {
            if (!gamestates.contains(Gamestate.INVENTORY))
            {
                gamestates.add(Gamestate.INVENTORY);
            }
            else
            {
                gamestates.remove(Gamestate.INVENTORY);
            }
        }
    }

    private void checkCraftingState()
    {

    }
}
