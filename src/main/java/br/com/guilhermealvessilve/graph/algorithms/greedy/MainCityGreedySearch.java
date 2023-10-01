package br.com.guilhermealvessilve.graph.algorithms.greedy;

import br.com.guilhermealvessilve.graph.mapsearch.MapSimpleGraph;

public class MainCityGreedySearch {

    public static void main(String[] args) {
        var graph = new MapSimpleGraph();
        var portoUniao = graph.getVertex("portoUniao");
        var curitiba = graph.getVertex("curitiba");

        if (CityGreedySearch.search(portoUniao, curitiba)) System.out.println("Found portoUniao -> curitiba");
        else System.out.println("Found portoUniao -> curitiba");
    }
}
