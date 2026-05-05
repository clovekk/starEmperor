package game;

public class ResourceIncome {
    private String resourceID;
    private int amount;

    public ResourceIncome(String resourceID, int amount) {
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

    @Override
    public String toString() {
        return "ResourceIncome{" +
                "resourceID='" + resourceID + '\'' +
                ", amount=" + amount +
                '}';
    }
}
