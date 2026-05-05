package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Player {
    private String id;
    private Color color;
    private ArrayList<Resource> resources;

    public Player(String id, Color color, ArrayList<Resource> resources) {
        this.id = id;
        this.color = color;
        this.resources = resources;
    }

    public Player(String id) {
        this.id = id;
        this.color = new Color(0, 0, 0);
        this.resources = new ArrayList<>();
    }

    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void addResources(Collection<Resource> newResources) {
        newResources.stream().forEach(resource -> {
            for (Resource r : resources) {
                if (r.getResourceID().equals(resource.getResourceID())) {
                    r.addResources(resource);
                }
            }
            if (resources.stream().noneMatch(resource1 -> resource1.getResourceID().equals(resource.getResourceID()))) {
                resources.add(resource);
            }
        });
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", color=" + color +
                ", resources=" + resources +
                '}';
    }
}
