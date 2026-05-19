package game;

public class FleetMoveOrder extends FleetOrder {
    protected String targetSystemID;

    public FleetMoveOrder(String id, String targetID) {
        super(id);
        this.targetSystemID = targetID;
    }

    public String getTargetSystemID() {
        return targetSystemID;
    }
    public void setTargetSystemID(String targetSystemID) {
        this.targetSystemID = targetSystemID;
    }

    @Override
    public String toString() {
        return "FleetMoveOrder{" +
                "targetSystemID='" + targetSystemID + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
