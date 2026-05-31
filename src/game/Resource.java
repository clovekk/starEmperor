package game;

import java.security.InvalidParameterException;
import java.util.Collection;

/**
 * This class represents the resources in-game
 */
public class Resource implements Comparable<Resource> {
    private String name;
    private String id;
    private int amount;

    public Resource(String name, String id, int amount) {
        this.name = name;
        this.id = id;
        this.amount = amount;
    }

    public Resource(String name, int amount) {
        this.name = name;
        this.id = name.toLowerCase();
        this.amount = amount;
    }

    public Resource(String name) {
        this.name = name;
        this.id = name.toLowerCase();
        this.amount = 0;
    }

    public Resource(Resource resource) {
        this.name = resource.name;
        this.id = resource.id;
        this.amount = resource.amount;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public int getAmount() {
        return amount;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Adds the specified resource together with this resource, they have to have matching IDs
     * @param resource the specified resource
     */
    public void addResources(Resource resource) {
        if (this.id.equals(resource.id)) {
            this.amount += resource.amount;
        } else {
            throw new InvalidParameterException("You can not add resources that do not match each others type");
        }
    }

    /**
     * Returns a good-looking string of this resource and amount
     * @return a good-looking string of this resource and amount
     */
    public String toPrettyString() {
        return this.getName() + ": " + this.getAmount();
    }

    /**
     * Returns a good-looking string of all resources in the collection.
     * @param resources The specified collection of resources
     * @return a good-looking string of all resources in the collection.
     */
    public static String toPrettyString(Collection<Resource> resources) {
        StringBuilder resourceList = new StringBuilder();
        for (Resource resource : resources) {
            resourceList.append(resource.getName()).append(": ").append(resource.getAmount()).append(" ");
        }
        return resourceList.toString();
    }

    /**
     * Returns a good-looking string of all resources in the collection with every resource on new line using HTML, intended usage is for JLabels
     * @param resources The specified collection of resources
     * @return a good-looking string of all resources in the collection with every resource on new line using HTML
     */
    public static String toPrettyHTMLString(Collection<Resource> resources) {
        StringBuilder resourceList = new StringBuilder("<html>");
        for (Resource resource : resources) {
            resourceList.append(resource.getName()).append(": ").append(resource.getAmount()).append("<br/>");
        }
        resourceList.append("<html>");
        return resourceList.toString();
    }

    @Override
    public String toString() {
        return "Resource{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int compareTo(Resource resource) {
        return this.amount - resource.amount;
    }
}
