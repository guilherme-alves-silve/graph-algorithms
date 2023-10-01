package br.com.guilhermealvessilve.graph.mapsearch;

import java.util.Objects;
import java.util.StringJoiner;

public class CityEdge {

    private final int distance;
    private final CityVertex source;
    private final int distanceAStar;

    public CityEdge(int distance, CityVertex source) {
        this.distance = distance;
        this.source = Objects.requireNonNull(source, "source can't be null!");;
        this.distanceAStar = distance + source.getDistance();
    }

    public CityEdge(CityVertex source) {
        this(Integer.MAX_VALUE, source);
    }

    public CityVertex getSource() {
        return source;
    }

    public int getDistance() {
        return distance;
    }

    public int getDistanceAStar() {
        return distanceAStar;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "E{", "}")
                .add("name='" + source.getName() + "'")
                .add("distanceAStar=" + distanceAStar)
                .toString();
    }
}
