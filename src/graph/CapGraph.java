/**
 * 
 */
package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
			throw new RuntimeException("One of the vertices doesn't exist.");
		}

		HashSet<Integer> fromNode = theGraph.get(from);
		HashSet<Integer> toNode = theGraph.get(to);

		fromNode.add(to);
		toNode.add(from);
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		// TODO Auto-generated method stub
		return null;
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
