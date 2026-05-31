package game;

import java.util.ArrayList;
import java.util.Arrays;

public class Fleet {
    private String name;
    private String id;
    private String ownerID;
    private ArrayList<Resource> upkeep;
    private int strength;
    private FleetMoveOrder order;

    public Fleet(String name, String id, String ownerID, ArrayList<Resource> upkeep, int strength, FleetMoveOrder order) {
        this.name = name;
        this.id = id;
        this.ownerID = ownerID;
        this.upkeep = upkeep;
        this.strength = strength;
        this.order = order;
    }

    public Fleet(String name, String ownerID) {
        ArrayList<Resource> upkeep = new ArrayList<>(Arrays.asList(new Resource("Metals", 2), new Resource("Energy", 3)));

        this.name = name;
        this.id = this.name.toLowerCase();
        this.ownerID = ownerID;
        this.upkeep = upkeep;
        this.strength = 5;
        this.order = null;
    }

    public Fleet() {
        this.name = "undef";
        this.id = this.name.toLowerCase();
        this.ownerID = null;
        this.upkeep = null;
        this.strength = 0;
        this.order = null;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public String getOwnerID() {
        return ownerID;
    }
    public ArrayList<Resource> getUpkeep() {
        return upkeep;
    }
    public int getStrength() {
        return strength;
    }
    public FleetOrder getOrder() {
        return order;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }
    public void setUpkeep(ArrayList<Resource> upkeep) {
        this.upkeep = upkeep;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setOrder(FleetMoveOrder order) {
        this.order = order;
    }

    public int getUpkeepAmount(String resourceID) {
        for (Resource resource : this.upkeep) {
            if (resourceID.equals(resource.getId())) {
                return resource.getAmount();
            }
        }
        return 0;
    }

    public int getUpkeepAmount(Resource resource) {
        return getUpkeepAmount(resource.getId());
    }

    @Override
    public String toString() {
        return "Fleet{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", ownerID='" + ownerID + '\'' +
                ", upkeep=" + upkeep +
                ", strength=" + strength +
                ", order=" + order +
                '}';
    }
}
