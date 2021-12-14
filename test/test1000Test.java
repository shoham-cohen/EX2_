import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test1000Test {
    DirectedWeightedGraphAlgorithms D = new DirectedWeightedGraphAlgorithms();

    public test1000Test(){
        D.load("C:\\inputs\\1000Nodes.json");
    }

    @Test
    void isConnect() {
        boolean connected = D.isConnected();
        assertEquals(connected, true);
    }
    @Test
    void shortestPathDist() {
        double dist = D.shortestPathDist(10,643) ;
        assertEquals(dist, 997.9959003867181, 0.001);
    }
    @Test
    void shortestPath() {
        List<NodeData> list = D.shortestPath(31,999) ;
        assertEquals(list.get(6).id, 683);
    }
    @Test
    void center() {
        NodeData center = D.center();
        assertEquals(center.getKey(), 362);
    }
    void tsp() {
        NodeData n1 = D.getGraph().getNode(2);
        NodeData n2 = D.getGraph().getNode(1);
        NodeData n3 = D.getGraph().getNode(4);
        List<NodeData> nodes = new ArrayList<>();
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        List<NodeData> list = D.tsp(nodes);
        assertEquals(list.size(), 6);
    }
    @Test
    void save() {
        boolean save = D.save("C:\\inputs\\1000Nodes.json");
        assertEquals(save, true);
    }

    @Test
    void load() {
        boolean load = D.load("C:\\inputs\\1000Nodes.json");
        assertEquals(load, true);
    }

}
