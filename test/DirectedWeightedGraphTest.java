import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphTest {
    HashMap<Integer, NodeData> nodes = new HashMap<Integer, NodeData>();//not connected graph
    HashMap<Integer, NodeData> nodes2 = new HashMap<Integer, NodeData>();//connected graph
    void SetGraph(){
        HashMap<String, EdgeData> N0 = new HashMap<String, EdgeData>();
        HashMap<String, EdgeData> N1 = new HashMap<String, EdgeData>();
        HashMap<String, EdgeData> N2 = new HashMap<String, EdgeData>();
        HashMap<String, EdgeData> N3 = new HashMap<String, EdgeData>();
        HashMap<String, EdgeData> N4 = new HashMap<String, EdgeData>();
        HashMap<String, EdgeData> N5 = new HashMap<String, EdgeData>();
        EdgeData e20 = new EdgeData(2,1.3,0);
        EdgeData e05 = new EdgeData(0,1.6,5);
        EdgeData e52 = new EdgeData(5,1.5,2);
        EdgeData e14 = new EdgeData(1,1.6,4);
        EdgeData e13 = new EdgeData(1,1.4,3);
        N0.put("2,0",e20);
        N0.put("0,5",e05);
        N1.put("1,3",e13);
        N1.put("1,4",e14);
        N2.put("2,0",e20);
        N2.put("5,2", e52);
        N3.put("1,3",e13);
        N4.put("1,4",e14);
        N5.put("5,2",e52);
        N5.put("0,5",e05);
        GeoLocation G =new GeoLocation(35.20319591121872,32.10318254621849,0.0);
        NodeData N00 = new NodeData(G,0,N0);
        NodeData N11 = new NodeData(G,1,N1);
        NodeData N22 = new NodeData(G,2,N2);
        NodeData N33 = new NodeData(G,3,N3);
        NodeData N44 = new NodeData(G,4,N4);
        NodeData N55 = new NodeData(G,5,N5);
        this.nodes.put(0,N00);
        this.nodes.put(1,N11);
        this.nodes.put(2,N22);
        this.nodes.put(3,N33);
        this.nodes.put(4,N44);
        this.nodes.put(5,N55);
        //another Graph (connected)
        HashMap<String, EdgeData> n0 = new HashMap<String, EdgeData>();
        HashMap<String, EdgeData> n1 = new HashMap<String, EdgeData>();
        HashMap<String, EdgeData> n2 = new HashMap<String, EdgeData>();
        EdgeData e01 = new EdgeData(0,1.3,1);
        EdgeData e12 = new EdgeData(1,1.6,2);
        EdgeData e200 = new EdgeData(2,1.5,0);
        n0.put("0,1",e01);
        n0.put("2,0",e200);
        n1.put("0,1",e01);
        n1.put("1,2",e12);
        n2.put("1,2",e12);
        n2.put("2,0",e200);
        NodeData n00 = new NodeData(G,0,n0);
        NodeData n11 = new NodeData(G,1,n1);
        NodeData n22 = new NodeData(G,2,n2);
        this.nodes2.put(0,n00);
        this.nodes2.put(1,n11);
        this.nodes2.put(2,n22);
    }
    @Test
    void getNode() {
        SetGraph();
        DirectedWeightedGraph G = new DirectedWeightedGraph(this.nodes,5,0);//connected Graph
        DirectedWeightedGraph G2 = new DirectedWeightedGraph(this.nodes2,3,0);//not connected Graph
        assertEquals(this.nodes.get(0).id,G.getNode(0).id);
        assertEquals(this.nodes.get(1).id,G.getNode(1).id);
        assertEquals(this.nodes.get(2).id,G.getNode(2).id);
        assertEquals(this.nodes.get(3).id,G.getNode(3).id);
        assertEquals(this.nodes.get(4).id,G.getNode(4).id);
        assertEquals(this.nodes.get(5).id,G.getNode(5).id);
        assertNull(G2.getNode(6));

        assertEquals(this.nodes.get(0).id,G2.getNode(0).id);
        assertEquals(this.nodes.get(1).id,G2.getNode(1).id);
        assertEquals(this.nodes.get(2).id,G2.getNode(2).id);
        assertNull(G2.getNode(5));
    }

    @Test
    void getEdge() {
        SetGraph();
        DirectedWeightedGraph G = new DirectedWeightedGraph(this.nodes,5,0);//connected Graph
        DirectedWeightedGraph G2 = new DirectedWeightedGraph(this.nodes2,3,0);//not connected Graph
        assertNull(G.getEdge(0,3));
        assertNull(G.getEdge(4,3));
        assertNull(G.getEdge(2,4));
        assertEquals(this.nodes.get(0).edg.get("0,5"),G.getEdge(0,5));
        assertEquals(this.nodes.get(0).edg.get("2,0"),G.getEdge(2,0));


        assertEquals(this.nodes2.get(0).edg.get("0,1"),G2.getEdge(0,1));
        assertEquals(this.nodes2.get(0).edg.get("2,0"),G2.getEdge(2,0));
    }


    @Test
    void nodeSize() {
        SetGraph();
        DirectedWeightedGraph G = new DirectedWeightedGraph(this.nodes,5,0);//connected Graph
        DirectedWeightedGraph G2 = new DirectedWeightedGraph(this.nodes2,3,0);//not connected Graph
        assertEquals(6,G.nodeSize());
        assertEquals(3,G2.nodeSize());
    }

    @Test
    void edgeSize() {
        SetGraph();
        DirectedWeightedGraph G = new DirectedWeightedGraph(this.nodes,5,0);//connected Graph
        DirectedWeightedGraph G2 = new DirectedWeightedGraph(this.nodes2,3,0);//not connected Graph
        assertEquals(5,G.edgeSize());
        assertEquals(3,G2.edgeSize());
    }

    @Test
    void getMC() {
        SetGraph();
        DirectedWeightedGraph G = new DirectedWeightedGraph(this.nodes,5,0);//connected Graph
        DirectedWeightedGraph G2 = new DirectedWeightedGraph(this.nodes2,3,0);//not connected Graph
        assertEquals(0,G.getMC());
        assertEquals(0,G2.getMC());
    }
}