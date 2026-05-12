package game;

import userinterface.ConsoleUserInterface;
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

    public void startGame() {
        this.worldManager.startGame();
    }

    public void startDisplay(UserInterface ui) {
        this.ui = ui;
        ui.startDisplaying(worldManager, displayEnd);
    }
}
