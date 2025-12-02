import processing.core.PApplet;

public class CursorSquare {
    PApplet p; // Reference to the main sketch

    CursorSquare(PApplet parent) {
        this.p = parent;
    }

    public void draw() {
        // Use 'p.' to access all Processing functions
        p.strokeWeight(2);
        p.stroke(255,255,255);
        p.square(p.mouseX-10, p.mouseY-10, 20);
    }
}