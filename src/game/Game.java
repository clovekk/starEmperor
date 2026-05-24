package game;

import userinterface.console.ConsoleUserInterface;
import userinterface.UserInterface;

import java.util.concurrent.atomic.AtomicBoolean;

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
        this.ui = new ConsoleUserInterface();
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

    public void startGame() {
        this.worldManager.startGame();
    }

    public void startDisplay(UserInterface ui) {
        this.ui = ui;
        this.ui.startDisplaying(worldManager, displayEnd);
    }

    public void startDisplay() {
        this.startDisplay(this.ui);
    }

    public synchronized void pauseGame() {
        this.worldManager.pauseGame();
    }

    public synchronized void unpauseGame() {
        this.worldManager.unpauseGame();
    }

    public void stopGame() {
        this.worldManager.setEnd(true);
    }

    public void stopDisplay() {
        this.displayEnd.set(true);
        System.out.println("User interface end flag updated to: " + this.getDisplayEnd().toString());
    }
}
