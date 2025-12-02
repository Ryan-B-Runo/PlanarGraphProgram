import processing.core.PVector;
import processing.event.MouseEvent;

import javax.swing.*;
import java.util.Random;

public class PlanarGraph extends Graph {

    private final String[] colors = {"red", "green", "blue", "pink"};

    public PlanarGraph() {
        super();
    }

    public void addRandomVertices(int maxVertices, JPanel panel) {
        Random random = new Random();
        int toRemove;
        int maxRemove;
        if(maxVertices >= 3) {//all cases contain trivial case K_3
            for(int i = 0; i < 3; i++) {
                addVertex(randomPosition(panel));
            }
            edges.add(new Edge(super.vertices.get(0), super.vertices.get(1)));
            edges.add(new Edge(super.vertices.get(0), super.vertices.get(2)));
            edges.add(new Edge(super.vertices.get(1), super.vertices.get(2)));

            maxRemove = 2;

            if(maxVertices >= 7){
                for(int i = 0; i < 4; i++) {//4 new super.vertices make 7
                    addVertex(randomPosition(panel));
                }
                edges.add(new Edge(super.vertices.get(3), super.vertices.get(0)));
                edges.add(new Edge(super.vertices.get(3), super.vertices.get(1)));
                edges.add(new Edge(super.vertices.get(3), super.vertices.get(2)));

                edges.add(new Edge(super.vertices.get(4), super.vertices.get(0)));
                edges.add(new Edge(super.vertices.get(4), super.vertices.get(3)));
                edges.add(new Edge(super.vertices.get(4), super.vertices.get(1)));

                edges.add(new Edge(super.vertices.get(5), super.vertices.get(3)));
                edges.add(new Edge(super.vertices.get(5), super.vertices.get(2)));
                edges.add(new Edge(super.vertices.get(5), super.vertices.get(1)));

                edges.add(new Edge(super.vertices.get(6), super.vertices.get(0)));
                edges.add(new Edge(super.vertices.get(6), super.vertices.get(2)));
                edges.add(new Edge(super.vertices.get(6), super.vertices.get(3)));

                maxRemove = 3;

                if(maxVertices >= 16){
                    for(int i = 0; i < 9; i++) {
                        addVertex(randomPosition(panel));
                    }

                    edges.add(new Edge(super.vertices.get(7), super.vertices.get(0)));
                    edges.add(new Edge(super.vertices.get(7), super.vertices.get(3)));
                    edges.add(new Edge(super.vertices.get(7), super.vertices.get(6)));

                    edges.add(new Edge(super.vertices.get(8), super.vertices.get(0)));
                    edges.add(new Edge(super.vertices.get(8), super.vertices.get(3)));
                    edges.add(new Edge(super.vertices.get(8), super.vertices.get(4)));

                    edges.add(new Edge(super.vertices.get(9), super.vertices.get(0)));
                    edges.add(new Edge(super.vertices.get(9), super.vertices.get(1)));
                    edges.add(new Edge(super.vertices.get(9), super.vertices.get(4)));

                    edges.add(new Edge(super.vertices.get(10), super.vertices.get(1)));
                    edges.add(new Edge(super.vertices.get(10), super.vertices.get(3)));
                    edges.add(new Edge(super.vertices.get(10), super.vertices.get(4)));

                    edges.add(new Edge(super.vertices.get(11), super.vertices.get(1)));
                    edges.add(new Edge(super.vertices.get(11), super.vertices.get(3)));
                    edges.add(new Edge(super.vertices.get(11), super.vertices.get(5)));

                    edges.add(new Edge(super.vertices.get(12), super.vertices.get(1)));
                    edges.add(new Edge(super.vertices.get(12), super.vertices.get(2)));
                    edges.add(new Edge(super.vertices.get(12), super.vertices.get(5)));

                    edges.add(new Edge(super.vertices.get(13), super.vertices.get(2)));
                    edges.add(new Edge(super.vertices.get(13), super.vertices.get(3)));
                    edges.add(new Edge(super.vertices.get(13), super.vertices.get(5)));

                    edges.add(new Edge(super.vertices.get(14), super.vertices.get(2)));
                    edges.add(new Edge(super.vertices.get(14), super.vertices.get(3)));
                    edges.add(new Edge(super.vertices.get(14), super.vertices.get(6)));

                    edges.add(new Edge(super.vertices.get(15), super.vertices.get(0)));
                    edges.add(new Edge(super.vertices.get(15), super.vertices.get(2)));
                    edges.add(new Edge(super.vertices.get(15), super.vertices.get(6)));

                    maxRemove = 8;
                }
            }
            for(int i = 0; i < (random.nextInt(maxRemove + 1)); i++){// remove between 0 and 2 vertices
                toRemove = random.nextInt(super.vertices.size());
                Vertex vertexToRemove = super.vertices.get(toRemove);
                edges.removeIf(edge -> (edge.getStart() == vertexToRemove || edge.getEnd() == vertexToRemove));
                super.vertices.remove(toRemove);
            }
            for(Edge edge : edges) {
                addEdge(edge);
            }
        }
        System.out.println(vertices.size());
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
