package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class Player {
    private String id;
    //private Color color;
    private ArrayList<Resource> resources;

    public Player(String id, Color color, ArrayList<Resource> resources) {
        this.id = id;
        //this.color = color;
        this.resources = resources;
    }

    public Player(String id) {
        this.id = id;
        //this.color = new Color(0, 0, 0);
        this.resources = new ArrayList<>();
    }

    public String getId() {
        return id;
    }
    public ArrayList<Resource> getResources() {
        return resources;
    }

    public void setId(String id) {
        this.id = id;
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
