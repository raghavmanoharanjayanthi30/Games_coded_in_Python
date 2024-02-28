import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.PriorityQueue;
/**
 * Your implementation of various different graph algorithms.
 *
 * @author Raghav Raahul Manoharan Jayanthi
 * @version 1.0
 * @userid YOUR USER ID rjayanthi30
 * @GTID YOUR GT ID 903536510
 *
 * Collaborators: none
 *
 * Resources: none
 */
public class GraphAlgorithms {

    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for BFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Invalid vertex");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Invalid graph");
        }
        if (!(graph.getVertices().contains(start))) {
            throw new IllegalArgumentException("The vertex start does not exist in the graph.");
        }
        Set<Vertex<T>> set = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        List<Vertex<T>> list = new ArrayList<>();
        set.add(start);
        queue.add(start);
        Map<Vertex<T>, List<VertexDistance<T>>> adjacentList = graph.getAdjList();
        while (queue.size() != 0 && list.size() != graph.getVertices().size()) {
            Vertex<T> removed = queue.remove();
            list.add(removed);
            for (VertexDistance<T> vd: adjacentList.get(removed)) {
                Vertex<T> vertex = vd.getVertex();
                if (!(set.contains(vertex))) {
                    set.add(vertex);
                    queue.add(vertex);
                }
            }
        }
        return list;
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * *NOTE* You MUST implement this method recursively, or else you will lose
     * all points for this method.
     *
     * You may import/use java.util.Set, java.util.List, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you may use is the
     * adjacency list from graph. DO NOT create new instances of Map
     * for DFS (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Invalid vertex");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Invalid graph");
        }
        if (!(graph.getVertices().contains(start))) {
            throw new IllegalArgumentException("The vertex start does not exist in the graph.");
        }
        Set<Vertex<T>> set = new HashSet<>();
        set.add(start);
        List<Vertex<T>> list = new ArrayList<>();
        list.add(start);
        dfsHelper(start, graph, list, set);
        return list;
    }

    /**
     * Helper method for depth first search method
     * @param start vertex to start the search for
     * @param graph graph to traverse
     * @param list list of vertices to be updated
     * @param set vertex set that is used to hold visited vertices
     * @param <T> generic type to accept different object types
     */
    private static <T> void dfsHelper(Vertex<T> start, Graph<T> graph, List<Vertex<T>> list, Set<Vertex<T>> set) {
        Map<Vertex<T>, List<VertexDistance<T>>> adjacentList = graph.getAdjList();
        for (VertexDistance<T> vd: adjacentList.get(start)) {
            Vertex<T> w = vd.getVertex();
            if (!(set.contains(w))) {
                set.add(w);
                list.add(w);
                dfsHelper(w, graph, list, set);
            }
        }
    }

    /**
     * Finds the single-source shortest distance between the start vertex and
     * all vertices given a weighted graph (you may assume non-negative edge
     * weights).
     *
     * Return a map of the shortest distances such that the key of each entry
     * is a node in the graph and the value for the key is the shortest distance
     * to that node from start, or Integer.MAX_VALUE (representing
     * infinity) if no path exists.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Map, and java.util.Set and any class that
     * implements the aforementioned interfaces, as long as your use of it
     * is efficient as possible.
     *
     * You should implement the version of Dijkstra's where you use two
     * termination conditions in conjunction.
     *
     * 1) Check if all of the vertices have been visited.
     * 2) Check if the PQ is empty yet.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstras(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null) {
            throw new IllegalArgumentException("Invalid vertex");
        }
        if (graph == null) {
            throw new IllegalArgumentException("Invalid graph");
        }
        if (!(graph.getVertices().contains(start))) {
            throw new IllegalArgumentException("The vertex start does not exist in the graph.");
        }
        Map<Vertex<T>, Integer> distanceMap = new HashMap<>();
        Set<Vertex<T>> set = new HashSet<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjacentList = graph.getAdjList();
        for (VertexDistance<T> vd: adjacentList.get(start)) {
            vd = new VertexDistance(vd.getVertex(), Integer.MAX_VALUE);
        }
        pq.add(new VertexDistance<T>(start, 0));
        while (pq.size() != 0 && set.size() != adjacentList.size()) {
            VertexDistance<T> vd = pq.remove();
            Vertex<T> removed = vd.getVertex();
            if (!(set.contains(removed))) {
                set.add(removed);
                distanceMap.put(removed, vd.getDistance());
            }
            for (VertexDistance<T> w: adjacentList.get(removed)) {
                if (!(set.contains(w.getVertex()))) {
                    pq.add(new VertexDistance<>(w.getVertex(), vd.getDistance() + w.getDistance()));
                }
            }
        }
        return distanceMap;
    }

    /**
     * Runs Kruskal's algorithm on the given graph and returns the Minimal
     * Spanning Tree (MST) in the form of a set of Edges. If the graph is
     * disconnected and therefore no valid MST exists, return null.
     *
     * You may assume that the passed in graph is undirected. In this framework,
     * this means that if (u, v, 3) is in the graph, then the opposite edge
     * (v, u, 3) will also be in the graph, though as a separate Edge object.
     *
     * The returned set of edges should form an undirected graph. This means
     * that every time you add an edge to your return set, you should add the
     * reverse edge to the set as well. This is for testing purposes. This
     * reverse edge does not need to be the one from the graph itself; you can
     * just make a new edge object representing the reverse edge.
     *
     * You may assume that there will only be one valid MST that can be formed.
     *
     * Kruskal's will also require you to use a Disjoint Set which has been
     * provided for you. A Disjoint Set will keep track of which vertices are
     * connected given the edges in your current MST, allowing you to easily
     * figure out whether adding an edge will create a cycle. Refer
     * to the DisjointSet and DisjointSetNode classes that
     * have been provided to you for more information.
     *
     * You should NOT allow self-loops or parallel edges into the MST.
     *
     * By using the Disjoint Set provided, you can avoid adding self-loops and
     * parallel edges into the MST.
     *
     * You may import/use java.util.PriorityQueue,
     * java.util.Set, and any class that implements the aforementioned
     * interface.
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * @param <T>   the generic typing of the data
     * @param graph the graph we are applying Kruskals to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Invalid graph");
        }
        DisjointSet<Vertex<T>> disjointSet = new DisjointSet<>();
        for (Vertex<T> vertex: graph.getVertices()) {
            disjointSet.find(vertex);
        }
        Set<Edge<T>> mst = new HashSet<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>();
        for (Edge<T> edge: graph.getEdges()) {
            pq.add(edge);
        }
        int number = graph.getVertices().size();
        while (pq.size() != 0 && mst.size() < 2 * (number - 1)) {
            Edge<T> edge = pq.remove();
            if (!(disjointSet.find(edge.getU()).equals(disjointSet.find(edge.getV())))) {
                mst.add(edge);
                mst.add(new Edge<T>(edge.getV(), edge.getU(), edge.getWeight()));
                disjointSet.union(edge.getU(), edge.getV());
            }
        }
        if (mst.size() != 2 * (number - 1)) {
            return null;
        }
        return mst;
    }
}
