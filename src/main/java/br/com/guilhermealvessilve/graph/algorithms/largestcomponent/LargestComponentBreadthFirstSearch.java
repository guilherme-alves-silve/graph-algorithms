package br.com.guilhermealvessilve.graph.algorithms.largestcomponent;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class LargestComponentBreadthFirstSearch {

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
        var queue = new ArrayDeque<Integer>();

        int largest = 0;
        for (var node : graph.keySet()) {
            queue.addLast(node);

            int counted = 0;
            while (!queue.isEmpty()) {
                var current = queue.pollFirst();

                for (var neighbor : graph.get(current)) {
                    if (!visited.contains(neighbor)) {
                        queue.addLast(neighbor);
                        visited.add(neighbor);
                        ++counted;
                    }
                }
            }

            if (counted > largest) largest = counted;
        }

        return largest;
    }
}
