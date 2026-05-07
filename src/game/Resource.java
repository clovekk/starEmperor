package game;

import java.security.InvalidParameterException;

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

    public void addResources(Resource resource) {
        if (this.id.equals(resource.id)) {
            this.amount += resource.amount;
        } else {
            throw new InvalidParameterException("You can not add resources that do not match each others type");
        }
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
