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

    public StarSystem getSystemContainingFleet(Fleet fleet) {
        for (StarSystem starSystem : this.getStarSystems().getAll()) {
            if (starSystem.getFleets().contains(fleet)) {
                return starSystem;
            }
        }
        return null;
    }

    public void moveFleetToSystem(Fleet fleet, StarSystem starSystem) {
        if (this.getSystemContainingFleet(fleet).getId().equals(starSystem.getId())) {
            return;
        }
        ArrayList<StarSystem> pathSystems = this.getStarSystems().getShortestPath(this.getSystemContainingFleet(fleet).getId(), starSystem.getId());
        pathSystems.removeFirst();
        ArrayList<String> pathSystemsID = new ArrayList<>();
        for (StarSystem system : pathSystems) {
            pathSystemsID.add(system.getId());
        }

        fleet.setOrder(new FleetMoveOrder(fleet.getId(), 10 * this.getStarSystems().getDistance(this.getSystemContainingFleet(fleet).getId(), pathSystemsID.get(0)), pathSystemsID));
    }

    public void updateFleetMovement() {
        for (StarSystem starSystem : this.getStarSystems().getAll()) {
            if (!starSystem.getFleets().isEmpty()) {
                ArrayList<Fleet> fleets = new ArrayList<>(starSystem.getFleets());
                for (Fleet fleet : fleets) {
                    if (fleet.getOrder() != null) {
                        if (fleet.getOrder().getClass().equals(FleetMoveOrder.class)) {
                            FleetMoveOrder fleetMoveOrder = (FleetMoveOrder) fleet.getOrder();
                            fleetMoveOrder.setTicksLeftToNext(fleetMoveOrder.getTicksLeftToNext() - 1);

                            if (fleetMoveOrder.getTicksLeftToNext() <= 0) {

                                this.getSystemContainingFleet(fleet).getFleets().remove(fleet);
                                this.getStarSystems().get(fleetMoveOrder.getPathSystemsID().getFirst()).addFleet(fleet);

                                fleetMoveOrder.getPathSystemsID().removeFirst();
                                if (!fleetMoveOrder.getPathSystemsID().isEmpty()) {
                                    fleetMoveOrder.setTicksLeftToNext(10 * this.getStarSystems().getDistance(this.getSystemContainingFleet(fleet).getId(), fleetMoveOrder.getPathSystemsID().getFirst()));
                                } else {
                                    fleet.setOrder(null);
                                }

                            }
                        }
                    }

                }
            }
        }
    }

    public String getCurrentDate() {
        int year = tick / 3600;
        int month = (tick - year * 3600) / 300;
        int day = (tick - year * 3600 - month * 300) / 10;
        year += 2208;
        month += 1;
        day += 1;

        StringBuilder date = new StringBuilder();

        if (day < 10) {
            date.append("0");
        }
        date.append(day).append(". ");

        if (month < 10) {
            date.append("0");
        }
        date.append(month).append(". ");

        date.append(year);

        return date.toString();
    }
    //TODO finish other update methods
}
