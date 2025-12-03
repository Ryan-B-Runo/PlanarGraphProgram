import processing.core.PVector;
import processing.event.MouseEvent;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import javax.swing.*;
import java.util.Random;

public class PlanarGraph extends Graph {

    private final String[] colors = {"red", "green", "blue", "pink"};

    public PlanarGraph() {
        super();
    }

    public void addRandomVertices(int[] vertexRange, JPanel panel) {
        Random rand = new Random();
        int vertexCount = rand.nextInt(vertexRange[0], vertexRange[1]+1);

        // Set up vertices
        for (int i = 0; i < vertexCount; i++) {
            addVertex(randomPosition(panel));
        }

        // Generate a random planar graph using the package JGraphT
        SimpleGraph<Integer, DefaultEdge> underlying = PlanarGraphGenerator.genPlanarGraph(vertexCount);

        // Translate the generated edges into our custom class
        for (DefaultEdge e : underlying.edgeSet()) {
            edges.add(new Edge(super.vertices.get(underlying.getEdgeSource(e)), super.vertices.get(underlying.getEdgeTarget(e))));
        }
        for(Edge edge : edges) {
            addEdge(edge);
        }
    }

    private PVector randomPosition(JPanel panel){
        int width = panel.getWidth() - 64;
        int height = panel.getHeight() - 64;

        Random random = new Random();
        int randX = random.nextInt(width - 20 + 1) + 20;
        int randY = random.nextInt(height - 20 + 1) + 20;

        while(tooClose(randX, randY)){
            randX = random.nextInt(width - 20 + 1) + 20;
            randY = random.nextInt(height - 20 + 1) + 20;
        }
        return new PVector(randX, randY);
    }

    private boolean tooClose(int x, int y){
        for(Vertex vertex : super.vertices){
            if((x < vertex.getPosition().x + 10 && x > vertex.getPosition().x - 10) || (y < vertex.getPosition().y + 10 && y > vertex.getPosition().y - 10)){
                return true;
            }
        }
        return false;
    }

    public void mouseWheel(MouseEvent event) {// for scrolling through colors
        System.out.println(event.getCount());
    }

}
