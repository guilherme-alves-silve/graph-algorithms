package br.com.guilhermealvessilve.graph.algorithms.dijkstra.v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraSearch {

    public static void main(String[] args) {
        var vertexA = new Vertex("A");
        var vertexB = new Vertex("B");
        var vertexC = new Vertex("C");
        var vertexD = new Vertex("D");
        var vertexE = new Vertex("E");
        var vertexF = new Vertex("F");
        var vertexG = new Vertex("G");
        var vertexH = new Vertex("H");

        vertexA.addNeighbor(5, vertexB)
            .addNeighbor(9, vertexE)
            .addNeighbor(8, vertexH);

        vertexB.addNeighbor(12, vertexC)
            .addNeighbor(15, vertexD)
            .addNeighbor(4, vertexH);

        vertexC.addNeighbor(3, vertexD)
            .addNeighbor(11, vertexG);

        vertexD.addNeighbor(9, vertexG);

        vertexE.addNeighbor(4, vertexF)
            .addNeighbor(20, vertexG)
            .addNeighbor(5, vertexH);

        vertexF.addNeighbor(1, vertexC)
            .addNeighbor(13, vertexH);

        vertexH.addNeighbor(7, vertexC)
            .addNeighbor(6, vertexF);

        System.out.println("shortestPath(A, D): " + getShortestPath(vertexA, vertexD)); // -> [A, E, F, C, D]
        System.out.println("shortestPath(A, G): " + getShortestPath(vertexA, vertexG)); // -> [A, E, F, C, D, G]
    }

    public static List<Vertex> getShortestPath(Vertex src, Vertex dest) {
        
        var queue = new PriorityQueue<Vertex>();
        var visited = new HashSet<Vertex>();
        src.setDistance(0);
        queue.add(src);
        
        while(!queue.isEmpty()) {
            var current = queue.poll();
            visited.add(current);
            for (var edge : current.getAdjacencyList()) {

                var neighbor = edge.target();
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
        
        return buildDestinationPath(dest);
    }

    private static List<Vertex> buildDestinationPath(final Vertex dest) {
        var path = new ArrayList<Vertex>();
        for (var node = dest; node != null; node = node.getParent()) {
            path.add(node);
        }

        Collections.reverse(path);
        return path;
    }
}
