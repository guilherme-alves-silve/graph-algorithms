package br.com.guilhermealvessilve.graph.algorithms.dijkstra.v2;

import java.util.Objects;

public class Path {
    private int distance;
    private final String node;
    private Path parent;

    Path(String node) {
        this.distance = Integer.MAX_VALUE;
        this.node = node;
    }

    public int getDistance() {
        return distance;
    }

    void setDistance(int distance) {
        this.distance = distance;
    }

    public String getNode() {
        return node;
    }

    public Path getParent() {
        return parent;
    }

    Path setParent(Path parent) {
        this.parent = parent;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Path path)) return false;
        return Objects.equals(node, path.node);
    }

    @Override
    public String toString() {
        return node;
    }

    @Override
    public int hashCode() {
        return Objects.hash(node);
    }
}
