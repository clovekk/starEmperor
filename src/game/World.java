package game;

import graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;

public class World {
    private Graph<StarSystem> starSystems;
    private HashMap<String, Player> players;

    public World(Graph<StarSystem> starSystems, HashMap<String, Player> players) {
        this.starSystems = starSystems;
        this.players = players;
    }

    public World() {
        this.starSystems = new Graph<>();
        this.players = new HashMap<>();
    }

    public Graph<StarSystem> getStarSystems() {
        return starSystems;
    }
    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public void update() {
        starSystems.getAll().stream().forEach(s -> {
            if (s.getOwnerID() != null) {
                players.get(s.getOwnerID()).addResources(s.getResources());
            }
        });
        //TODO finish the update method
    }
}
