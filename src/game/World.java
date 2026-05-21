package game;

import graph.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class World {
    private Graph<StarSystem> starSystems;
    private HashMap<String, Player> players;
    private int tick;

    public World(Graph<StarSystem> starSystems, HashMap<String, Player> players, int tick) {
        this.starSystems = starSystems;
        this.players = players;
        this.tick = tick;
    }

    public World() {
        this.starSystems = new Graph<>();
        this.players = new HashMap<>();
        this.tick = 0;
    }

    public World(GameData gameData) {
        this.starSystems = gameData.getStarSystems();
        this.players = gameData.getPlayers();
        this.tick = gameData.getTick();
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

    public Collection<Fleet> getPlayerFleets(String playerID) {
        ArrayList<Fleet> fleets = new ArrayList<>();
        starSystems.getAll().stream().forEach(s -> s.getFleets().stream().forEach(f -> {
            if (f.getOwnerID().equals(playerID)) {
                fleets.add(f);
            }
        }));
        return fleets;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public void updatePlayerResources() {
        starSystems.getAll().stream().forEach(s -> {
            if (s.getOwnerID() != null) {
                players.get(s.getOwnerID()).addResources(s.getResources());
            }
        });
    }

    public void updatePlayerFleetUpkeep() {
        this.players.values().stream().forEach(p -> this.getPlayerFleets(p.getId()).stream().forEach(f -> p.subtractResources(f.getUpkeep())));
    }
    //TODO finish other update methods
}
