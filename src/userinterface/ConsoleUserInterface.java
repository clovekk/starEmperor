package userinterface;

import game.World;

import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsoleUserInterface extends Thread implements UserInterface {
    private World world;
    private AtomicBoolean end;

    @Override
    public void startDisplaying(World world, AtomicBoolean end) {
        this.world = world;
        this.end = end;
        this.start();
    }

    @Override
    public void run() {
        super.run();
        System.out.println("IN ORDER FOR THE CONSOLE INTERFACE TO UPDATE YOU HAVE TO EXECUTE ANY COMMAND");
        while (!end.get()) {
            Scanner scn = new Scanner(System.in);
            String input = scn.nextLine();

            if (input.equals("info")) {
                world.getPlayers().values().stream().forEach(p -> System.out.println(p.getResources()));
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }
}
