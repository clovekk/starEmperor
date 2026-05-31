package game;

import graph.Graph;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

/**
 * tThis class is responsible for the loading/saving of worlds
 */
public class WorldLoader {
    public WorldLoader() {

    }

    /**
     * Loads the saved world from the appdata location
     * @return the saved world from the appdata location
     */
    public World loadWorld() {
        Path path = Paths.get(System.getenv("APPDATA") + "/StarEmperor/saves/gamedata.json");

        World world = null;
        try {
            GameData gameData = GameData.loadGameData(path);
            world = new World(gameData);
        } catch (RuntimeException e) {
            world = createNewWorld();
        }

        return world;
    }

    /**
     * Saves the inputted world into the appdata location
     * @param world the world to be saved
     */
    public void saveWorld(World world) {
        Path folderPath = Paths.get(System.getenv("APPDATA") + "/StarEmperor/saves");
        Path path = Paths.get(folderPath + "/gamedata.json");
        GameData gameData = new GameData(world);

        if (!Files.exists(folderPath)) {
            boolean dirCreated = new File(folderPath.toString()).mkdirs();
            System.out.println("New save directory created: " + dirCreated);
        }

        gameData.saveGameData(path);
    }

    /**
     * Loads the new world file from resources
     * @return the new world file from resources converted to World class
     */
    public World createNewWorld() {
        String path  = "/gamedata_newgame_clustered.json";
        GameData gameData = GameData.loadNewGameData(path);
        //world.getStarSystems().get("sol").addFleet(new Fleet("Alpha", "1"));

        /*
        //temporary test - start
        Graph<StarSystem> starSystems = new Graph<>("starSystems");
        HashMap<String, Player> players = new HashMap<>();
        players.put("1", new Player("1", new PlayerColor(20, 50, 110), new ArrayList<>(Arrays.asList(new Resource("Metals"), new Resource("Energy")))));

        starSystems.add("one", new StarSystem("One", 0, 0));
        starSystems.get("one").addResource("Metals", 7);
        starSystems.get("one").addResource("Energy", 4);
        starSystems.get("one").setOwnerID("1");
        starSystems.add("two", new StarSystem("Two", 50, 0));
        starSystems.add("three", new StarSystem("Three", 100, 50));
        starSystems.get("three").addResource("Metals", 9);
        starSystems.get("three").setOwnerID("1");
        starSystems.add("four", new StarSystem("Four", 50, 100));
        starSystems.get("four").addResource("Energy", 6);
        starSystems.get("four").setOwnerID("1");
        starSystems.add("five", new StarSystem("Five", 0, 50));

        starSystems.connectVertices("a", "one", "five", false, 20);
        starSystems.connectVertices("b", "one", "two", false, 10);
        starSystems.connectVertices("c", "two", "three", false, 8);
        starSystems.connectVertices("d", "three", "four", false, 10);
        starSystems.connectVertices("e", "two", "four", false, 15);
        starSystems.connectVertices("f", "one", "three", false, 20);

        Fleet testFleet = new Fleet("Alpha", "1");
        starSystems.get("five").addFleet(testFleet);

        return new World("1.0.0", starSystems, players, 0);*/
        //temporary test - end
        return new World(gameData);
    }
}
