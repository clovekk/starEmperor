package userinterface;

import game.World;

import java.util.concurrent.atomic.AtomicBoolean;

public class GraphicsUserInterface extends Thread implements UserInterface {
    private volatile World world;
    private volatile boolean end;

    @Override
    public void startDisplaying(World world, AtomicBoolean end) {
        this.world = world;
        this.start();
    }

    @Override
    public void run() {
        super.run();
        int temp = 0;
        while(!Thread.interrupted()) {
            //System.out.println(world.getStarSystems());
            world.getPlayers().values().stream().forEach(p -> System.out.println(p.getResources()));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
