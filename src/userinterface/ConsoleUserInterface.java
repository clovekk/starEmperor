package userinterface;

import game.World;
import game.WorldManager;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsoleUserInterface extends Thread implements UserInterface {
    private static String DEFAULT = "\u001B[0m";
    private static String REVERSE = "\u001B[7m";
    private static String BLACK_FG = "\u001B[30m";
    private static String DARK_RED_FG = "\u001B[31m";
    private static String BRIGHT_YELLOW_BG = "\u001B[103m";

    private WorldManager worldManager;
    private AtomicBoolean end;

    @Override
    public void startDisplaying(WorldManager worldManager, AtomicBoolean end) {
        this.worldManager = worldManager;
        this.end = end;
        this.start();
    }

    @Override
    public void run() {
        super.run();
        System.out.println(BRIGHT_YELLOW_BG + BLACK_FG + "IN ORDER FOR THE CONSOLE INTERFACE TO UPDATE AFTER A FLAG CHANGE YOU HAVE TO EXECUTE ANY COMMAND" + DEFAULT);
        while (!end.get()) {
            Scanner scn = new Scanner(System.in);
            String input = scn.nextLine().toLowerCase();

            switch (input) {
                case "sinfo":
                    System.out.println("StarSystem info: ");
                    worldManager.getWorld().getStarSystems().getAll().stream().forEach(System.out::println);
                    break;

                case "pinfo":
                    System.out.println("Player info: ");
                    worldManager.getWorld().getPlayers().values().stream().forEach(System.out::println);
                    break;

                case "stop":
                    System.out.println("Stopping on tick " + worldManager.getWorld().getTick());
                    worldManager.setEnd(true);
                    this.end.set(true);
                    System.out.println("User interface endFlag updated to: " + this.end.toString());
                    break;

                case "pause":
                    System.out.println("Pausing game on tick: " + worldManager.getWorld().getTick());
                    worldManager.pauseGame();
                    break;

                case "unpause":
                    System.out.println("Unpausing the game on tick: " + worldManager.getWorld().getTick());
                    worldManager.unpauseGame();
                    break;

                default:
                    break;
            }
        }
    }
}
