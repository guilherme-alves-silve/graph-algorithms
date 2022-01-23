package br.com.guilhermealvessilve.graph.algorithms.undirectedpath;

import java.util.*;

import static java.util.Map.Entry;
import static java.util.Map.entry;

public class UndirectedPathBreadthFirstSearch {

    public static void main(String[] args) {
        var edges = List.of(
                entry("i", "j"),
                entry("k", "i"),
                entry("m", "k"),
                entry("k", "l"),
                entry("o", "n")
        );

        System.out.println("undirectedPath(edges, 'j', 'm'): " + undirectedPath(edges, "j", "m")); // true
        System.out.println("undirectedPath(edges, 'm', 'j'): " + undirectedPath(edges, "m", "j")); // -> true
        System.out.println("undirectedPath(edges, 'l', 'j'): " + undirectedPath(edges, "l", "j")); // -> true
        System.out.println("undirectedPath(edges, 'k', 'o'): " + undirectedPath(edges, "k", "o")); // -> false
        System.out.println("undirectedPath(edges, 'i', 'o'): " + undirectedPath(edges, "i", "o")); // -> false
    }

    public static boolean undirectedPath(List<Entry<String, String>> edges, String nodeA, String nodeB) {
        var graph = buildGraph(edges);
        return hasPath(graph, nodeA, nodeB, new HashSet<>());
    }

    private static Map<String, List<String>> buildGraph(List<Entry<String, String>> edges) {
        var graph = new HashMap<String, List<String>>();
        for (var edge : edges) {
            var nodeA = edge.getKey();
            var nodeB = edge.getValue();
            if (!graph.containsKey(nodeA)) graph.put(nodeA, new ArrayList<>());
            if (!graph.containsKey(nodeB)) graph.put(nodeB, new ArrayList<>());

            graph.get(nodeA).add(nodeB);
            graph.get(nodeB).add(nodeA);
        }

        return graph;
    }

    private static boolean hasPath(Map<String, List<String>> graph, String src, String dest, Set<String> visited) {

        var queue = new ArrayDeque<String>();
        queue.addLast(src);

        while (!queue.isEmpty()) {
            var current = queue.pollFirst();
            visited.add(current);

            if (current.equals(dest)) return true;

            for (var neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.addLast(neighbor);
                }
            }
        }

        return false;
    }
}
