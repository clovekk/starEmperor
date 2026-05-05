package game;

import java.security.InvalidParameterException;

public class Resource implements Comparable<Resource> {
    private String resourceID;
    private int amount;

    public Resource(String resourceID, int amount) {
        this.resourceID = resourceID;
        this.amount = amount;
    }

    public String getResourceID() {
        return resourceID;
    }
    public int getAmount() {
        return amount;
    }

    public void setResourceID(String resourceID) {
        this.resourceID = resourceID;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addResources(Resource resource) {
        if (this.resourceID.equals(resource.resourceID)) {
            this.amount += resource.amount;
        } else {
            throw new InvalidParameterException("You can not add resources that do not match each others type");
        }

    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceID='" + resourceID + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int compareTo(Resource resource) {
        return this.amount - resource.amount;
    }
}
