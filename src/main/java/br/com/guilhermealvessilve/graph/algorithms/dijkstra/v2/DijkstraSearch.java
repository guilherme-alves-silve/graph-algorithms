package br.com.guilhermealvessilve.graph.algorithms.dijkstra.v2;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class DijkstraSearch {

    public static void main(String[] args) {

        Map<String, List<Edge>> graph1 = Map.of(
            "A", List.of(edge(5, "B"), edge(9, "E"), edge(8, "H")),
            "B", List.of(edge(12, "C"), edge(15, "D"), edge(4, "H")),
            "C", List.of(edge(3, "D"), edge(11, "G")),
            "D", List.of(edge(9, "G")),
            "E", List.of(edge(4, "F"), edge(20, "G"), edge(5, "H")),
            "F", List.of(edge(1, "C"), edge(13, "H")),
            "G", List.of(),
            "H", List.of(edge(7, "C"), edge(6, "F"))
        );

        Map<String, List<Edge>> graph2 = Map.of(
                "A", List.of(edge(5, "B"), edge(8, "C")),
                "B", List.of(edge(12, "A"), edge(2, "C")),
                "C", List.of(edge(20, "A"), edge(7, "B"))
        );

        Map<String, List<Edge>> graph3 = Map.of(
                "A", List.of(edge(1, "B"), edge(1, "C")),
                "B", List.of(edge(1, "A"), edge(1, "C")),
                "C", List.of(edge(1, "A"), edge(1, "B"))
        );

        System.out.println("shortestPath(A, D): " + getShortestPath(graph1, "A", "D")); // -> [A, E, F, C, D]
        System.out.println("shortestPath(A, G): " + getShortestPath(graph1, "A", "G")); // -> [A, E, F, C, G]
        System.out.println("shortestPath(A, C): " + getShortestPath(graph2, "A", "C")); // -> [A, B, C]
        System.out.println("shortestPath(A, C): " + getShortestPath(graph3, "A", "C")); // -> [A, C]
    }

    public static List<Path> getShortestPath(Map<String, List<Edge>> graph, String src, String dest) {
        
        var queue = new PriorityQueue<>(Comparator.comparingInt(Path::getDistance));
        var visited = new HashSet<Path>();

        var nodeAndPath = buildNodeAndPath(graph);
        var srcPath = nodeAndPath.get(src);
        srcPath.setDistance(0);
        queue.add(srcPath);
        
        while(!queue.isEmpty()) {
            var current = queue.poll();
            visited.add(current);
            for (var edge : graph.get(current.getNode())) {

                var neighbor = nodeAndPath.get(edge.target());
                var distance = current.getDistance() + edge.weight();

                if (!visited.contains(neighbor) || distance < neighbor.getDistance()) {
                    queue.remove(neighbor);
                    neighbor.setParent(current)
                            .setDistance(distance);
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        
        return buildDestinationPath(dest, nodeAndPath);
    }

    private static Map<String, Path> buildNodeAndPath(Map<String, List<Edge>> graph) {
        return graph.keySet()
                .stream()
                .map(Path::new)
                .collect(toMap(Path::getNode, Function.identity()));
    }

    private static List<Path> buildDestinationPath(final String dest, final Map<String, Path> graphPath) {
        var path = new ArrayList<Path>();
        var destPath = graphPath.get(dest);
        for (var node = destPath; node != null; node = node.getParent()) {
            path.add(node);
        }

        Collections.reverse(path);
        return path;
    }

    private static Edge edge(int weight, String target) {
        return new Edge(weight, target);
    }
}
