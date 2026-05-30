package game;

import java.util.ArrayList;

public class FleetMoveOrder extends FleetOrder {
    private int ticksLeftToNext;
    private ArrayList<String> pathSystemsID;

    public FleetMoveOrder(String id, int ticksLeftToNext, ArrayList<String> pathSystemsID) {
        super(id);
        this.ticksLeftToNext = ticksLeftToNext;
        this.pathSystemsID = pathSystemsID;

    }

    //public String getTargetSystemID() {
    //    return targetSystemID;
    //}
    public int getTicksLeftToNext() {
        return ticksLeftToNext;
    }
    public ArrayList<String> getPathSystemsID() {
        return pathSystemsID;
    }

    //public void setTargetSystemID(String targetSystemID) {
    //    this.targetSystemID = targetSystemID;
    //}
    public void setTicksLeftToNext(int ticksLeftToNext) {
        this.ticksLeftToNext = ticksLeftToNext;
    }
    public void setPathSystemsID(ArrayList<String> pathSystemsID) {
        this.pathSystemsID = pathSystemsID;
    }

    @Override
    public String toString() {
        return "FleetMoveOrder{" +
                /*"targetSystemID='" + targetSystemID + '\'' +*/
                ", ticksLeftToNext=" + ticksLeftToNext +
                ", pathSystemsID=" + pathSystemsID +
                ", id='" + id + '\'' +
                '}';
    }
}
