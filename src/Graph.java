import processing.core.PApplet;
import processing.core.PVector;
import processing.event.MouseEvent;

import java.util.concurrent.CopyOnWriteArrayList;

public class Graph extends PApplet {

    CursorSquare cursorSquare;
    CopyOnWriteArrayList<Vertex> vertices = new CopyOnWriteArrayList<>();// CopyOnWriteArrayList prevents Concurrent Modification exceptions
    CopyOnWriteArrayList<Edge> edges =  new CopyOnWriteArrayList<>();

    Vertex selectedVertex = null;
    Edge selectedEdge = null;

    @Override
    public void settings() {
        size(800, 600);
    }

    @Override
    public void setup() {
        cursorSquare = new CursorSquare(this);
    }

    @Override
    public void draw() {
        background(43,45,48);
        stroke(255,255,255);
        noFill();
        cursorSquare.draw();
        for(Vertex vertex : vertices) {
            fill(255,255,255);
            circle(vertex.getPosition().x, vertex.getPosition().y, 10);
        }
        for(Edge edge : edges) {
            line(edge.getStartX(), edge.getStartY(), edge.getEndX(), edge.getEndY());
        }
    }

    public void mousePressed() {
        for(Vertex vertex : vertices) {
            if((mouseX < vertex.getPosition().x + 10) && (mouseX > vertex.getPosition().x - 10) && (mouseY < vertex.getPosition().y + 10) && (mouseY > vertex.getPosition().y - 10) && (selectedVertex == null || selectedVertex == vertex)){// mouse is approximately clicking the vertex
                selectedVertex = vertex;
                vertex.getPosition().x = mouseX;
                vertex.getPosition().y = mouseY;
            }
        }
        for(Edge edge : edges) {
            selectedEdge = edge;
            if(((mouseX < edge.getStartX() + 10) && (mouseX > edge.getStartX() - 10) && (mouseY < edge.getStartY() + 10) && (mouseY > edge.getStartY() - 10)) && (selectedEdge == null || selectedEdge == edge)){
                edge.setStartX(mouseX);
                edge.setStartY(mouseY);
            }else if((mouseX < edge.getEndX() + 10) && (mouseX > edge.getEndX() - 10) && (mouseY < edge.getEndY() + 10) && (mouseY > edge.getEndY() - 10) && (selectedEdge == null || selectedEdge == edge)){
                edge.setEndX(mouseX);
                edge.setEndY(mouseY);
            }
        }
    }

    public void mouseDragged(MouseEvent e) {// move vertices by click and drag
        if(selectedVertex != null && selectedEdge != null) {
            selectedVertex.getPosition().x = mouseX;
            selectedVertex.getPosition().y = mouseY;
            if (((mouseX < selectedEdge.getStartX() + 10) && (mouseX > selectedEdge.getStartX() - 10) && (mouseY < selectedEdge.getStartY() + 10) && (mouseY > selectedEdge.getStartY() - 10))) {
                assert selectedEdge != null;
                selectedEdge.setStartX(mouseX);
                selectedEdge.setStartY(mouseY);
            } else if ((mouseX < selectedEdge.getEndX() + 10) && (mouseX > selectedEdge.getEndX() - 10) && (mouseY < selectedEdge.getEndY() + 10) && (mouseY > selectedEdge.getEndY() - 10)) {
                assert selectedEdge != null;
                selectedEdge.setEndX(mouseX);
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
        selectedVertex = null;
        selectedEdge = null;
    }

    public void addVertex(PVector pos) {
        vertices.add(new Vertex(pos));
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public CopyOnWriteArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void reset(){
        vertices.clear();
        edges.clear();
    }

}