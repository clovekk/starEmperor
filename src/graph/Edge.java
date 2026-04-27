package graph;

public class Edge<E> implements Comparable<Edge<E>> {
    private String label;
    private Vertex<E> tail;
    private Vertex<E> head;
    private boolean directed;
    private int weight;

    public Edge(String label, Vertex<E> tail, Vertex<E> head, boolean directed, int weight) {
        this.label = label;
        this.tail = tail;
        this.head = head;
        this.directed = directed;
        this.weight = weight;
    }

    public Edge() {
        this.label = "unspecified";
        this.tail = null;
        this.head = null;
        this.directed = false;
        this.weight = 0;
    }

    public String getLabel() {
        return label;
    }
    public Vertex<E> getTail() {
        return tail;
    }
    public Vertex<E> getHead() {
        return head;
    }
    public boolean isDirected() {
        return directed;
    }
    public int getWeight() {
        return weight;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public void setTail(Vertex<E> tail) {
        this.tail = tail;
    }
    public void setHead(Vertex<E> head) {
        this.head = head;
    }
    public void setDirected(boolean directed) {
        this.directed = directed;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex<E> getOtherEndpoint(Vertex<E> vertex) {
        if (vertex == tail) {
            return head;
        }
        if (vertex == head) {
            return tail;
        }
        return null;
    }

    /*@Override
    public String toString() {
        return "Edge{" +
                "label='" + label + '\'' +
                ", tail=" + tail.getLabel() +
                ", head=" + head.getLabel() +
                ", directed=" + directed +
                ", weight=" + weight +
                '}';
    }*/

    @Override
    public String toString() {
        return "Edge{" + label + '-' + tail.getLabel() + '-' + head.getLabel() + '-' + weight + '}';
    }

    @Override
    public int compareTo(Edge e) {
        return this.getWeight() - e.getWeight();
    }
}
