package game;

import userinterface.GraphicsUserInterface;
import userinterface.UserInterface;

import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    private volatile World world;
    private UserInterface ui;
    private boolean end;
    private int tickrate;

    public Game(World world, UserInterface gui, boolean end, int tickrate) {
        this.world = world;
        this.ui = gui;
        this.end = end;
        this.tickrate = tickrate;
    }

    public Game() {
        this.world = new World();
        this.ui = new GraphicsUserInterface();
        this.end = false;
        this.tickrate = 10;
    }

    public void startGame(UserInterface ui) {
        //this.world = new World(); //TODO world loader
        this.ui = ui;
        this.end = false;
        this.tickrate = 10;

        long totalTicks = 0;

        AtomicBoolean displayEnd = new AtomicBoolean(false);
        ui.startDisplaying(world, displayEnd);

        long loopStartTime = System.nanoTime();
        while (!end) {
            totalTicks += 1;
            long startTime = System.nanoTime();

            //start of game loop
            this.updateWorld();
            //TODO complete game loop
            //end of game loop

            long totalTime = System.nanoTime() - startTime;
            try {
                Thread.sleep(1000 / tickrate - totalTime / 1000000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (totalTicks >= 100) {
                end = true;
                displayEnd.set(true);
            }
        }
        long avgTicks = totalTicks / ((System.nanoTime() - loopStartTime) / 1000000000);
        System.out.println("avg ticks: " + avgTicks);
    }

    public void updateWorld() {
        world.update();
        //TODO finish the update method
    }
}
