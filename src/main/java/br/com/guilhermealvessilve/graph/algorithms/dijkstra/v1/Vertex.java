package br.com.guilhermealvessilve.graph.algorithms.dijkstra.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex implements Comparable<Vertex> {

    private int distance;
    private final String name;
    private Vertex parent;
    private final List<Edge> adjacencyList;

    public Vertex(String name) {
        this.name = name;
        this.distance = Integer.MAX_VALUE;
        this.adjacencyList = new ArrayList<>();
    }

    public int getDistance() {
        return distance;
    }

    public Vertex setDistance(int distance) {
        this.distance = distance;
        return this;
    }

    public String getName() {
        return name;
    }

    public Vertex getParent() {
        return parent;
    }

    public Vertex setParent(Vertex parent) {
        this.parent = parent;
        return this;
    }

    public List<Edge> getAdjacencyList() {
        return adjacencyList;
    }

    @Override
    public int compareTo(Vertex other) {
        return Integer.compare(distance, other.distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex vertex)) return false;
        return Objects.equals(name, vertex.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }

    public Vertex addNeighbor(int weight, Vertex target) {
        this.adjacencyList.add(new Edge(weight, target));
        return this;
    }
}
