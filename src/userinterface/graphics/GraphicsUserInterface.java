package userinterface.graphics;

import game.WorldManager;
import userinterface.UserInterface;

import java.util.concurrent.atomic.AtomicBoolean;

public class GraphicsUserInterface extends Thread implements UserInterface {
    private volatile WorldManager worldManager;
    private volatile boolean end;

    @Override
    public void startDisplaying(WorldManager worldManager, AtomicBoolean end) {
        this.worldManager = worldManager;
        this.start();
    }

    @Override
    public void run() {
        super.run();
        int temp = 0;
        while(!Thread.interrupted()) {
            //System.out.println(world.getStarSystems());
            worldManager.getWorld().getPlayers().values().stream().forEach(p -> System.out.println(p.getResources()));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
