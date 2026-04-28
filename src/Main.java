import graph.Graph;

public class Main {
    public static void main(String[] args) {
        //temporary1 - test to see if the reworked graph still works
        Graph<String> g1 = new Graph<>("g1");

        g1.add("A", "1");
        g1.add("B", "2");
        g1.add("C", "3");
        g1.add("D", "4");
        g1.add("E", "5");

        g1.connectVertices("f", "A", "E", false, 20);
        g1.connectVertices("g", "A", "B", false, 10);
        g1.connectVertices("h", "B", "C", false, 8);
        g1.connectVertices("i", "C", "D", false, 10);
        g1.connectVertices("j", "B", "D", false, 15);
        g1.connectVertices("k", "A", "C", false, 20);

        String start = "A";
        String end = "D";
        System.out.println("Shortest path from " + start + " to " + end + ": " + g1.getShortestPath(start, end));
        System.out.println("Shortest edge path from Vertex " + start + " to Vertex " + end + ": " + g1.findShortestEdgePath(start, end));
        System.out.println("Shortest vertex path from Vertex " + start + " to Vertex " + end + ": " + g1.findShortestVertexPath(start, end));
        System.out.println("Length of the shortest path from Vertex " + start + " to Vertex " + end + " is: " + g1.getDistance(start, end));
        System.out.println(g1);
        //temporary1 - end

        /*//temporary2 - bigger test for the graph
        Graph<String> g2 = new Graph<>("g1");

        g1.add("A", "1");
        g1.add("B", "2");
        g1.add("C", "3");
        g1.add("D", "4");
        g1.add("E", "5");
        g1.add("F", "6");
        g1.add("G", "7");
        g1.add("H", "8");
        g1.add("I", "9");
        g1.add("J", "10");
        g1.add("K", "11");
        g1.add("L", "12");
        g1.add("M", "13");
        g1.add("N", "14");
        g1.add("O", "15");
        g1.add("P", "16");
        g1.add("Q", "17");
        g1.add("R", "18");
        g1.add("S", "19");
        g1.add("T", "20");
        g1.add("U", "21");
        g1.add("V", "22");
        g1.add("W", "23");
        g1.add("X", "24");
        g1.add("Y", "25");

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
        System.out.println("Shortest path from " + start + " to " + end + ": " + g2.getShortestPath(start, end));
        System.out.println("Shortest edge path from Vertex " + start + " to Vertex " + end + ": " + g2.findShortestEdgePath(start, end));
        System.out.println("Shortest vertex path from Vertex " + start + " to Vertex " + end + ": " + g2.findShortestVertexPath(start, end));
        System.out.println("Length of the shortest path from Vertex " + start + " to Vertex " + end + " is: " + g2.getDistance(start, end));
        start = "C";
        end = "T";
        System.out.println("Shortest path from " + start + " to " + end + ": " + g2.getShortestPath(start, end));
        System.out.println("Shortest edge path from Vertex " + start + " to Vertex " + end + ": " + g2.findShortestEdgePath(start, end));
        System.out.println("Shortest vertex path from Vertex " + start + " to Vertex " + end + ": " + g2.findShortestVertexPath(start, end));
        System.out.println("Length of the shortest path from Vertex " + start + " to Vertex " + end + " is: " + g2.getDistance(start, end));
        System.out.println(g2);
        //temporary2 - end*/
    }
}
