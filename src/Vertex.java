import processing.core.PVector;

public class Vertex {
    private PVector position;

    public Vertex(PVector position) {
        this.position = position;
    }

    public PVector getPosition() {
        return position;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }
}
