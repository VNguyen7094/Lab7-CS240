import java.util.*;

public class GraphExample {

    public static class Graph {
        private Map<Integer, List<Integer>> adjList;

        public Graph() {
            adjList = new HashMap<>();
        }

        public void addVertex(int vertex) {
            adjList.putIfAbsent(vertex, new ArrayList<>());
        }

        public void addEdge(int source, int destination) {
            adjList.putIfAbsent(source, new ArrayList<>());
            adjList.putIfAbsent(destination, new ArrayList<>());
            adjList.get(source).add(destination);
            adjList.get(destination).add(source); 
        }

        public void printGraph() {
            for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
                System.out.print("Vertex " + entry.getKey() + ": ");
                for (Integer neighbor : entry.getValue()) {
                    System.out.print(neighbor + " ");
                }
                System.out.println();
            }
        }

        public List<Integer> getNeighbors(int vertex) {
            return adjList.getOrDefault(vertex, new ArrayList<>());
        }

        public Set<Integer> getVertices() {
            return adjList.keySet();
        }
    }

    // Breadth-First Search (BFS)
    public static List<Integer> bfs(Graph graph, int start) {
        List<Integer> traversalOrder = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            traversalOrder.add(node);

            for (int neighbor : graph.getNeighbors(node)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return traversalOrder;
    }

    // Depth-First Search (DFS) 
    public static List<Integer> dfs(Graph graph, int start) {
        List<Integer> traversalOrder = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfsHelper(graph, start, visited, traversalOrder);
        return traversalOrder;
    }

    // Helper method for DFS (recursive)
    private static void dfsHelper(Graph graph, int node, Set<Integer> visited, List<Integer> traversalOrder) {
        visited.add(node);
        traversalOrder.add(node);

        // Visit all unvisited neighbors
        for (int neighbor : graph.getNeighbors(node)) {
            if (!visited.contains(neighbor)) {
                dfsHelper(graph, neighbor, visited, traversalOrder);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();

        for (int i = 1; i <= 6; i++) {
            graph.addVertex(i);
        }

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);

        // Print the graph
        System.out.println("Graph Adjacency List:");
        graph.printGraph();

        // Perform BFS starting from vertex 1
        List<Integer> bfsOrder = bfs(graph, 1);
        System.out.println("\nBFS Traversal: " + bfsOrder);

        // Perform DFS starting from vertex 1
        List<Integer> dfsOrder = dfs(graph, 1);
        System.out.println("\nDFS Traversal: " + dfsOrder);
    }
}
