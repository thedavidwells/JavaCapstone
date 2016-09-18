/**
 * Created by TheDavid on 9/17/16.
 */

import graph.CapGraph;
import graph.Graph;
import org.junit.*;

import static org.junit.Assert.*;

public class graphTest {


    @Test
    public void testGraph() {

        Graph myGraph = new CapGraph();

        myGraph.addVertex(1);
        myGraph.addVertex(2);
        myGraph.addVertex(3);

        myGraph.addEdge(1,2);
        myGraph.addEdge(2,3);
        myGraph.addEdge(1,3);

        myGraph.exportGraph();

        Assert.assertNotNull(myGraph);
    }


}
