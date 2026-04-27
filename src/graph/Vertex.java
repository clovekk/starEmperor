package graph;

import java.util.HashMap;

public class Vertex<E> {
    private String label;
    private HashMap<String, Edge<E>> edges;
    private int distance;
    private boolean closed;
    private Edge<E> toParent;
    private Edge<E> toChild;
    private E data;

    public Vertex(String label, HashMap<String, Edge<E>> edges, int distance, boolean closed, Edge<E> toParent, Edge<E> toChild) {
        this.label = label;
        this.edges = edges;
        this.distance = distance;
        this.closed = closed;
        this.toParent = toParent;
        this.toChild = toChild;
    }

    public Vertex(int distance, boolean closed) {
        this.label = "unspecified";
        this.edges = new HashMap<>();
        this.distance = distance;
        this.closed = closed;
        this.toParent = null;
        this.toChild = null;
    }

    public Vertex(String label) {
        this.label = label;
        this.edges = new HashMap<>();
        this.distance = 0;
        this.closed = false;
        this.toParent = null;
        this.toChild = null;
    }

    public Vertex(int distance) {
        this.label = "unspecified";
        this.edges = new HashMap<>();
        this.distance = distance;
        this.closed = false;
        this.toParent = null;
        this.toChild = null;
    }

    public Vertex() {
        this.label = "unspecified";
        this.edges = new HashMap<>();
        this.distance = 0;
        this.closed = false;
        this.toParent = null;
        this.toChild = null;
    }

    public String getLabel() {
        return label;
    }
    public HashMap<String,Edge<E>> getEdges() {
        return edges;
    }
    public int getDistance() {
        return distance;
    }
    public boolean isClosed() {
        return closed;
    }
    public Edge<E> getToParent() {
        return toParent;
    }
    public Edge<E> getToChild() {
        return toChild;
    }
    public E getData() {
        return data;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public void setEdges(HashMap<String, Edge<E>> edges) {
        this.edges = edges;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    public void setToParent(Edge<E> toParent) {
        this.toParent = toParent;
    }
    public void setToChild(Edge<E> toChild) {
        this.toChild = toChild;
    }
    public void setData(E data) {
        this.data = data;
    }

    public HashMap<String, Vertex<E>> getAdjacentVertices() {
        HashMap<String, Vertex<E>> adjacentVertices = new HashMap<>();
        for (Edge<E> e : this.edges.values()) {
            adjacentVertices.put(e.getOtherEndpoint(this).getLabel(), e.getOtherEndpoint(this));
        }
        return adjacentVertices;
    }

    public Vertex<E> getClosestNeighbor() {
        Vertex<E> closest = null;
        for (Vertex<E> v : this.getAdjacentVertices().values()) {
            if (closest == null || v.getDistance() < closest.getDistance()) {
                closest = v;
            }
        }
        return closest;
    }

    public void addEdge(Edge<E> e) {
        this.edges.put(e.getLabel(), e);
    }

    public boolean isConnectedTo(Vertex<E> v) {
        for (Edge<E> e : this.edges.values()) {
            if (e.getOtherEndpoint(this).equals(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder connections = new StringBuilder();
        for (Edge<E> e : this.edges.values()) {
            connections.append(e.getOtherEndpoint(this).getLabel()).append(" ");
        }

        String parentLabel;
        if (toParent == null) {
            parentLabel = "null";
        } else {
            parentLabel = this.toParent.getLabel() + "-" + this.toParent.getOtherEndpoint(this).getLabel();
        }

        String childLabel;
        if (toChild == null) {
            childLabel = "null";
        } else {
            childLabel = this.toChild.getLabel() + "-" + this.toChild.getOtherEndpoint(this).getLabel();
        }
        return "Vertex{ label='" + label + '\'' + ", edges=" + edges + ", distance=" + distance + ", closed=" + closed + ", toParent=" + parentLabel + ", toChild=" + childLabel + ", connections="+  connections + " }";
    }
}
