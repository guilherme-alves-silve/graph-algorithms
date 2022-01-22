package br.com.guilhermealvessilve.graph.algorithms.largestcomponent;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LargestComponentDepthFirstSearch {

    public static void main(String[] args) {

        Map<Integer, List<Integer>> graph1 = Map.of(
            1, List.of(2),
            2, List.of(1, 8),
            6, List.of(7),
            9, List.of(8),
            7, List.of(6, 8),
            8, List.of(9, 7, 2)
        );

        Map<Integer, List<Integer>> graph2 = Map.of(
            3, List.of(),
            4, List.of(6),
            6, List.of(4, 5, 7, 8),
            8, List.of(6),
            7, List.of(6),
            5, List.of(6),
            1, List.of(2),
            2, List.of(1)
        );

        Map<Integer, List<Integer>> graph3 = Map.of(
            0, List.of(4,7),
            1, List.of(),
            2, List.of(),
            3, List.of(6),
            4, List.of(0),
            6, List.of(3),
            7, List.of(0),
            8, List.of()
        );

        System.out.println("largestComponent(graph): " + largestComponent(graph1));; // 6
        System.out.println("largestComponent(graph): " + largestComponent(graph2));; // 5
        System.out.println("largestComponent(graph): " + largestComponent(graph3));; // 3
        System.out.println("largestComponent(graph): " + largestComponent(Map.of()));; // 0
    }

    public static int largestComponent(Map<Integer, List<Integer>> graph) {

        var visited = new HashSet<Integer>();
        int largest = 0;
        for (var node : graph.keySet()) {
            int counted = countComponents(graph, node, visited);
            if (counted > largest) largest = counted;
        }

        return largest;
    }

    private static int countComponents(Map<Integer, List<Integer>> graph,
                                       int node,
                                       Set<Integer> visited) {
        if (visited.contains(node)) return 0;
        visited.add(node);

        int counted = 1;
        for (var neighbor : graph.get(node)) {
            counted += countComponents(graph, neighbor, visited);
        }

        return counted;
    }
}
