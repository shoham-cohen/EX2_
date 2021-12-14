import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test10000Test {
    DirectedWeightedGraphAlgorithms D = new DirectedWeightedGraphAlgorithms();

    public test10000Test(){
        D.load("C:\\inputs\\10000Nodes.json");
    }
    @Test
    void isConnect() {
        boolean connected = D.isConnected();
        assertEquals(connected, true);
    }
    @Test
    void shortestPathDist() {
        double dist = D.shortestPathDist(10,643) ;
        assertEquals(dist, 1821.1682136561835, 0.001);
    }
    @Test
    void shortestPath() {
        List<NodeData> list = D.shortestPath(31,999) ;
        assertEquals(list.get(2).id, 7801);
    }
    @Test
    void center() {
        NodeData center = D.center();
        assertEquals(center.getKey(), 3846);
    }
    @Test
    void tsp() {
        NodeData n1 = D.getGraph().getNode(2);
        NodeData n2 = D.getGraph().getNode(1);
        NodeData n3 = D.getGraph().getNode(4);
        List<NodeData> nodes = new ArrayList<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        List<NodeData> list = D.tsp(nodes);
        assertEquals(list.size(), 19);
    }
    @Test
    void save() {
        boolean save = D.save("C:\\inputs\\10000Nodes.json");
        assertEquals(save, true);
    }

    @Test
    void load() {
        boolean load = D.load("C:\\inputs\\10000Nodes.json");
        assertEquals(load, true);
    }

}
