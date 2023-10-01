package br.com.guilhermealvessilve.graph.algorithms.greedy;

import br.com.guilhermealvessilve.graph.mapsearch.CityVertex;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class CityGreedySearch {

    public static boolean search(CityVertex src, CityVertex dest) {
        if (src.equals(dest)) {
            System.out.println("Found " + dest);
            return true;
        }

        src.setVisited(true);
        System.out.println("Visited " + src);

        var compareDistance = Comparator.comparingInt(CityVertex::getDistance);
        var queue = new PriorityQueue<>(compareDistance);
        for (var edge : src.getNeighbors()) {
            var neighbor = edge.getSource();
            if (!neighbor.isVisited()) {
                queue.add(neighbor);
                neighbor.setVisited(true);
            }
        }

        System.out.println(new TreeSet<>(compareDistance){{ addAll(queue); }});
        if (!queue.isEmpty()) return search(queue.poll(), dest);
        return false;
    }
}
