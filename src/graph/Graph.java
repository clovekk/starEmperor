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
            throw new KeyAlreadyExistsException("Key" + v.getLabel() + " is already taken");
        } else {
            this.vertices.put(v.getLabel(), v);
        }
    }

    public void addVertex(String v) {
        addVertex(new Vertex<>(v));
    }

    public void connectVertices(Vertex<E> v1, Vertex<E> v2, String edgeLabel, int edgeWeight) {
        if (this.vertices.containsValue(v1) && this.vertices.containsValue(v2)) {
            if (!v1.isConnectedTo(v2)) {
                v1.addEdge(new Edge<>(edgeLabel, v2, edgeWeight));
                v2.addEdge(new Edge<>(edgeLabel, v1, edgeWeight));
            } else {
                throw new InvalidParameterException("Vertex " + v1.getLabel() + " and vertex " + v2.getLabel() + " are already connected by an edge");
            }
        } else {
            if (!this.vertices.containsValue(v1)) {
                throw new NoSuchElementException("Vertex " + v1.getLabel() + " is not a part of the graph");
            }
            if (!this.vertices.containsValue(v2)) {
                throw new NoSuchElementException("Vertex " + v2.getLabel() + " is not a part of the graph");
            }
        }
    }

    public void connectVertices(String v1, String v2, String edgeLabel, int edgeWeight) {
        if (this.vertices.containsKey(v1) && this.vertices.containsKey(v2)) {
            this.connectVertices(this.vertices.get(v1), this.vertices.get(v2), edgeLabel, edgeWeight);
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
            current.getToParent().getEnd().setToChild(current.getToParent().getEnd().getEdges().get(current.getToParent().getLabel()));
            shortestPath.add(current.getToParent().getEnd().getToChild());
            current = current.getToParent().getEnd();
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }

    public void setAllDistances(Vertex<E> start) {
        ArrayList<Edge<E>> currentEdges = new ArrayList<>(start.getEdges().values());
        Collections.sort(currentEdges);

        if (start.isClosed()) {
            return;
        }

        //neighbors set distance based on the distance from original start
        for (Edge<E> e : currentEdges) {
            if (!e.getEnd().isClosed()) {
                if (e.getEnd().getDistance() > start.getDistance() + e.getWeight()) {
                    e.getEnd().setDistance(start.getDistance() + e.getWeight());
                    e.getEnd().setToParent(e.getEnd().getEdges().get(e.getLabel()));
                }
            }
        }

        Vertex<E> closestNeighbor = currentEdges.getFirst().getEnd();
        start.setClosed(true);
        currentEdges.removeFirst();
        setAllDistances(closestNeighbor);
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
