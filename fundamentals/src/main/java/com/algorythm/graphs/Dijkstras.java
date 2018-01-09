package com.algorythm.graphs;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstras {

    private class Node {
        public Vertex vertex;
        public int distanceFromSource;

        public Node(Vertex vertex, int distanceFromSource) {
            this.vertex = vertex;
            this.distanceFromSource = distanceFromSource;
        }
    }

    // source vertex -> Map < neighbor vertex, distance from source vertex >
    private Map<Vertex, Map<Vertex, Integer>> graph;
    private PriorityQueue<Node> queue;
    private Map<Vertex, Vertex> previous;
    private Map<Vertex, Integer> distances;


    public Dijkstras(Graph graph) {
        this.graph = new HashMap<>();

        for(Edge edge : graph.edges) {
            Map<Vertex, Integer> neighborDistance = this.graph.getOrDefault(edge.source, new HashMap<>());
            neighborDistance.put(edge.destination, edge.weight);
            this.graph.put(edge.source, neighborDistance);
        }
    }

    public void execute(Vertex source) {
        queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distanceFromSource));
        previous = new HashMap<>();
        distances = new HashMap<>();

        distances.put(source, 0);
        queue.add(new Node(source, 0));

        while(!queue.isEmpty()) {
            Vertex vertex = queue.remove().vertex;
            updateDistances(vertex);
        }
    }

    public List<Vertex> getShortestPath(Vertex destination) {
        List<Vertex> path = new LinkedList<>();
        if(!previous.containsKey(destination)) {
            return null;
        }
        Vertex curr = destination;
        path.add(curr);
        while(previous.get(curr) != null) {
            curr = previous.get(curr);
            path.add(0, curr);
        }
        return path;
    }

    private void updateDistances(Vertex vertex) {
        for(Vertex neighbor : getNeighbors(vertex)) {
            int distanceThroughVertex = getShortestDistanceFromSource(vertex) + getWeight(vertex, neighbor);
            if(getShortestDistanceFromSource(neighbor) > distanceThroughVertex) {
                distances.put(neighbor, distanceThroughVertex);
                queue.add(new Node(neighbor, distanceThroughVertex));
                previous.put(neighbor, vertex);
            }
        }
    }

    private int getWeight(Vertex source, Vertex destination) {
        return graph.get(source).get(destination);
    }

    private Set<Vertex> getNeighbors(Vertex vertex) {
        return graph.containsKey(vertex) ? graph.get(vertex).keySet() : new HashSet<>();
    }

    private int getShortestDistanceFromSource(Vertex destination) {
        return distances.getOrDefault(destination, Integer.MAX_VALUE);
    }

}
