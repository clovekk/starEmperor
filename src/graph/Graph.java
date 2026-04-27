package graph;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Graph<E> {
    private String name;
    private HashMap<String, Vertex<E>> vertices;

    public Graph(String name, HashMap<String, Vertex<E>> vert) {
        this.name = name;
        this.vertices = vert;
    }

    public Graph(String name) {
        this.name = name;
        this.vertices = new HashMap<>();
    }

    public Graph() {
        this.name = "unspecified";
        this.vertices = new HashMap<>();
    }

    public String getName() {
        return name;
    }
    public HashMap<String, Vertex<E>> getVertices() {
        return vertices;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setVertices(HashMap<String, Vertex<E>> vertices) {
        this.vertices = vertices;
    }

    public void addVertex(Vertex<E> v) {
        if (this.vertices.containsKey(v.getLabel())) {
            throw new KeyAlreadyExistsException("Key" + v.getLabel() + " is already used by a different vertex in this graph");
        } else {
            this.vertices.put(v.getLabel(), v);
        }
    }

    public void addVertex(String v) {
        addVertex(new Vertex<>(v));
    }

    public void connectVertices(String edgeLabel, Vertex<E> tail, Vertex<E> head, boolean directed, int edgeWeight) {
        if (this.vertices.containsValue(tail) && this.vertices.containsValue(head)) {
            if (!tail.isConnectedTo(head)) {
                Edge<E> edge = new Edge<>(edgeLabel, tail, head, directed, edgeWeight);
                tail.addEdge(edge);
                head.addEdge(edge);
            } else {
                throw new InvalidParameterException("Vertex " + tail.getLabel() + " and vertex " + head.getLabel() + " are already connected by an edge"); //TODO potentially change for a custom exception since idk if this is the correct usage of this exception type
            }
        } else {
            if (!this.vertices.containsValue(tail)) {
                throw new NoSuchElementException("Vertex " + tail.getLabel() + " is not a part of the graph");
            }
            if (!this.vertices.containsValue(head)) {
                throw new NoSuchElementException("Vertex " + head.getLabel() + " is not a part of the graph");
            }
        }
    }

    public void connectVertices(String edgeLabel, String v1, String v2, boolean directed, int edgeWeight) {
        if (this.vertices.containsKey(v1) && this.vertices.containsKey(v2)) {
            this.connectVertices(edgeLabel, this.vertices.get(v1), this.vertices.get(v2), directed, edgeWeight);
        } else {
            if (!this.vertices.containsKey(v1)) {
                throw new NoSuchElementException("Vertex " + v1 + " is not a part of the graph");
            }
            if (!this.vertices.containsKey(v2)) {
                throw new NoSuchElementException("Vertex " + v2 + " is not a part of the graph");
            }
        }
    }

    public ArrayList<Edge<E>> findShortestPath(String start, String end) {
        return findShortestPath(this.vertices.get(start), this.vertices.get(end));
    }

    public ArrayList<Edge<E>> findShortestPath(Vertex<E> start, Vertex<E> end) {
        ArrayList<Edge<E>> shortestPath = new ArrayList<>();

        //all vertices except start set distance to max
        for (Vertex<E> v : this.vertices.values()) {
            v.setDistance(Integer.MAX_VALUE);
        }
        start.setDistance(0);

        //probable start of a second method that might be created from this part
        setAllDistances(start);

        Vertex<E> current = end;
        while (current.getToParent() != null) {
            current.getToParent().getOtherEndpoint(current).setToChild(current.getToParent());
            shortestPath.add(current.getToParent());
            current = current.getToParent().getOtherEndpoint(current);
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }

    public void setAllDistances(Vertex<E> start) {
        if (start.isClosed()) {
            return;
        }

        ArrayList<Edge<E>> currentEdges = new ArrayList<>(start.getEdges().values());
        Collections.sort(currentEdges);

        //neighbors set distance based on the distance from original start
        for (Edge<E> e : currentEdges) {
            if (!e.getOtherEndpoint(start).isClosed()) {
                if (e.getOtherEndpoint(start).getDistance() > start.getDistance() + e.getWeight()) {
                    e.getOtherEndpoint(start).setDistance(start.getDistance() + e.getWeight());
                    e.getOtherEndpoint(start).setToParent(e);
                }
            }
        }

        start.setClosed(true);

        for (Edge<E> e : currentEdges) {
            Vertex<E> closestNeighbor = e.getOtherEndpoint(start);
            setAllDistances(closestNeighbor);
        }
    }

    public E get(String key) {
        return this.vertices.get(key).getData();
    }

    public void set(String key, E data) {
        this.vertices.get(key).setData(data);
    }

    public int size() {
        return vertices.size();
    }

    public void add(String key, E data) {
        this.addVertex(key);
        this.vertices.get(key).setData(data);
    }

    public int getDistance(String key) {
        return this.vertices.get(key).getDistance();
    }

    @Override
    public String toString() {
        StringBuilder vertexList = new StringBuilder();
        for (Vertex<E> v : this.vertices.values()) {
            vertexList.append(" ").append(v).append("\n");
        }
        return "Graph{" +
                " name='" + name + '\'' +
                ", vertices: \n" + vertexList +
                '}';
    }
}
