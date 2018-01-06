package com.algorythm.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstras {

    private final List<Vertex> vertices;
    private final List<Edge> edges;
    private Map<Vertex, Integer> distances;
    private Map<Vertex, Vertex> previous;
    private Set<Vertex> remaining;

    public Dijkstras(Graph graph) {
        vertices = new ArrayList<>(graph.vertices);
        edges = new ArrayList<>(graph.edges);
    }

    public void execute(Vertex source) {
        distances = new HashMap<>();
        previous = new HashMap<>();
        remaining = new HashSet<>();

        distances.put(source, 0);
        remaining.add(source);

        while(remaining.size() > 0) {
            Vertex vertex = getMinimum(remaining);
            updateDistances(vertex);
            remaining.remove(vertex);
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
                previous.put(neighbor, vertex);
                remaining.add(neighbor);
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

    private Vertex getMinimum(Set<Vertex> remaining) {
        Vertex min = null;
        for(Vertex vertex : remaining) {
            if(vertex == null) {
                min = vertex;
            } else if(getShortestDistanceFromSource(vertex) < getShortestDistanceFromSource(min)) {
                min = vertex;
            }
        }
        return min;
    }

    private int getShortestDistanceFromSource(Vertex destination) {
        return distances.getOrDefault(destination, Integer.MAX_VALUE);
    }
}
