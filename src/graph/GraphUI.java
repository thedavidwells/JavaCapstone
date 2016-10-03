package graph;

import java.awt.Dimension;
import javax.swing.JFrame;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationImageServer;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.Viewer;
import util.GraphLoader;


/**
 * Graph visualization class using JUNG library.
 *
 * @author David Wells
 */
public class GraphUI {

    public static void main(String[] args){

                DirectedSparseMultigraph graph = new DirectedSparseMultigraph();
                GraphLoader.loadGraph(graph, "data/facebook_1000.txt");
                VisualizationImageServer vs =
                        new VisualizationImageServer(
                                new ISOMLayout(graph), new Dimension(800, 800));

                JFrame frame = new JFrame();
                frame.getContentPane().add(vs);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);






    }
}


//    Graph graph = new MultiGraph("Bazinga!");
//        // Populate the graph.
//        GraphLoader.loadGraph(graph, "data/facebook_1000.txt");
//        Viewer viewer = graph.display();
//        // Let the layout work ...
//        viewer.disableAutoLayout();
//
//        // Do some work ...
//        viewer.enableAutoLayout();