package game;

import userinterface.UserInterface;

import java.time.Duration;

public class WorldManager extends Thread {
    private volatile boolean paused;
    private volatile World world;
    private boolean end;
    private int tickrate;
    private long targetTickTime;

    public WorldManager(World world, UserInterface gui, boolean end, int tickrate) {
        this.world = world;
        this.end = end;
        this.setTickrate(tickrate);
        this.paused = false;
        this.targetTickTime = 1000000000 / this.tickrate;
    }

    public WorldManager() {
        this.world = new World();
        this.end = false;
        this.tickrate = 10;
        this.paused = false;
        this.targetTickTime = 1000000000 / this.tickrate;
    }

    public void startGame() {
        //this.world = new World(); //TODO world loader
        this.end = false;
        this.paused = false;

        this.start();
    }

    public World getWorld() {
        return world;
    }
    public boolean isEnd() {
        return end;
    }
    public int getTickrate() {
        return tickrate;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    public void setEnd(boolean end) {
        this.end = end;
    }
    public void setTickrate(int tickrate) {
        if (tickrate <= 0) {
            System.out.println("ERROR - INVALID TICKRATE: " + tickrate + "\n TICKRATE SET TO DEFAULT(10)");
            this.setTickrate(10);
        } else {
            this.tickrate = tickrate;
            this.targetTickTime = 1000000000 / this.tickrate;
        }
    }

    public void updateWorld() {
        world.updatePlayerResources();
        //TODO finish the update method
    }

    @Override
    public void run() {
        super.run();

        long totalTicks = 0;
        long totalTime = 0;
        long loopStartTime = System.nanoTime();

        while (!end) {
            long tickStartTime = System.nanoTime();

            //paused check
            synchronized (this) {
                while(paused) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            //start of game loop ---------------------------------------------------------------------------------------

            world.setTick(world.getTick() + 1);
            totalTicks += 1;

            //monthly updates
            if (world.getTick() % 300 == 0) {
                world.updatePlayerResources();
            }

            //daily updates
            if (world.getTick() % 10 == 0) {
                System.out.println("Day: " + world.getTick() / 10 + " Tick: " + world.getTick());
            }

            //TODO complete game loop

            //end of game loop -----------------------------------------------------------------------------------------

            long tickTime = System.nanoTime() - tickStartTime;
            totalTime += tickTime;
            if (targetTickTime - tickTime > 0) {
                try {
                    Thread.sleep((targetTickTime - tickTime) / 1000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        long avgTickrate = 1000000000 * totalTicks / (System.nanoTime() - loopStartTime);
        System.out.println("avg tickrate: " + avgTickrate); //this calculation isn't precise, because it does not take into consideration the time when the thread was paused
        System.out.println("avg tick time: " + totalTime / totalTicks + " ns");
    }

    public synchronized void pauseGame() {
        this.paused = true;
    }

    public synchronized void unpauseGame() {
        this.notify();
        this.paused = false;
    }
}
