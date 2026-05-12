package game;

import userinterface.UserInterface;

public class WorldManager extends Thread {
    private volatile World world;
    private boolean end;
    private int tickrate;

    public WorldManager(World world, UserInterface gui, boolean end, int tickrate) {
        this.world = world;
        this.end = end;
        this.tickrate = tickrate;
    }

    public WorldManager() {
        this.world = new World();
        this.end = false;
        this.tickrate = 10;
    }

    public void startGame() {
        //this.world = new World(); //TODO world loader
        this.end = false;
        this.tickrate = 10;

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
        this.tickrate = tickrate;
    }

    public void updateWorld() {
        world.updatePlayerResources();
        //TODO finish the update method
    }

    @Override
    public void run() {
        super.run();

        int totalTicks = 0;

        long loopStartTime = System.nanoTime();
        while (!end) {
            long startTime = System.nanoTime();

            //start of game loop

            world.setTick(world.getTick() + 1);
            totalTicks += 1;

            if (world.getTick() % 300 == 0) {
                world.updatePlayerResources();
            }
            if (world.getTick() % 10 == 0) {
                System.out.println("Day: " + world.getTick() / 10 + " Tick: " + world.getTick());
            }
            //TODO complete game loop

            //end of game loop

            if (totalTicks >= 400) {
                end = true;
            }

            long totalTime = System.nanoTime() - startTime;
            if (1000 / tickrate - totalTime / 1000000 > 0) {
                try {
                    Thread.sleep(1000 / tickrate - totalTime / 1000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        long avgTicks = totalTicks / ((System.nanoTime() - loopStartTime) / 1000000000);
        System.out.println("avg ticks: " + avgTicks);
    }
}
