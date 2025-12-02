import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button1 extends JButton {

    private Border standardBorder, clickedBorder;

    private int mTop;
    private int mBottom;
    private int mLeft;
    private int mRight;
    private final int thickness;

    public Button1(String text) {
        super(text);

        mTop = 5;
        mBottom = 30;
        mLeft = 10;
        mRight = 10;
        thickness = 2;

        standardBorder = BorderFactory.createCompoundBorder(new EmptyBorder(mTop, mLeft, mBottom, mRight), new ButtonBorder1(thickness, Color.WHITE));
        clickedBorder = BorderFactory.createCompoundBorder(new EmptyBorder(mTop, mLeft, mBottom, mRight), new ButtonBorder1(thickness, Color.LIGHT_GRAY));

        setForeground(Color.white);
        setBackground(null);
        setFont(new Font("Courier New", Font.BOLD, 16));
        setBorder(standardBorder);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setAlignmentX(CENTER_ALIGNMENT);
        setAlignmentY(CENTER_ALIGNMENT);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(isEnabled()){
                    setBorder(clickedBorder);
                }
            }
            public void mouseReleased(MouseEvent e) {
                if(isEnabled()){
                    if(getBounds().contains(e.getPoint())){
                        setBorder(standardBorder);
                    }else{
                        setBorder(standardBorder);
                    }
                }
            }
        });
    }

    public void setMargin(int mTop, int mBottom, int mLeft, int mRight){
        this.mTop = mTop;
        this.mBottom = mBottom;
        this.mLeft = mLeft;
        this.mRight = mRight;
        standardBorder = BorderFactory.createCompoundBorder(new EmptyBorder(mTop, mLeft, mBottom, mRight), new ButtonBorder1(thickness, Color.WHITE));
        clickedBorder = BorderFactory.createCompoundBorder(new EmptyBorder(mTop, mLeft, mBottom, mRight), new ButtonBorder1(thickness, Color.LIGHT_GRAY));
    }
}
