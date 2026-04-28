package graph;

public class Edge<E> implements Comparable<Edge<E>> {
    private String label;
    private String tail;
    private String head;
    private boolean directed;
    private int weight;

    public Edge(String label, String tail, String head, boolean directed, int weight) {
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
    public String getTail() {
        return tail;
    }
    public String getHead() {
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
    public void setTail(String tail) {
        this.tail = tail;
    }
    public void setHead(String head) {
        this.head = head;
    }
    public void setDirected(boolean directed) {
        this.directed = directed;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getOtherEndpoint(String vertex) {
        if (vertex.equals(tail)) {
            return head;
        }
        if (vertex.equals(head)) {
            return tail;
        }
        return null;
    }

    public String getOtherEndpoint(Vertex<E> vertex) {
        if (vertex.getLabel().equals(tail)) {
            return head;
        }
        if (vertex.getLabel().equals(head)) {
            return tail;
        }
        return null;
    }

    /*@Override
    public String toString() {
        return "Edge{" +
                "label='" + label + '\'' +
                ", tail=" + tail +
                ", head=" + head +
                ", directed=" + directed +
                ", weight=" + weight +
                '}';
    }*/

    @Override
    public String toString() {
        return "Edge{" + label + '-' + tail + '-' + head + '-' + weight + '}';
    }

    @Override
    public int compareTo(Edge e) {
        return this.getWeight() - e.getWeight();
    }
}
