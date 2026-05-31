package game;

import java.util.ArrayList;

/**
 * This class represents the order of a fleet to move a certain path
 */
public class FleetMoveOrder extends FleetOrder {
    private int ticksLeftToNext;
    private ArrayList<String> pathSystemsID;

    public FleetMoveOrder(String id, int ticksLeftToNext, ArrayList<String> pathSystemsID) {
        super(id);
        this.ticksLeftToNext = ticksLeftToNext;
        this.pathSystemsID = pathSystemsID;

    }

    public int getTicksLeftToNext() {
        return ticksLeftToNext;
    }
    public ArrayList<String> getPathSystemsID() {
        return pathSystemsID;
    }

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
