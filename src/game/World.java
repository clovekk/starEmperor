package game;

import graph.Graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * This class contains the main in-game world
 */
public class World {
    private String gameVersion;
    private Graph<StarSystem> starSystems;
    private HashMap<String, Player> players;
    private int tick;

    public World(String gameVersion, Graph<StarSystem> starSystems, HashMap<String, Player> players, int tick) {
        this.gameVersion = gameVersion;
        this.starSystems = starSystems;
        this.players = players;
        this.tick = tick;
    }

    public World() {
        this.gameVersion = "1.0.0";
        this.starSystems = new Graph<>();
        this.players = new HashMap<>();
        this.tick = 0;
    }

    public World(GameData gameData) {
        this.gameVersion = gameData.getGameVersion();
        this.starSystems = gameData.getStarSystems();
        this.players = gameData.getPlayers();
        this.tick = gameData.getTick();
    }

    //getters
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

    /**
     * Returns a collection of all the fleets owned by the player
     * @param playerID ID of the specified player
     * @return a collection of all the fleets owned by the specified player
     */
    public Collection<Fleet> getPlayerFleets(String playerID) {
        ArrayList<Fleet> fleets = new ArrayList<>();
        starSystems.getAll().stream().forEach(s -> s.getFleets().stream().forEach(f -> {
            if (f.getOwnerID().equals(playerID)) {
                fleets.add(f);
            }
        }));
        return fleets;
    }

    //setter
    public void setTick(int tick) {
        this.tick = tick;
    }

    /**
     * Updates players resources according to resources produced from his systems
     */
    public void updatePlayerResources() {
        starSystems.getAll().stream().forEach(s -> {
            if (s.getOwnerID() != null) {
                players.get(s.getOwnerID()).addResources(s.getResources());
            }
        });
    }

    /**
     * Updates player resources according to the fleet upkeep
     */
    public void updatePlayerFleetUpkeep() {
        this.players.values().stream().forEach(p -> this.getPlayerFleets(p.getId()).stream().forEach(f -> p.subtractResources(f.getUpkeep())));
    }

    /**
     * Returns the StarSystem containing this fleet
     * @param fleet The specified fleet
     * @return the StarSystem containing this fleet
     */
    public StarSystem getSystemContainingFleet(Fleet fleet) {
        for (StarSystem starSystem : this.getStarSystems().getAll()) {
            if (starSystem.getFleets().contains(fleet)) {
                return starSystem;
            }
        }
        return null;
    }

    /**
     * moves the specified fleet to the specified system
     * @param fleet The specified fleet
     * @param starSystem The specified system
     */
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

    /**
     * Updates all the fleet movement orders and their locations according to ticks elapsed since the fleet order has been assigned
     */
    public void updateFleetMovement() {
        for (StarSystem starSystem : this.getStarSystems().getAll()) {
            if (!starSystem.getFleets().isEmpty()) {
                ArrayList<Fleet> fleets = new ArrayList<>(starSystem.getFleets());
                for (Fleet fleet : fleets) {
                    if (fleet.getOrder() != null) {
                        //checks if fleet order is movement order
                        if (fleet.getOrder().getClass().equals(FleetMoveOrder.class)) {
                            FleetMoveOrder fleetMoveOrder = (FleetMoveOrder) fleet.getOrder();
                            fleetMoveOrder.setTicksLeftToNext(fleetMoveOrder.getTicksLeftToNext() - 1);

                            //checks if the fleet should be in the next system
                            if (fleetMoveOrder.getTicksLeftToNext() <= 0) {

                                this.getSystemContainingFleet(fleet).getFleets().remove(fleet);
                                this.getStarSystems().get(fleetMoveOrder.getPathSystemsID().getFirst()).addFleet(fleet);

                                fleetMoveOrder.getPathSystemsID().removeFirst();

                                //checks if the move order has any more systems in it or if this is the target system
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

    /**
     * Returns the formatted string with the current tick converted to normal date in the format DD.MM.YYYY where: day = 10 ticks, month = 30 days, year = 12 months = 360 days
     * @return the formatted string with the current tick converted to normal date in the format DD.MM.YYYY
     */
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

    /**
     * Returns an ArrayList of all the systems owned by the specified player
     * @param player The specified player
     * @return an ArrayList of all the systems owned by the specified player
     */
    public ArrayList<StarSystem> getSystemsOwnedBy(Player player) {
        ArrayList<StarSystem> playerOwnedStarSystems = new ArrayList<>();

        this.starSystems.getAll().stream().forEach(starSystem -> {
            if (player.getId().equals(starSystem.getOwnerID())) {
                playerOwnedStarSystems.add(starSystem);
            }
        });

        return playerOwnedStarSystems;
    }

    /**
     * Returns an ArrayList of all the fleets owned by the specified player
     * @param player The specified player
     * @return an ArrayList of all the fleets owned by the specified player
     */
    public ArrayList<Fleet> getFleetsOwnedBy(Player player) {
        ArrayList<Fleet> playerOwnedFleets = new ArrayList<>();

        this.starSystems.getAll().stream().forEach(starSystem -> {
            starSystem.getFleets().stream().forEach(fleet -> {
                if (player.getId().equals(fleet.getOwnerID())) {
                    playerOwnedFleets.add(fleet);
                }
            });
        });

        return playerOwnedFleets;
    }
}
