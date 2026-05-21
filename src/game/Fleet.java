package game;

import java.util.ArrayList;

public class Fleet {
    private String name;
    private String id;
    private String ownerID;
    private ArrayList<Resource> upkeep;
    private int strength;
    private FleetOrder order;

    public Fleet(String name, String id, String ownerID, ArrayList<Resource> upkeep, int strength, FleetOrder order) {
        this.name = name;
        this.id = id;
        this.ownerID = ownerID;
        this.upkeep = upkeep;
        this.strength = strength;
        this.order = order;
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
    public void setOrder(FleetOrder order) {
        this.order = order;
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
