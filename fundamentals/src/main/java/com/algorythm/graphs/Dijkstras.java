package com.algorythm.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstras {


    private class Node {
        public Vertex vertex;
        public int distanceFromSource;

        public Node(Vertex vertex, int distanceFromSource) {
            this.vertex = vertex;
            this.distanceFromSource = distanceFromSource;
        }
    }

    private final List<Vertex> vertices;
    private final List<Edge> edges;
    private PriorityQueue<Node> queue;
    private Map<Vertex, Vertex> previous;
    private Map<Vertex, Integer> distances;

    public Dijkstras(Graph graph) {
        vertices = new ArrayList<>(graph.vertices);
        edges = new ArrayList<>(graph.edges);
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
        List<Vertex> neighbors = getNeighbors(vertex);
        for(Vertex neighbor : neighbors) {
            int distanceThroughVertex = getShortestDistanceFromSource(vertex) + getWeight(vertex, neighbor);
            if(getShortestDistanceFromSource(neighbor) > distanceThroughVertex) {
                distances.put(neighbor, distanceThroughVertex);
                queue.add(new Node(neighbor, distanceThroughVertex));
                previous.put(neighbor, vertex);
            }
        }
    }

    private int getWeight(Vertex source, Vertex destination) {
        for(Edge edge : edges) {
            if(edge.source.equals(source) && edge.destination.equals(destination)) {
                return edge.weight;
            }
        }
        throw new RuntimeException("Edge not found: Should not happen!");
    }

    private List<Vertex> getNeighbors(Vertex vertex) {
        List<Vertex> neighbors = new ArrayList<>();
        for(Edge edge : edges) {
            if(edge.source.equals(vertex)) {
                neighbors.add(edge.destination);
            }
        }
        return neighbors;
    }

    private int getShortestDistanceFromSource(Vertex destination) {
        return distances.getOrDefault(destination, Integer.MAX_VALUE);
    }

}
