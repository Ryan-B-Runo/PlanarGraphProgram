import javax.swing.*;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

public class Driver {

    public static void main(String[] args) {
        MainForm mainForm = new MainForm();
        mainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainForm.pack();
        mainForm.setVisible(true);
    }

}