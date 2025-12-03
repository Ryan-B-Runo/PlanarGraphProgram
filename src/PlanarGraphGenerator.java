import org.jgrapht.Graph;
import org.jgrapht.generate.GnmRandomGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;
import org.jgrapht.alg.planar.BoyerMyrvoldPlanarityInspector;
import java.util.Random;

public class PlanarGraphGenerator {
    public static void main(String[] args) {
        SimpleGraph<Integer, DefaultEdge> g = genPlanarGraph(5);

        // for (DefaultEdge e : g.edgeSet()) {
        //     System.out.println(g.getEdgeSource(e));
        //     System.out.println(g.getEdgeTarget(e));
        // }

        System.out.println(g);
    }

    public static SimpleGraph<Integer, DefaultEdge> genPlanarGraph(int n) {
        Random rand = new Random();

        int m;

        if (n < 3) {
            m = n - 1;
        }else {
            m = rand.nextInt(n-1, 3*n-5);
        }

        // Set up generation objects
        SimpleGraph<Integer, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
        GnmRandomGraphGenerator<Integer, DefaultEdge> gen = new GnmRandomGraphGenerator<>(n, m);

        // Repeat random generation until a graph is generated.
        while (true) {
            var vertexSupplier = SupplierUtil.createIntegerSupplier();
            var edgeSupplier   = SupplierUtil.createDefaultEdgeSupplier();
            g = new SimpleGraph<>(vertexSupplier, edgeSupplier, false);

            gen.generateGraph(g, null); // Generate the graph

            // Inspector that determines graph planarity.
            BoyerMyrvoldPlanarityInspector<Integer, DefaultEdge> inspector = new BoyerMyrvoldPlanarityInspector<>(g);

            if (inspector.isPlanar()) {
                return g;
            }
        }
    }
}
