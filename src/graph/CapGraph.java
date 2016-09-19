/**
 * 
 */
package graph;

import java.util.*;

/**
 * @author Your name here.
 *         <p>
 *         For the warm up assignment, you must implement your Graph in a class
 *         named CapGraph.  Here is the stub file.
 */
public class CapGraph implements Graph {

	private HashMap<Integer, HashSet<Integer>> theGraph;

	public CapGraph() {
		theGraph = new HashMap<>();
	}


	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override

	public void addVertex(int num) {
		// TODO Auto-generated method stub

		if(theGraph.containsKey(num)) {
			return;
		}

		theGraph.put(num, new HashSet<>(num));
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		// TODO Auto-generated method stub

		if(!theGraph.containsKey(from) || !theGraph.containsKey(to)) {
			//System.out.println("One of the vertices doesn't exist.");
			throw new RuntimeException("One of the vertices doesn't exist.");
		}

		theGraph.get(from).add(to);

	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {

		// Create a new graph
		Graph egoNetGraph = new CapGraph();

		// If the existing graph doesn't contain the key, return an empty graph.
		if(!theGraph.containsKey(center)) {
			return egoNetGraph;
		}

		// Add the center as the first vertex in the new egonet graph
		egoNetGraph.addVertex(center);

		// Get friends of the center node
		HashSet<Integer> friends = theGraph.get(center);

		// If the center node has no friends, return the graph
		if(friends == null) {
			return egoNetGraph;
		}


		// For every friend, add connected node to new egoNetGraph
		for(Integer node : friends) {
			egoNetGraph.addVertex(node);
			egoNetGraph.addEdge(center, node);
		}

		// Now go through friends again, adding edges for each node that is connected.
		for(Integer node : friends) {
			HashSet<Integer> moreFriends = theGraph.get(node);

			for (Integer friendNode : moreFriends) {
				if(friendNode == center || friends.contains(friendNode)){

					egoNetGraph.addEdge(node, friendNode);
				}
			}
		}


		return egoNetGraph;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub

		if(theGraph == null){
			return null;
		}

		System.out.println("Current Graph:");
		for(Integer node : theGraph.keySet()) {
			for(Integer edge : theGraph.get(node)) {
				System.out.println(node + "-" + edge);
			}
		}

		return theGraph;
	}

}
