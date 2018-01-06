package com.algorythm.graphs;

public class Edge {
    public String id;
    public Vertex source;
    public Vertex destination;
    public int weight;

    public Edge(String id, Vertex source, Vertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}
