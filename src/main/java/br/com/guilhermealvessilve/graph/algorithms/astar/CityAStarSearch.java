package br.com.guilhermealvessilve.graph.algorithms.astar;

import br.com.guilhermealvessilve.graph.mapsearch.CityEdge;
import br.com.guilhermealvessilve.graph.mapsearch.CityVertex;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class CityAStarSearch {

    public static boolean search(CityVertex src, CityVertex dest) {
        if (src.equals(dest)) {
            System.out.println("Found " + dest);
            return true;
        }

        src.setVisited(true);
        System.out.println("Visited " + src);

        var compareAStarDistance = Comparator.comparingInt(CityEdge::getDistanceAStar);
        var queue = new PriorityQueue<>(compareAStarDistance);
        for (var edge : src.getNeighbors()) {
            var neighbor = edge.getSource();
            if (!neighbor.isVisited()) {
                queue.add(edge);
                neighbor.setVisited(true);
            }
        }

        System.out.println(new TreeSet<>(compareAStarDistance){{ addAll(queue); }});
        if (!queue.isEmpty()) return search(queue.poll().getSource(), dest);
        return false;
    }
}
