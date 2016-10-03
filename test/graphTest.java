///**
// * Created by TheDavid on 9/17/16.
// */
//
//import graph.CapGraph;
//import graph.Graph;
//import org.junit.*;
//import util.GraphLoader;
//
//import static org.junit.Assert.*;
//@Ignore
//public class graphTest {
//
//
//    @Test
//    public void testInformationFlow() {
//
//
//
//
//        Graph socialNetwork = new CapGraph();
//
//        GraphLoader.loadGraph(socialNetwork, "data/facebook_ucsd.txt");
//
//
//        socialNetwork.exportGraph();
//
//        Assert.assertNotNull(socialNetwork);
//
//
//    }
//
//
//
//    @Test
//    public void testGraph() {
//
//        Graph myGraph = new CapGraph();
//
//        myGraph.addVertex(1);
//        myGraph.addVertex(2);
//        myGraph.addVertex(3);
//
//        myGraph.addEdge(1,2);
//        myGraph.addEdge(2,3);
//        myGraph.addEdge(1,3);
//
//        myGraph.exportGraph();
//
//        Assert.assertNotNull(myGraph);
//    }
//
//
//    @Test
//    public void testGraphLoaderSmallTestGraph() {
//
//        Graph loaderGraph = new CapGraph();
//
//        GraphLoader.loadGraph(loaderGraph, "data/small_test_graph.txt");
//
//        Assert.assertNotNull(loaderGraph);
//    }
//
//
//    @Test
//    public void testGraphLoaderFacebook() {
//
//        Graph loaderGraph = new CapGraph();
//
//        GraphLoader.loadGraph(loaderGraph, "data/facebook_1000.txt");
//
//        loaderGraph.exportGraph();
//
//        Assert.assertNotNull(loaderGraph);
//    }
//
//
//}
