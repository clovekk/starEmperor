package game;

import java.util.ArrayList;

public class StarSystem {
    private String name;
    private String id;
    private ArrayList<Planet> planets;
    private String ownerID;
    private ArrayList<Resource> resources;

    public StarSystem(String name, String id, ArrayList<Planet> planets, String ownerID, ArrayList<Resource> resources) {
        this.name = name;
        this.id = id;
        this.planets = planets;
        this.ownerID = ownerID; //null = no owner
        this.resources = resources;
    }

    public StarSystem(String name, String id) {
        this.name = name;
        this.id = id;
        this.planets = new ArrayList<>();
        this.ownerID = null;
        this.resources = new ArrayList<>();
    }

    public StarSystem(String name) {
        this.name = name;
        this.id = name.toLowerCase();
        this.planets = new ArrayList<>();
        this.ownerID = null;
        this.resources = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public int getResourceAmount(String resourceID) {
        for (Resource r : this.resources) {
            if (r.getId().equals(resourceID)) {
                return r.getAmount();
            }
        }
        return 0;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }

    public void addResource(String resourceName, int amount) {
        this.resources.add(new Resource(resourceName, amount));
    }

    @Override
    public String toString() {
        return "StarSystem{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", planets=" + planets +
                ", ownerID='" + ownerID + '\'' +
                ", resources=" + resources +
                '}';
    }
}
