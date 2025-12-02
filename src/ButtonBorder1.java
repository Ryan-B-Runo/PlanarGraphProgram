import javax.swing.border.Border;
import java.awt.*;

public class ButtonBorder1 implements Border {
    private final int thickness;
    private final int arc;
    private final Color color;

    public ButtonBorder1(int thickness, Color color) {
        this.thickness = thickness;
        this.arc = 5;
        this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(this.color);
        g2d.setStroke(new BasicStroke(this.thickness));

        int inset = thickness / 2;
        g2d.drawRoundRect(x-inset, y-inset, width+this.thickness, height+this.thickness, arc, arc);

        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(this.thickness, this.thickness, this.thickness, this.thickness);
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
