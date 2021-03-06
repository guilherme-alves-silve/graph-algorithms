package br.com.guilhermealvessilve.graph.algorithms.connectedcomponentscount;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConnectedComponentsCountDepthFirstSearch {

    public static void main(String[] args) {

        Map<Integer, List<Integer>> graph1 = Map.of(
            3, List.of(),
            4, List.of(6),
            6, List.of(4, 5, 7, 8),
            8, List.of(6),
            7, List.of(6),
            5, List.of(6),
            1, List.of(2),
            2, List.of(1)
        );

        Map<Integer, List<Integer>> graph2 = Map.of(
                0, List.of(4,7),
                1, List.of(),
                2, List.of(),
                3, List.of(6),
                4, List.of(0),
                6, List.of(3),
                7, List.of(0),
                8, List.of()
        );

        System.out.println("connectedComponentsCount: " + connectedComponentsCount(graph1)); // -> 3
        System.out.println("connectedComponentsCount: " + connectedComponentsCount(graph2)); // -> 5
        System.out.println("connectedComponentsCount: " + connectedComponentsCount(Map.of())); // -> 0
    }

    public static int connectedComponentsCount(Map<Integer, List<Integer>> graph) {

        int count = 0;
        var visited = new HashSet<Integer>();
        for (var node : graph.keySet()) {
            if (explore(graph, node, visited)) {
                ++count;
            }
        }

        return count;
    }

    private static boolean explore(Map<Integer, List<Integer>> graph, int node, Set<Integer> visited) {
        if (visited.contains(node)) return false;

        visited.add(node);
        for (var neighbor : graph.get(node)) {
            explore(graph, neighbor, visited);
        }

        return true;
    }
}
