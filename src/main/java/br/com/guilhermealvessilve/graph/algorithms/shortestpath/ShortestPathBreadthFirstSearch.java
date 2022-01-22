package br.com.guilhermealvessilve.graph.algorithms.shortestpath;

import java.util.*;

import static java.util.Map.Entry;
import static java.util.Map.entry;

public class ShortestPathBreadthFirstSearch {

    public static void main(String[] args) {
        var edges1 = List.of(
          entry("a", "c"),
          entry("a", "b"),
          entry("c", "b"),
          entry("c", "d"),
          entry("b", "d"),
          entry("e", "d"),
          entry("g", "f")
        );

        var edges2 = List.of(
          entry("c", "n"),
          entry("c", "e"),
          entry("c", "s"),
          entry("c", "w"),
          entry("w", "e")
        );

        var edges3 = List.of(
          entry("m", "n"),
          entry("n", "o"),
          entry("o", "p"),
          entry("p", "q"),
          entry("t", "o"),
          entry("r", "q"),
          entry("r", "s")
        );

        System.out.println("shortestPath(edges, 'a', 'e'): " + shortestPath(edges1, "a", "e")); // -> 3
        System.out.println("shortestPath(edges, 'e', 'c'): " + shortestPath(edges1, "e", "c")); // -> 2
        System.out.println("shortestPath(edges, 'b', 'g'): " + shortestPath(edges1, "b", "g")); // -> -1
        System.out.println("shortestPath(edges, 'w', 'e'): " + shortestPath(edges2, "w", "e"));; // -> 1
        System.out.println("shortestPath(edges, 'n', 'e'): " + shortestPath(edges2, "n", "e")); // -> 2
        System.out.println("shortestPath(edges, 'm', 's'): " + shortestPath(edges3, "m", "s")); // -> 6
    }

    public static int shortestPath(List<Entry<String, String>> edges, String src, String dest) {

        var graph = buildGraph(edges);
        var queue = new ArrayDeque<Path>();
        var visited = new HashSet<String>();

        queue.addLast(new Path(src));
        visited.add(src);

        while (!queue.isEmpty()) {
            var current = queue.pollFirst();
            if (current.node.equals(dest)) return current.distance;

            for (var neighbor : graph.get(current.node)) {
                if (!visited.contains(neighbor)) {
                    queue.addLast(new Path(neighbor, current.distance + 1));
                    visited.add(neighbor);
                }
            }
        }

        return -1;
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

    private static class Path {

        String node;
        int distance;

        public Path(String node) {
            this(node, 0);
        }

        public Path(String node, int count) {
            this.node = node;
            this.distance = count;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Path path)) return false;
            return distance == path.distance && Objects.equals(node, path.node);
        }

        @Override
        public int hashCode() {
            return Objects.hash(node, distance);
        }
    }
}
