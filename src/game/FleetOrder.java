package game;

/**
 * This abstract class is used to extend all fleet oreders
 */
public abstract class FleetOrder {
    protected String id;

    public FleetOrder(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FleetOrder{" +
                "id='" + id + '\'' +
                '}';
    }
}
