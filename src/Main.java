import game.StarSystem;
import graph.Graph;

public class Main {
    public static void main(String[] args) {
        /*//temporary1 - test to see if the reworked graph still works
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
        //temporary1 - end*/

        //temporary2 - bigger test for the graph
        Graph<StarSystem> g1 = new Graph<>("g1");

        g1.addVertex("A");
        g1.addVertex("B");
        g1.addVertex("C");
        g1.addVertex("D");
        g1.addVertex("E");
        g1.addVertex("F");
        g1.addVertex("G");
        g1.addVertex("H");
        g1.addVertex("I");
        g1.addVertex("J");
        g1.addVertex("K");
        g1.addVertex("L");
        g1.addVertex("M");
        g1.addVertex("N");
        g1.addVertex("O");
        g1.addVertex("P");
        g1.addVertex("Q");
        g1.addVertex("R");
        g1.addVertex("S");
        g1.addVertex("T");
        g1.addVertex("U");
        g1.addVertex("V");
        g1.addVertex("W");
        g1.addVertex("X");
        g1.addVertex("Y");

        g1.connectVertices("e1", "A", "B", false, 5);
        g1.connectVertices("e2", "B", "C", false, 6);
        g1.connectVertices("e3", "C", "D", false, 4);
        g1.connectVertices("e4", "D", "E", false, 7);
        g1.connectVertices("e5", "E", "F", false, 3);
        g1.connectVertices("e6", "F", "G", false, 6);
        g1.connectVertices("e7", "G", "H", false, 5);
        g1.connectVertices("e8", "H", "I", false, 4);
        g1.connectVertices("e9", "I", "J", false, 7);
        g1.connectVertices("e10", "J", "K", false, 6);
        g1.connectVertices("e11", "K", "L", false, 5);
        g1.connectVertices("e12", "L", "M", false, 4);
        g1.connectVertices("e13", "M", "N", false, 6);
        g1.connectVertices("e14", "N", "O", false, 5);
        g1.connectVertices("e15", "O", "P", false, 7);
        g1.connectVertices("e16", "P", "Q", false, 4);
        g1.connectVertices("e17", "Q", "R", false, 6);
        g1.connectVertices("e18", "R", "S", false, 5);
        g1.connectVertices("e19", "S", "T", false, 3);
        g1.connectVertices("e20", "T", "U", false, 6);
        g1.connectVertices("e21", "U", "V", false, 4);
        g1.connectVertices("e22", "V", "W", false, 5);
        g1.connectVertices("e23", "W", "X", false, 7);
        g1.connectVertices("e24", "X", "Y", false, 6);
        g1.connectVertices("e25", "A", "F", false, 9);
        g1.connectVertices("e26", "C", "H", false, 8);
        g1.connectVertices("e27", "E", "J", false, 10);
        g1.connectVertices("e28", "H", "M", false, 7);
        g1.connectVertices("e29", "L", "Q", false, 9);
        g1.connectVertices("e30", "P", "U", false, 8);
        g1.connectVertices("e31", "T", "Y", false, 10);

        String start = "Y";
        String end = "A";
        System.out.println("Shortest path from Vertex " + start + " to Vertex " + end + ": " + g1.findShortestPath(start, end));
        System.out.println("Length of the shortest path from Vertex " + start + " to Vertex " + end + " is: " + g1.getDistance(end));
        System.out.println(g1);
        //temporary2 - end
    }
}
