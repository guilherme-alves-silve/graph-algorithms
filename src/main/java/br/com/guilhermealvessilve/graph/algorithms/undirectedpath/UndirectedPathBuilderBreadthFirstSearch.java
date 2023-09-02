package br.com.guilhermealvessilve.graph.algorithms.undirectedpath;

import java.util.*;

import static java.util.Map.entry;

public class UndirectedPathBuilderBreadthFirstSearch {

    public static void main(String[] args) {
        var solver = new UndirectedPathBuilderBreadthFirstSearch();

        var edges = List.of(
                entry("i", "j"),
                entry("k", "i"),
                entry("m", "k"),
                entry("k", "l"),
                entry("o", "n")
        );

        System.out.println("undirectedPath(edges, 'j', 'm'): " + solver.buildUndirectedPath(edges, "j", "m")); // [j, i, k, m]
        System.out.println("undirectedPath(edges, 'm', 'j'): " + solver.buildUndirectedPath(edges, "m", "j")); // -> [m, k, i, j]
        System.out.println("undirectedPath(edges, 'l', 'j'): " + solver.buildUndirectedPath(edges, "l", "j")); // -> [l, k, i, j]
        System.out.println("undirectedPath(edges, 'k', 'o'): " + solver.buildUndirectedPath(edges, "k", "o")); // -> []
        System.out.println("undirectedPath(edges, 'i', 'o'): " + solver.buildUndirectedPath(edges, "i", "o")); // -> []
    }

    public List<String> buildUndirectedPath(List<Map.Entry<String, String>> undirectedGraph, String src, String dest) {
        var graph = buildGraph(undirectedGraph);
        var visited = new HashSet<String>();

        var queue = new ArrayDeque<String>();
        queue.addLast(src);

        var nodeAndPaths = new HashMap<String, Path>();
        nodeAndPaths.put(src, new Path(src, null));
        while (!queue.isEmpty()) {
            var node = queue.pollFirst();
            if (node.equals(dest)) break;
            for (var neighbor : graph.get(node)) {
                if (!visited.contains(neighbor)) {
                    nodeAndPaths.put(neighbor, new Path(neighbor, nodeAndPaths.get(node)));
                    visited.add(neighbor);
                    queue.addLast(neighbor);
                }
            }
        }

        return buildPath(dest, nodeAndPaths);
    }

    private List<String> buildPath(String dest, Map<String, Path> nodeAndPaths) {

        var path = new ArrayList<String>();

        for (var actual = nodeAndPaths.get(dest); actual != null; actual = actual.parent) {
            path.add(actual.node);
        }

        Collections.reverse(path);
        return path;
    }

    private Map<String, List<String>> buildGraph(List<Map.Entry<String, String>> undirectedGraph) {
        var graph = new HashMap<String, List<String>>();
        for (var pair : undirectedGraph) {
            var nodeA = pair.getKey();
            var nodeB = pair.getValue();

            if (!graph.containsKey(nodeA)) graph.put(nodeA, new ArrayList<>());
            if (!graph.containsKey(nodeB)) graph.put(nodeB, new ArrayList<>());

            graph.get(nodeA).add(nodeB);
            graph.get(nodeB).add(nodeA);
        }

        return graph;
    }

    private static class Path {
        final String node;
        final Path parent;

        Path(String node, Path parent) {
            this.node = node;
            this.parent = parent;
        }
    }
}
