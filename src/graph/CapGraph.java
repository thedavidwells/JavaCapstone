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


	public HashMap<Integer, HashSet<Integer>> getGraph() {
		return theGraph;
	}


	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {

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

		// 1. DFS(G), keeping track of the order in which vertices finish
		Stack<Integer> vertices = getVertices();

		Stack<Integer> finished = DepthFirstSearch.DepthFirstSearch(this, vertices);

		// 2. Compute the transpose of G
		CapGraph transposeGraph = getTransposeGraph();


		// 3.  DFS(G-transpose), exploring in the reverse order of finish time from step 1.
		// 		Each tree found forms a SCC.
		return findSCC(transposeGraph, finished);

	}


	private List<Graph> findSCC(CapGraph graph, Stack<Integer> vertices) {

		List<Graph> scc = new ArrayList<>();

		// Initialize set and stack finished
		Stack<Integer> finished = new Stack<>();
		HashSet<Integer> visited = new HashSet<>();

		while(!vertices.isEmpty()) {
			Integer vertex = vertices.pop();

			if(!visited.contains(vertex)) {

				// Everytime to do a DFS-Visit, it forms the start of our SCC Tree
				DepthFirstSearch.DepthFirstSearch_Visit(graph, vertex, visited, finished);

				// Then we basically have to go through the neighbors and build the new SCC graph
				CapGraph sccGraph = new CapGraph();

				for(Integer node : finished) {
					sccGraph.addVertex(node);
				}

				// Now go through their friends, adding edges for each node that is connected.
				for(Integer node : finished) {
					HashSet<Integer> moreFriends = theGraph.get(node);

					for (Integer friendNode : moreFriends) {
						if(finished.contains(friendNode))
							sccGraph.addEdge(friendNode, node);

					}
				}
				finished = new Stack<>();

				// We have completed another SCC, so add it to the list
				scc.add(sccGraph);
			}
		}

		return scc;
	}

	// Transpose is just the graph with all of the edges flipped.
	private CapGraph getTransposeGraph() {

		CapGraph transposeGraph = new CapGraph();

		Set<Integer> friends = theGraph.keySet();

		// Go through all of the friends again, and add them to the new graph
		for(Integer node : friends) {
			transposeGraph.addVertex(node);
		}

		// Now go through their friends, adding edges for each node that is connected.
		for(Integer node : friends) {
			HashSet<Integer> moreFriends = theGraph.get(node);

			for (Integer friendNode : moreFriends) {

				// Switch order in which nodes are added, thus switching direction and "transposing".
				transposeGraph.addEdge(friendNode, node);

			}
		}


		return transposeGraph;


	}

	private Stack<Integer> getVertices() {

		Stack<Integer> vertices = new Stack<>();

		for(Integer vertex : theGraph.keySet()) {
			vertices.add(vertex);
		}
		return vertices;
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
