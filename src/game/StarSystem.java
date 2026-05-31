package game;

import java.util.ArrayList;

/**
 * This class represents the star systems in the game
 */
public class StarSystem {
    private String name;
    private String id;
    private ArrayList<Planet> planets;
    private String ownerID;
    private ArrayList<Resource> resources;
    private ArrayList<Fleet> fleets;
    private int x;
    private int y;

    public StarSystem(String name, String id, ArrayList<Planet> planets, String ownerID, ArrayList<Resource> resources, ArrayList<Fleet> fleets, int x, int y) {
        this.name = name;
        this.id = id;
        this.planets = planets;
        this.ownerID = ownerID; //null = no owner
        this.resources = resources;
        this.fleets = fleets;
        this.x = x;
        this.y = y;
    }

    public StarSystem(String name, String id) {
        this.name = name;
        this.id = id;
        this.planets = new ArrayList<>();
        this.ownerID = null;
        this.resources = new ArrayList<>();
        this.fleets = new ArrayList<>();
        this.x = 0;
        this.y = 0;
    }

    public StarSystem(String name) {
        this.name = name;
        this.id = this.name.toLowerCase();
        this.planets = new ArrayList<>();
        this.ownerID = null;
        this.resources = new ArrayList<>();
        this.fleets = new ArrayList<>();
        this.x = 0;
        this.y = 0;
    }

    public StarSystem(String name, int x, int y) {
        this.name = name;
        this.id = this.name.toLowerCase();
        this.planets = new ArrayList<>();
        this.ownerID = null;
        this.resources = new ArrayList<>();
        this.fleets = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public StarSystem() {
        this.name = "undef";
        this.id = this.name.toLowerCase();
        this.planets = new ArrayList<>();
        this.ownerID = null;
        this.resources = new ArrayList<>();
        this.fleets = new ArrayList<>();
        this.x = 0;
        this.y = 0;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public ArrayList<Planet> getPlanets() {
        return planets;
    }
    public String getOwnerID() {
        return ownerID;
    }
    public ArrayList<Resource> getResources() {
        return resources;
    }
    public ArrayList<Fleet> getFleets() {
        return fleets;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    /**
     * Returns the amount of this resource in the system
     * @param resource The specified resource
     * @return the amount of this resource in the system
     */
    public int getResourceAmount(Resource resource) {
        return getResourceAmount(resource.getId());
    }

    /**
     * Returns the amount of this resource in the system
     * @param resourceID The label of the specified resource
     * @return the amount of this resource in the system
     */
    public int getResourceAmount(String resourceID) {
        for (Resource r : this.resources) {
            if (resourceID.equals(r.getId())) {
                return r.getAmount();
            }
        }
        return 0;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setPlanets(ArrayList<Planet> planets) {
        this.planets = planets;
    }
    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }
    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }
    public void setFleets(ArrayList<Fleet> fleets) {
        this.fleets = fleets;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Adds a new resource with these parameters to the system.
     * @param resourceName The name of the resource
     * @param amount Amount of the resource
     */
    public void addResource(String resourceName, int amount) {
        this.resources.add(new Resource(resourceName, amount));
    }

    /**
     * Adds this fleet to the system.
     * @param fleet The specified fleet to the system
     */
    public void addFleet(Fleet fleet) {
        this.getFleets().add(fleet);
    }

    @Override
    public String toString() {
        return "StarSystem{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", planets=" + planets +
                ", ownerID='" + ownerID + '\'' +
                ", resources=" + resources +
                ", fleetIDs=" + fleets +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
