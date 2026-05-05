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
    private HashMap<String, Edge<E>> edges;

    //constructors
    public Graph(String name, HashMap<String, Vertex<E>> vertices, HashMap<String, Edge<E>> edges) {
        this.name = name;
        this.vertices = vertices;
        this.edges = edges;
    }

    public Graph(String name) {
        this.name = name;
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }

    public Graph() {
        this.name = "unspecified";
        this.vertices = new HashMap<>();
        this.edges = new HashMap<>();
    }

    //getters
    public String getName() {
        return name;
    }
    public HashMap<String, Vertex<E>> getVertices() {
        return vertices;
    }
    public HashMap<String, Edge<E>> getEdges() {
        return edges;
    }
    public E get(String key) {
        return this.vertices.get(key).getData();
    }

    /**
     * Performs the Dijkstra's algorithm on the graph to find the shortest path between the start vertex and the end vertex. The vertices have to be connected by at least one path and contained in the same graph.
     * @param v1 Start
     * @param v2 End
     * @return integer value equivalent to the sum of the weights of all the edges contained in the shortest path between the start and the end
     */
    public int getDistance(Vertex<E> v1, Vertex<E> v2) {
        findShortestEdgePath(v1, v2);
        return v2.getDistance();
    }

    /**
     * Finds the vertices that are assigned the specified labels and invokes the Graph.getDistance() method using them as the two parameters. Both of the labels have to be assigned to a vertex in this graph. Performs the Dijkstra's algorithm on the graph to find the shortest path between the start vertex and the end vertex. The vertices have to be connected by at least one path and contained in the same graph.
     * @param v1 Start vertex label
     * @param v2 End vertex label
     * @return integer value equivalent to the sum of the weights of all the edges contained in the shortest path between the start and the end
     */
    public int getDistance(String v1, String v2) {
        return getDistance(this.vertices.get(v1), this.vertices.get(v2));
    }

    /**
     * Searches for all the neighbors (vertices connected to this one by exactly one edge) of the specified vertex and returns an arraylist containing all of their data.
     * @param key The label of the desired vertex
     * @return an arraylist containing all the data of the neighboring vertices
     */
    public ArrayList<E> getNeighbors(String key) {
        ArrayList<E> neighbors = new ArrayList<>();
        this.getAdjacentVertices(this.vertices.get(key)).values().stream().forEach(n -> neighbors.add(n.getData()));
        return neighbors;
    }

    /**
     * Searches for the vertices which are assigned the specified labels and finds the shortest path between the two specified vertices and returns an arraylist containing all their data.
     * @param start Start vertex label
     * @param end End vertex label
     * @return an arraylist containing all the data of the vertices contained on the path
     */
    public ArrayList<E> getShortestPath(String start, String end) {
        ArrayList<E> path = new ArrayList<>();
        this.findShortestVertexPath(start, end).stream().forEach(v -> path.add(v.getData()));
        return path;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setVertices(HashMap<String, Vertex<E>> vertices) {
        this.vertices = vertices;
    }
    public void setEdges(HashMap<String, Edge<E>> edges) {
        this.edges = edges;
    }
    public void set(String key, E data) {
        this.vertices.get(key).setData(data);
    }

    //adders
    public void add(String key, E data) {
        this.addVertex(key);
        this.set(key, data);
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

    //general methods

    /**
     * Returns the amount of vertices in this graph.
     * @return the amount of vertices in this graph
     */
    public int size() {
        return vertices.size();
    }

    /**
     * Connects the two specified vertices by a path with the specified parameters, thus making them neighbors to each other.
     * @param edgeLabel Label to be assigned to the connecting edge
     * @param tail Starting vertex of the edge
     * @param head Ending vertex of the edge
     * @param directed <p>True - makes the edge directed, making it traversable only in the direction from its tail to its head<p>False - makes the edge not directed, making it traversable in both directions
     * @param edgeWeight The weight to be assigned to the connecting edge
     */
    public void connectVertices(String edgeLabel, Vertex<E> tail, Vertex<E> head, boolean directed, int edgeWeight) {
        if (this.vertices.containsValue(tail) && this.vertices.containsValue(head)) {
            if (!this.isConnectedTo(tail, head)) {
                Edge<E> edge = new Edge<>(edgeLabel, tail.getLabel(), head.getLabel(), directed, edgeWeight);
                this.edges.put(edgeLabel, edge);
                tail.addEdge(edge.getLabel());
                head.addEdge(edge.getLabel());
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

    /**
     * Connects the two specified vertices by a path with the specified parameters, thus making them neighbors to each other.
     * @param edgeLabel Label to be assigned to the connecting edge
     * @param tail Label of the starting vertex of the edge
     * @param head Label of the ending vertex of the edge
     * @param directed <p>True - makes the edge directed, making it traversable only in the direction from its tail to its head<p>False - makes the edge not directed, making it traversable in both directions
     * @param edgeWeight The weight to be assigned to the connecting edge
     */
    public void connectVertices(String edgeLabel, String tail, String head, boolean directed, int edgeWeight) {
        if (this.vertices.containsKey(tail) && this.vertices.containsKey(head)) {
            this.connectVertices(edgeLabel, this.vertices.get(tail), this.vertices.get(head), directed, edgeWeight);
        } else {
            if (!this.vertices.containsKey(tail)) {
                throw new NoSuchElementException("Vertex " + tail + " is not a part of the graph");
            }
            if (!this.vertices.containsKey(head)) {
                throw new NoSuchElementException("Vertex " + head + " is not a part of the graph");
            }
        }
    }

    public ArrayList<Vertex<E>> findShortestVertexPath(String start, String end) {
        return findShortestVertexPath(this.vertices.get(start), this.vertices.get(end));
    }

    public ArrayList<Vertex<E>> findShortestVertexPath(Vertex<E> start, Vertex<E> end) {
        ArrayList<Vertex<E>> path = new ArrayList<>();
        path.add(start);
        ArrayList<Edge<E>> edgePath = this.findShortestEdgePath(start, end);
        Vertex<E> current = start;
        for (Edge<E> e : edgePath) {
            current = this.getOtherEndpoint(e, current);
            path.add(current);
        }
        return path;
    }

    public ArrayList<Edge<E>> findShortestEdgePath(String start, String end) {
        return findShortestEdgePath(this.vertices.get(start), this.vertices.get(end));
    }

    public ArrayList<Edge<E>> findShortestEdgePath(Vertex<E> start, Vertex<E> end) {
        ArrayList<Edge<E>> shortestPath = new ArrayList<>();

        //all vertices except start set distance to max, remove parent/child relations, and set to unclosed(open)
        for (Vertex<E> v : this.vertices.values()) {
            v.setDistance(Integer.MAX_VALUE);
            v.setClosed(false);
            v.setToParent(null);
            v.setToChild(null);
        }
        start.setDistance(0);

        //probable start of a second method that might be created from this part
        setAllDistances(start);

        Vertex<E> current = end;
        while (current.getToParent() != null) {
            Edge<E> toParent = this.edges.get(current.getToParent());
            this.getOtherEndpoint(toParent, current).setToChild(current.getToParent());
            shortestPath.add(toParent);
            current = this.getOtherEndpoint(toParent, current);
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }

    private void setAllDistances(Vertex<E> start) {
        if (start.isClosed()) {
            return;
        }

        ArrayList<Edge<E>> currentEdges = new ArrayList<>(this.getVertexEdges(start));
        Collections.sort(currentEdges);

        //neighbors set distance based on the distance from original start
        for (Edge<E> e : currentEdges) {
            if (!this.getOtherEndpoint(e, start).isClosed()) {
                if (this.getOtherEndpoint(e, start).getDistance() > start.getDistance() + e.getWeight()) {
                    this.getOtherEndpoint(e, start).setDistance(start.getDistance() + e.getWeight());
                    this.getOtherEndpoint(e, start).setToParent(e.getLabel());
                }
            }
        }

        start.setClosed(true);
        setAllDistances(getClosestUnclosedVertex());
    }

    private Vertex<E> getClosestUnclosedVertex() {
        Vertex<E> closest = new Vertex<>(Integer.MAX_VALUE, true);
        for (Vertex<E> v : vertices.values()) {
            if (v.getDistance() < closest.getDistance() && !v.isClosed()) {
                closest = v;
            }
        }
        return closest;
    }

    public HashMap<String, Vertex<E>> getAdjacentVertices(Vertex<E> vertex) {
        HashMap<String, Vertex<E>> adjacentVertices = new HashMap<>();
        for (Edge<E> e : this.getVertexEdges(vertex)) {
            Vertex<E> otherEndpoint = this.getOtherEndpoint(e, vertex);
            adjacentVertices.put(otherEndpoint.getLabel(), otherEndpoint);
        }
        return adjacentVertices;
    }

    public Vertex<E> getClosestNeighbor(Vertex<E> vertex) {
        Vertex<E> closest = null;
        for (Vertex<E> v : this.getAdjacentVertices(vertex).values()) {
            if (closest == null || v.getDistance() < closest.getDistance()) {
                closest = v;
            }
        }
        return closest;
    }

    public boolean isConnectedTo(Vertex<E> v1, Vertex<E> v2) {
        for (Edge<E> e : this.getVertexEdges(v1)) {
            if (this.getOtherEndpoint(e, v1).equals(v2)) {
                return true;
            }
        }
        return false;
    }

    public Vertex<E> getOtherEndpoint(Edge<E> edge, Vertex<E> vertex) {
        if (vertex.equals(this.vertices.get(edge.getTail()))) {
            return this.vertices.get(edge.getHead());
        }
        if (vertex.equals(this.vertices.get(edge.getHead()))) {
            return this.vertices.get(edge.getTail());
        }
        return null;
    }

    public Vertex<E> getOtherEndpoint(Edge<E> edge, String vertex) {
        return this.getOtherEndpoint(edge, this.vertices.get(vertex));
    }

    public Vertex<E> getOtherEndpoint(String edge, Vertex<E> vertex) {
        return this.getOtherEndpoint(this.edges.get(edge), vertex);
    }

    public Vertex<E> getOtherEndpoint(String edge, String vertex) {
        return this.getOtherEndpoint(this.edges.get(edge), this.vertices.get(vertex));
    }

    public ArrayList<Edge<E>> getVertexEdges(Vertex<E> vertex) {
        ArrayList<Edge<E>> vertexEdges = new ArrayList<>();
        for (String e : vertex.getEdges()) {
            vertexEdges.add(this.edges.get(e));
        }
        return vertexEdges;
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
