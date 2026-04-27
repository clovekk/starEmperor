import game.StarSystem;
import graph.Graph;

public class Main {
    public static void main(String[] args) {
        //temporary1 - test to see if the reworked graph still works
        Graph<StarSystem> g1 = new Graph<>("g1");

        g1.addVertex("A");
        g1.addVertex("B");
        g1.addVertex("C");
        g1.addVertex("D");
        g1.addVertex("E");

        g1.connectVertices("f", "A", "E", false, 20);
        g1.connectVertices("g", "A", "B", false, 10);
        g1.connectVertices("h", "B", "C", false, 8);
        g1.connectVertices("i", "C", "D", false, 10);
        g1.connectVertices("j", "B", "D", false, 15);
        g1.connectVertices("k", "A", "C", false, 20);

        String start = "D";
        String end = "E";
        System.out.println("Shortest path from Vertex " + start + " to Vertex " + end + ": " + g1.findShortestPath(start, end));
        System.out.println("Length of the shortest path from Vertex " + start + " to Vertex " + end + " is: " + g1.getDistance(end));
        System.out.println(g1);
        //temporary 1 - end
    }
}
