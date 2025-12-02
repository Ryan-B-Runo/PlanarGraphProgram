import processing.core.PVector;

public class Edge {

    private final Vertex start;
    private final Vertex end;

    public Edge(Vertex end, Vertex start) {
        this.end = end;
        this.start = start;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public void setStartX(int n) {
        this.start.setPosition(new PVector(n, this.start.getPosition().y));
    }

    public void setEndX(int n) {
        this.end.setPosition(new PVector(n, this.end.getPosition().y));
    }

    public void setStartY(int n) {
        this.start.setPosition(new PVector(this.start.getPosition().x, n));
    }

    public void setEndY(int n) {
        this.end.setPosition(new PVector(this.end.getPosition().x, n));
    }

    public int getStartX(){
        return (int) this.start.getPosition().x;
    }

    public int getStartY(){
        return (int) this.start.getPosition().y;
    }

    public int getEndX(){
        return (int) this.end.getPosition().x;
    }

    public int getEndY(){
        return (int) this.end.getPosition().y;
    }
}
