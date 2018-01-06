package com.algorythm.graphs;

import java.util.List;

public class Graph {
    public List<Vertex> vertices;
    public List<Edge> edges;

    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }
}
