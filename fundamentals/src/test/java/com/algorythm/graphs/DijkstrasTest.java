package com.algorythm.graphs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstrasTest {
    private static List<Vertex> vertices;
    private static List<Edge> edges;
    private static Graph graph;
    private static List<Vertex> expectedShortestPath;

    private Dijkstras dijkstras;

    @BeforeClass
    public static void createGraph() {
        vertices = getVertices();
        edges = getEdges();
        graph = new Graph(vertices, edges);
        expectedShortestPath = new ArrayList<>();
        expectedShortestPath.add(vertices.get(0));
        expectedShortestPath.add(vertices.get(2));
        expectedShortestPath.add(vertices.get(3));
        expectedShortestPath.add(vertices.get(6));
        expectedShortestPath.add(vertices.get(8));
    }

    @Test
    public void findsShortestPath() {
        dijkstras = new Dijkstras(graph);
        dijkstras.execute(vertices.get(0));
        assertThat(dijkstras.getShortestPath(vertices.get(8)), is(expectedShortestPath));
    }

    private static List<Vertex> getVertices() {
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            vertices.add(new Vertex(Integer.toString(i), String.format("Vertex_%s", i)));
        }
        return vertices;
    }

    private static List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        edges.add(getEdge("0", 0, 1, 5));
        edges.add(getEdge("1", 0, 2, 3));
        edges.add(getEdge("2", 0, 4, 2));

        edges.add(getEdge("3", 1, 3, 2));

        edges.add(getEdge("4", 2, 1, 1));
        edges.add(getEdge("5", 2, 3, 1));

        edges.add(getEdge("6", 3, 0, 1));
        edges.add(getEdge("7", 3, 6, 2));
        edges.add(getEdge("8", 3, 7, 1));

        edges.add(getEdge("9", 4, 0, 1));
        edges.add(getEdge("10", 4, 7, 4));
        edges.add(getEdge("11", 4, 8, 7));

        edges.add(getEdge("12", 5, 1, 3));
        edges.add(getEdge("13", 5, 6, 1));

        edges.add(getEdge("14", 6, 2, 3));
        edges.add(getEdge("15", 6, 8, 2));

        edges.add(getEdge("17", 7, 2, 2));
        edges.add(getEdge("18", 7, 5, 2));
        edges.add(getEdge("19", 7, 6, 2));

        return edges;
    }

    private static Edge getEdge(String edgeId, int source, int destination, int weight) {
        return new Edge(edgeId, vertices.get(source), vertices.get(destination), weight);
    }
}