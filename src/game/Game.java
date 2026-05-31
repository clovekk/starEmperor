package game;

import userinterface.console.ConsoleUserInterface;
import userinterface.UserInterface;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This is the main class that contains the gui and world manager
 */
public class Game {
    private volatile WorldManager worldManager;
    private UserInterface ui;
    private volatile AtomicBoolean displayEnd;

    public Game(WorldManager worldManager, UserInterface ui, Boolean displayEnd) {
        this.worldManager = worldManager;
        this.ui = ui;
        this.displayEnd = new AtomicBoolean(displayEnd);
    }

    public Game() {
        this.worldManager = new WorldManager();
        this.ui = new ConsoleUserInterface(worldManager, new AtomicBoolean(false));
        this.displayEnd = new AtomicBoolean(false);
    }

    public WorldManager getWorldManager() {
        return worldManager;
    }
    public UserInterface getUi() {
        return ui;
    }
    public AtomicBoolean getDisplayEnd() {
        return displayEnd;
    }

    public void setWorldManager(WorldManager worldManager) {
        this.worldManager = worldManager;
    }
    public void setUi(UserInterface ui) {
        this.ui = ui;
    }
    public void setDisplayEnd(AtomicBoolean displayEnd) {
        this.displayEnd = displayEnd;
    }

    /**
     * This method starts the game WorldManager thread
     */
    public void startGame() {
        this.worldManager.startGame();
    }

    /**
     * This method starts the game GraphicsUserInterface thread
     * @param ui the UserInterface supposed to display the game
     */
    public void startDisplay(UserInterface ui) {
        this.ui = ui;
        this.ui.startDisplaying();
    }

    /**
     * This method starts the game GraphicsUserInterface thread
     */
    public void startDisplay() {
        this.startDisplay(this.ui);
    }

    /**
     * This method pauses the game WorldManager thread
     */
    public synchronized void pauseGame() {
        this.worldManager.pauseGame();
    }

    /**
     * This method unpauses the game WorldManager thread
     */
    public synchronized void unpauseGame() {
        this.worldManager.unpauseGame();
    }

    /**
     * This method stops the game WorldManager thread
     */
    public void stopGame() {
        this.worldManager.setEnd(true);
    }

    /**
     * This method stops the game GraphicsUserInterface thread
     */
    public void stopDisplay() {
        this.displayEnd.set(true);
        System.out.println("User interface end flag updated to: " + this.getDisplayEnd().toString());
    }
}
