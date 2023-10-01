package br.com.guilhermealvessilve.graph.mapsearch;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapSimpleGraph {

    private final Map<String, CityVertex> graph;

    public MapSimpleGraph() {

        var portoUniao = new CityVertex("Porto União", 203);
        var pauloFrontin = new CityVertex("Paulo Frontin", 172);
        var canoinhas = new CityVertex("Canoinhas", 141);
        var irati = new CityVertex("Irati", 139);
        var palmeira = new CityVertex("Palmeira", 59);
        var campoLargo = new CityVertex("Campo Largo", 27);
        var curitiba = new CityVertex("Curitiba", 0);
        var balsaNova = new CityVertex("Balsa Nova", 41);
        var araucaria = new CityVertex("Araucária", 23);
        var saoJose = new CityVertex("São José dos Pinhais", 13);
        var contenda = new CityVertex("Contenda", 39);
        var mafra = new CityVertex("Mafra", 94);
        var tijucas = new CityVertex("Tijucas do Sul", 56);
        var lapa = new CityVertex("Lapa", 74);
        var saoMateus = new CityVertex("São Mateus do Sul", 123);
        var tresBarras = new CityVertex("Três Barras", 131);

        this.graph = new HashMap<>() {{
            put("portoUniao", portoUniao);
            put("pauloFrontin", pauloFrontin);
            put("canoinhas", canoinhas);
            put("irati", irati);
            put("palmeira", palmeira);
            put("campoLargo", campoLargo);
            put("curitiba", curitiba);
            put("balsaNova", balsaNova);
            put("araucaria", araucaria);
            put("saoJose", saoJose);
            put("contenda", contenda);
            put("mafra", mafra);
            put("tijucas", tijucas);
            put("lapa", lapa);
            put("saoMateus", saoMateus);
            put("tresBarras", tresBarras);
        }};

        portoUniao.addEdge(pauloFrontin, 46)
            .addEdge(canoinhas, 78)
            .addEdge(saoMateus, 87);

        pauloFrontin.addEdge(portoUniao, 46)
            .addEdge(irati, 75);

        canoinhas.addEdge(portoUniao, 78)
            .addEdge(tresBarras, 12)
            .addEdge(mafra, 66);

        irati.addEdge(pauloFrontin, 75)
            .addEdge(palmeira, 75)
            .addEdge(saoMateus, 57);

        palmeira.addEdge(irati, 75)
            .addEdge(saoMateus, 77)
            .addEdge(campoLargo, 55);

        campoLargo.addEdge(palmeira, 55)
            .addEdge(balsaNova, 22)
            .addEdge(curitiba, 29);

        curitiba.addEdge(campoLargo, 29)
            .addEdge(balsaNova, 51)
            .addEdge(araucaria, 37)
            .addEdge(saoJose, 15);

        balsaNova.addEdge(curitiba, 51)
            .addEdge(campoLargo, 22)
            .addEdge(contenda, 19);

        araucaria.addEdge(curitiba, 37)
            .addEdge(contenda, 18);

        saoJose.addEdge(curitiba, 15)
            .addEdge(tijucas, 49);

        contenda.addEdge(balsaNova, 19)
            .addEdge(araucaria, 18)
            .addEdge(lapa, 26);

        mafra.addEdge(tijucas, 99)
            .addEdge(lapa, 57)
            .addEdge(canoinhas, 66);

        tijucas.addEdge(mafra, 99)
            .addEdge(saoJose, 49);

        lapa.addEdge(contenda, 26)
            .addEdge(saoMateus, 60)
            .addEdge(mafra, 57);

        saoMateus.addEdge(palmeira, 77)
            .addEdge(irati, 57)
            .addEdge(lapa, 60)
            .addEdge(tresBarras, 43)
            .addEdge(portoUniao, 87);

        tresBarras.addEdge(saoMateus, 43)
            .addEdge(canoinhas, 12);
    }

    public CityVertex getVertex(String name) {
        return graph.get(Objects.requireNonNull(name, "Vertex name can't be null!"));
    }

    public Collection<CityVertex> getCities() {
        return graph.values();
    }
}
