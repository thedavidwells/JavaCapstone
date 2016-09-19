package graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * Created by TheDavid on 9/18/16.
 */
public class DepthFirstSearch {


    // Methods implement the psuedo-code from lecture

    public static Stack DepthFirstSearch(CapGraph theGraph,
                                         Stack<Integer> vertices) {

        // Initialize set and stack finished
        Stack<Integer> finished = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();

        while(!vertices.isEmpty()) {
            Integer vertex = vertices.pop();

            if(!visited.contains(vertex)) {
                DepthFirstSearch_Visit(theGraph, vertex, visited, finished);
            }
        }

        return finished;
    }


    public static void DepthFirstSearch_Visit(CapGraph theGraph,
                                              Integer vertex,
                                              HashSet<Integer> visited,
                                              Stack<Integer> finished) {


        visited.add(vertex);

        for(Integer neighbor : theGraph.getGraph().get(vertex)) {

            if(!visited.contains(neighbor)) {
                DepthFirstSearch_Visit(theGraph, neighbor, visited, finished);
            }
        }

        finished.push(vertex);
    }
}
