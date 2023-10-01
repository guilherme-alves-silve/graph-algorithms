package br.com.guilhermealvessilve.graph.mapsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class CityVertex {
    private final String name;
    private int distance;
    private final List<CityEdge> unmodifiableNeighbors;
    private boolean visited;
    private final List<CityEdge> neighbors;

    public CityVertex(String name, int distance) {
        this.name = name;
        this.distance = distance;
        this.neighbors= new ArrayList<>();
        this.unmodifiableNeighbors = Collections.unmodifiableList(neighbors);
    }

    public String getName() {
        return name;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public CityVertex setVisited(boolean visited) {
        this.visited = visited;
        return this;
    }

    public boolean isVisited() {
        return visited;
    }

    public CityVertex addEdge(CityVertex vertex, int distance) {
        neighbors.add(new CityEdge(distance, vertex));
        return this;
    }

    public CityVertex addEdge(CityVertex vertex) {
        neighbors.add(new CityEdge(vertex));
        return this;
    }

    public List<CityEdge> getNeighbors() {
        return unmodifiableNeighbors;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "V{", "}")
                .add("name='" + name + "'")
                .add("distance=" + distance)
                .toString();
    }
}
