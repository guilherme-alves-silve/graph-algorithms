package br.com.guilhermealvessilve.graph.algorithms.haspath;

import java.util.List;
import java.util.Map;

public class HasPathDepthFirstSearch {

    public static void main(String[] args) {
        Map<String, List<String>> graph1 = Map.of(
            "f", List.of("g", "i"),
            "g", List.of("h"),
            "h", List.of(),
            "i", List.of("g", "k"),
            "j", List.of("i"),
            "k", List.of()
        );

        Map<String, List<String>> graph2 = Map.of(
            "v", List.of("x", "w"),
            "w", List.of(),
            "x", List.of(),
            "y", List.of("z"),
            "z", List.of()
        );

        System.out.println("hasPath(graph1, \"f\", \"k\"): " + hasPath(graph1, "f", "k")); // true
        System.out.println("hasPath(graph1, \"f\", \"j\"): " + hasPath(graph1, "f", "j")); // false
        System.out.println("hasPath(graph1, \"i\", \"h\"): " + hasPath(graph1, "i", "h")); // true
        System.out.println("hasPath(graph2, \"v\", \"w\"): " + hasPath(graph2, "v", "w")); // true
        System.out.println("hasPath(graph2, \"v\", \"z\"): " + hasPath(graph2, "v", "z")); // false
    }

    public static boolean hasPath(Map<String, List<String>> graph, String src, String dest) {
        if (src.equals(dest)) return true;

        for (var neighbor : graph.get(src)) {
            if (hasPath(graph, neighbor, dest)) {
                return true;
            }
        }

        return false;
    }
}
