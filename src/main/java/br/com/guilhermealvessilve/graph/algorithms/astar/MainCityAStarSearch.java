package br.com.guilhermealvessilve.graph.algorithms.astar;

import br.com.guilhermealvessilve.graph.mapsearch.MapSimpleGraph;

public class MainCityAStarSearch {

    public static void main(String[] args) {
        var graph = new MapSimpleGraph();
        var portoUniao = graph.getVertex("portoUniao");
        var curitiba = graph.getVertex("curitiba");

        if (CityAStarSearch.search(portoUniao, curitiba)) System.out.println("Found portoUniao -> curitiba");
        else System.out.println("Not found portoUniao -> curitiba");
    }
}
