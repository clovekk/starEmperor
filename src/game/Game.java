package game;

public class Game {
    private World world;
    private GraphicsUserInterface gui;
    private boolean end;

    public Game(World world, GraphicsUserInterface gui, boolean end) {
        this.world = world;
        this.gui = gui;
        this.end = end;
    }

    public Game() {
        this.world = new World();
        this.gui = new GraphicsUserInterface();
        this.end = false;
    }

    public void updateWorld() {
        world.update();
        //TODO finish the update method
    }
}
