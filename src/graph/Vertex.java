package graph;

import java.util.ArrayList;

/**
 * This class serves as the vertex for the main Graph data structure
 * @param <E> The data contained in the vertices
 */
public class Vertex<E> {
    private String label;
    private ArrayList<String> edges;
    private int distance;
    private boolean closed;
    private String toParent;
    private String toChild;
    private E data;

    public Vertex(String label, ArrayList<String> edges, int distance, boolean closed, String toParent, String toChild) {
        this.label = label;
        this.edges = edges;
        this.distance = distance;
        this.closed = closed;
        this.toParent = toParent;
        this.toChild = toChild;
    }

    public Vertex(int distance, boolean closed) {
        this.label = "unspecified";
        this.edges = new ArrayList<>();
        this.distance = distance;
        this.closed = closed;
        this.toParent = null;
        this.toChild = null;
    }

    public Vertex(String label) {
        this.label = label;
        this.edges = new ArrayList<>();
        this.distance = 0;
        this.closed = false;
        this.toParent = null;
        this.toChild = null;
    }

    public Vertex(int distance) {
        this.label = "unspecified";
        this.edges = new ArrayList<>();
        this.distance = distance;
        this.closed = false;
        this.toParent = null;
        this.toChild = null;
    }

    public Vertex() {
        this.label = "unspecified";
        this.edges = new ArrayList<>();
        this.distance = 0;
        this.closed = false;
        this.toParent = null;
        this.toChild = null;
    }

    public String getLabel() {
        return label;
    }
    public ArrayList<String> getEdges() {
        return this.edges;
    }
    public int getDistance() {
        return distance;
    }
    public boolean isClosed() {
        return closed;
    }
    public String getToParent() {
        return toParent;
    }
    public String getToChild() {
        return toChild;
    }
    public E getData() {
        return data;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public void setEdges(ArrayList<String> edges) {
        this.edges = edges;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    public void setToParent(String toParent) {
        this.toParent = toParent;
    }
    public void setToChild(String toChild) {
        this.toChild = toChild;
    }
    public void setData(E data) {
        this.data = data;
    }

    public void addEdge(String edge) {
        this.edges.add(edge);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "label='" + label + '\'' +
                ", edges=" + edges +
                ", distance=" + distance +
                ", closed=" + closed +
                ", toParent='" + toParent + '\'' +
                ", toChild='" + toChild + '\'' +
                ", data=" + data +
                '}';
    }
}
