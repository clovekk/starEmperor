package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class represents the player in-game
 */
public class Player {
    private String id;
    private PlayerColor color;
    private ArrayList<Resource> resources;

    public Player(String id, PlayerColor color, ArrayList<Resource> resources) {
        this.id = id;
        this.color = color;
        this.resources = resources;
    }

    public Player(String id) {
        this.id = id;
        this.color = new PlayerColor(0, 0, 0);
        this.resources = new ArrayList<>();
    }

    //getters
    public String getId() {
        return id;
    }
    public PlayerColor getColor() {
        return color;
    }
    public ArrayList<Resource> getResources() {
        return resources;
    }

    /**
     * Returns the amount of this resource the player possesses
     * @param resourceID The specified resource
     * @return the amount of this resource the player possesses
     */
    public int getResourceAmount(String resourceID) {
        for (Resource r : resources) {
            if (r.getId().equals(resourceID)) {
                return r.getAmount();
            }
        }
        return 0;
    }

    /**
     * returns a String of all the resources the player has with their amounts
     * @return a String of all the resources the player has with their amounts
     */
    public String getResourceList() {
        StringBuilder resourceList = new StringBuilder();
        for (Resource r : resources) {
            resourceList.append(" ").append(r.getName()).append(": ").append(r.getAmount());
        }
        return resourceList.toString();
    }

    //setters
    public void setId(String id) {
        this.id = id;
    }
    public void setColor(PlayerColor color) {
        this.color = color;
    }
    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

    /**
     * This method adds the collection of the resources to the player, if any of the resources already exist within players stockpile they are merged
     * @param newResources the collection fo resources to be added
     */
    public void addResources(Collection<Resource> newResources) {
        newResources.stream().forEach(resource -> {
            for (Resource r : resources) {
                if (r.getId().equals(resource.getId())) {
                    r.addResources(resource);
                }
            }
            if (resources.stream().noneMatch(resource1 -> resource1.getId().equals(resource.getId()))) {
                resources.add(new Resource(resource));
            }
        });
    }

    /**
     * This method adds this resource to players stockpile, merging it with already existing one if it mathces the id
     * @param newResource the resource to be added
     */
    public void addResource(Resource newResource) {
        ArrayList<Resource> newResources = new ArrayList<>();
        newResources.add(newResource);
        this.addResources(newResources);
    }

    /**
     * This method removes subtracts the resources in the collection from players stockpile
     * @param subtractedResources resoruces to be subtracted
     */
    public void subtractResources(Collection<Resource> subtractedResources) {
        ArrayList<Resource> negativeResources = new ArrayList<>();
        subtractedResources.stream().forEach(r -> negativeResources.add(new Resource(r.getName(), -r.getAmount())));
        this.addResources(negativeResources);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                //", color=" + color +
                ", resources=" + resources +
                '}';
    }
}
