import javax.swing.*;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraphAlgorithms D = new DirectedWeightedGraphAlgorithms();
        D.load(json_file);
        DirectedWeightedGraph ans = D.getGraph();
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms D = new DirectedWeightedGraphAlgorithms();
        D.load(json_file);
        DirectedWeightedGraphAlgorithms ans = new DirectedWeightedGraphAlgorithms(D.getGraph());
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);

        JFrame screen = new JFrame("Directed Weighted Graph");
        screen.setSize(1200,600);
        screen.setVisible(true);
        GUI graph = new GUI(alg);
        screen.add(graph);

        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setVisible(true);

    }
}