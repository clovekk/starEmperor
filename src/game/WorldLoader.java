package game;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorldLoader {
    public WorldLoader() {

    }

    public World loadWorld() {
        Path path = Paths.get(System.getenv("APPDATA") + "/StarEmperor/saves/gamedata.json");
        GameData gameData = GameData.loadGameData(path);

        return new World(gameData);
    }

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
}
