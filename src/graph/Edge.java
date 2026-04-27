package graph;

public class Edge<E> implements Comparable<Edge<E>> {
    private String label;
    private Vertex<E> end;
    private int weight;

    public Edge(String label, Vertex<E> end, int weight) {
        this.label = label;
        this.end = end;
        this.weight = weight;
    }

    public Edge() {
        this.label = "unspecified";
        this.end = null;
        this.weight = 0;
    }

    public String getLabel() {
        return label;
    }
    public Vertex<E> getEnd() {
        return end;
    }
    public int getWeight() {
        return weight;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public void setEnd(Vertex<E> end) {
        this.end = end;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "label='" + label + '\'' +
                ", end=" + end.getLabel() +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Edge e) {
        if (this.label.equals(e.label)) {
            return 0;
        }
        return this.getWeight() - e.getWeight();
    }
}
