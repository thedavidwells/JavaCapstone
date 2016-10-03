/*
    Created by David Wells
    Fall 2016
 */

package graph;

import java.awt.*;
import java.awt.geom.*;
import java.util.HashSet;
import java.util.Map;
import javax.swing.*;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.organic.JGraphFastOrganicLayout;
import com.jgraph.layout.organic.JGraphOrganicLayout;
import org.jgraph.*;
import org.jgraph.graph.*;
import org.jgraph.plaf.GraphUI;
import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;
import org.jgrapht.graph.DefaultEdge;
import util.GraphLoader;

/**
 * Graph visualization class using JGraphT library.
 *
 * @author David Wells
 */
public class GraphVisualize
        extends JApplet
{
    private static final long serialVersionUID = 3256444702936019250L;
    private static final Color DEFAULT_BG_COLOR = Color.decode("#FAFBFF");
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);

    //
    private JGraphModelAdapter<Integer, DefaultEdge> jgAdapter;

    /**
     * An alternative starting point for this demo, to also allow running this applet as an
     * application.
     *
     * @param args ignored.
     */
    public static void main(String[] args)
    {
        GraphVisualize applet = new GraphVisualize();
        applet.init();

        JFrame frame = new JFrame();
        frame.getContentPane().add(applet);
        frame.setTitle("Capstone Graph Visualize");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    public void init()
    {
        // create a JGraphT graph
        ListenableGraph<Integer, DefaultEdge> g =
                new ListenableDirectedMultigraph<>(DefaultEdge.class);

        // create a visualization using JGraph, via an adapter
        jgAdapter = new JGraphModelAdapter<>(g);

        JGraph jgraph = new JGraph(jgAdapter);

        adjustDisplaySettings(jgraph);
        getContentPane().add(jgraph);
        resize(DEFAULT_SIZE);


        GraphLoader.loadGraph(g, "data/facebook_1000.txt");

        JGraphFastOrganicLayout layout = new JGraphFastOrganicLayout();
        JGraphFacade facade = new JGraphFacade(jgraph);
        layout.run(facade);

        Map nested = facade.createNestedMap(false, false);
        jgraph.getGraphLayoutCache().edit(nested);

        JScrollPane scrollPane = new JScrollPane(jgraph);
        this.add(scrollPane);

/*



   // this a a JGraphT graph
    ListenableDirectedGraph<TableColumn, DefaultEdge> dependencyGraph = getDependencyGraph();

    JGraphModelAdapter adapter = new JGraphModelAdapter(dependencyGraph);

    JGraph jgraph = new JGraph(adapter);


    JGraphLayout layout = new JGraphHierarchicalLayout(); // or whatever layouting algorithm
    JGraphFacade facade = new JGraphFacade(jgraph);
    layout.run(facade);
    Map nested = facade.createNestedMap(false, false);
    jgraph.getGraphLayoutCache().edit(nested);

    JScrollPane sp = new JScrollPane(jgraph);
    this.add(sp);





        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        // add some sample data (graph manipulated via JGraphT)
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v1);
        g.addEdge(v4, v3);

        // position vertices nicely within JGraph component
        positionVertexAt(v1, 130, 40);
        positionVertexAt(v2, 60, 200);
        positionVertexAt(v3, 310, 230);
        positionVertexAt(v4, 380, 70);
*/
        // that's all there is to it!...
    }

    private void adjustDisplaySettings(JGraph jg)
    {
        jg.setPreferredSize(DEFAULT_SIZE);
        jg.setScale(0.25);
        jg.setAutoResizeGraph(true);



        Color c = DEFAULT_BG_COLOR;
        String colorStr = null;

        try {
            colorStr = getParameter("bgcolor");
        } catch (Exception e) {
        }

        if (colorStr != null) {
            c = Color.decode(colorStr);
        }

        jg.setBackground(c);
    }

    @SuppressWarnings("unchecked") // FIXME hb 28-nov-05: See FIXME below
    private void positionVertexAt(Object vertex, int x, int y)
    {
        DefaultGraphCell cell = jgAdapter.getVertexCell(vertex);
        AttributeMap attr = cell.getAttributes();
        Rectangle2D bounds = GraphConstants.getBounds(attr);

        Rectangle2D newBounds = new Rectangle2D.Double(x, y, bounds.getWidth(), bounds.getHeight());

        GraphConstants.setBounds(attr, newBounds);

        // TODO: Clean up generics once JGraph goes generic
        AttributeMap cellAttr = new AttributeMap();
        cellAttr.put(cell, attr);
        jgAdapter.edit(cellAttr, null, null, null);
    }

    /**
     * a listenable directed multigraph that allows loops and parallel edges.
     */
    private static class ListenableDirectedMultigraph<V, E>
            extends DefaultListenableGraph<V, E>
            implements DirectedGraph<V, E>
    {
        private static final long serialVersionUID = 1L;

        ListenableDirectedMultigraph(Class<E> edgeClass)
        {
            super(new DirectedMultigraph<>(edgeClass));
        }
    }
}

// End GraphVisualize.java
