package br.com.guilhermealvessilve.graph.algorithms.cycledetection;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CycleDetectionDepthFirstSearch {

    public static void main(String[] args) {
        Map<String, List<String>> graph1 = Map.of(
                "A", List.of("E", "C"),
                "B", List.of(),
                "C", List.of("B", "D"),
                "D", List.of(),
                "E", List.of("F"),
                "F", List.of("A")
        );

        Map<String, List<String>> graph2 = Map.of(
                "A", List.of("E", "C"),
                "B", List.of(),
                "C", List.of("B", "D"),
                "D", List.of(),
                "E", List.of(),
                "F", List.of("A")
        );

        Map<String, List<String>> graph3 = Map.of(
                "A", List.of("B"),
                "B", List.of("C"),
                "C", List.of("A")
        );

        Map<String, List<String>> graph4 = Map.of(
                "A", List.of("B"),
                "B", List.of("C"),
                "C", List.of()
        );

        Map<String, List<String>> graph5 = Map.of(
                "A", List.of("B"),
                "B", List.of("C"),
                "C", List.of("D"),
                "D", List.of("E"),
                "E", List.of("F"),
                "F", List.of("G"),
                "G", List.of("H"),
                "H", List.of("B")
        );

        Map<String, List<String>> graph6 = Map.of(
                "A", List.of("B"),
                "B", List.of("C"),
                "C", List.of("D"),
                "D", List.of("E"),
                "E", List.of("F"),
                "F", List.of("G"),
                "G", List.of("H"),
                "H", List.of()
        );

        System.out.println("hasCycle: " + hasCycle(graph1)); // -> true
        System.out.println("hasCycle: " + hasCycle(graph2)); // -> false
        System.out.println("hasCycle: " + hasCycle(graph3)); // -> true
        System.out.println("hasCycle: " + hasCycle(graph4)); // -> false
        System.out.println("hasCycle: " + hasCycle(graph5)); // -> true
        System.out.println("hasCycle: " + hasCycle(graph6)); // -> false
        System.out.println("hasCycle: " + hasCycle(Map.of())); // -> false
    }

    public static boolean hasCycle(Map<String, List<String>> graph) {
        var visited = new HashSet<String>();
        var beingVisited = new HashSet<String>();
        for (var node : graph.keySet()) {
            if (hasCycle(graph, node, visited, beingVisited)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasCycle(Map<String, List<String>> graph,
                                    String node,
                                    Set<String> visited,
                                    Set<String> beingVisited) {
        if (visited.contains(node)) return false;
        beingVisited.add(node);

        for (var neighbor : graph.get(node)) {
            if (beingVisited.contains(neighbor)) return true;
            if (hasCycle(graph, neighbor, visited, beingVisited)) return true;
        }

        beingVisited.remove(node);
        visited.add(node);

        return false;
    }
}
