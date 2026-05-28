package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

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

    public String getId() {
        return id;
    }
    public PlayerColor getColor() {
        return color;
    }
    public ArrayList<Resource> getResources() {
        return resources;
    }

    public int getResourceAmount(String resourceID) {
        for (Resource r : resources) {
            if (r.getId().equals(resourceID)) {
                return r.getAmount();
            }
        }
        return 0;
    }

    public String getResourceList() {
        StringBuilder resourceList = new StringBuilder();
        for (Resource r : resources) {
            resourceList.append(" ").append(r.getName()).append(": ").append(r.getAmount());
        }
        return resourceList.toString();
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setColor(PlayerColor color) {
        this.color = color;
    }
    public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }

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

    public void addResource(Resource newResource) {
        ArrayList<Resource> newResources = new ArrayList<>();
        newResources.add(newResource);
        this.addResources(newResources);
    }

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
