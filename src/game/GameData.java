package game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import graph.Graph;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class GameData {
    private String gameVersion;
    private Graph<StarSystem> starSystems;
    private HashMap<String, Player> players;
    private int tick;

    public GameData() {
        this.gameVersion = "1.0.0";
        this.starSystems = new Graph<>();
        this.players = new HashMap<>();
        this.tick = 0;
    }

    public GameData(World world) {
        this.gameVersion = world.getGameVersion();
        this.starSystems = world.getStarSystems();
        this.players = world.getPlayers();
        this.tick = world.getTick();
    }

    public String getGameVersion() {
        return gameVersion;
    }
    public Graph<StarSystem> getStarSystems() {
        return starSystems;
    }
    public HashMap<String, Player> getPlayers() {
        return players;
    }
    public int getTick() {
        return tick;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }
    public void setStarSystems(Graph<StarSystem> starSystems) {
        this.starSystems = starSystems;
    }
    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
    }
    public void setTick(int tick) {
        this.tick = tick;
    }

    public static GameData loadGameData(Path filepath) {
        Gson gson = new Gson();

        try(Reader r = Files.newBufferedReader(filepath, StandardCharsets.UTF_8))  {
            return gson.fromJson(r, GameData.class);
        } catch (Exception e) {
            throw new RuntimeException("Error with json loading: " + e.getMessage());
        }
    }

    public void saveGameData(Path filepath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(Writer w = Files.newBufferedWriter(filepath, StandardCharsets.UTF_8)) {
            gson.toJson(this, w);
        } catch (Exception e) {
            throw new RuntimeException("Error with json saving: " + e.getMessage());
        }
    }
}
