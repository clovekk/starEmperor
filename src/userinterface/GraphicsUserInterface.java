package userinterface;

import game.World;

public class GraphicsUserInterface implements UserInterface, Runnable {
    private volatile World world;
    private volatile boolean end;

    @Override
    public void startDisplaying(World world) {
        this.world = world;
        this.run();
    }

    @Override
    public synchronized void endDisplaying() {
        this.end = true;
    }

    @Override
    public void run() {

    }
}
