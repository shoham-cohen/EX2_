import org.junit.jupiter.api.Test;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class DirectedWeightedGraphAlgorithmsTest {
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
    void isConnected() {
        SetGraph();
        DirectedWeightedGraph G = new DirectedWeightedGraph(this.nodes,5,0);
        DirectedWeightedGraphAlgorithms D = new DirectedWeightedGraphAlgorithms(G);//not connected Graph
        DirectedWeightedGraph G2 = new DirectedWeightedGraph(this.nodes2,3,0);
        DirectedWeightedGraphAlgorithms D2 = new DirectedWeightedGraphAlgorithms(G2);//not connected Graph
        assertFalse(D.isConnected());
        assertTrue(D2.isConnected());
    }

    @Test
    void shortestPathDist() {
        SetGraph();
        DirectedWeightedGraph G = new DirectedWeightedGraph(this.nodes,5,0);
        DirectedWeightedGraphAlgorithms D = new DirectedWeightedGraphAlgorithms(G);//not connected Graph

        DirectedWeightedGraph G2 = new DirectedWeightedGraph(this.nodes2,3,0);
        DirectedWeightedGraphAlgorithms D2 = new DirectedWeightedGraphAlgorithms(G2);//connected Graph
        //not connected graph:
        //from 0 to 2 result expected: 2
        assertEquals(3.1,D.shortestPathDist(0,2));

        //from 0 to 1 result expected: 0-1
        assertEquals(-1.0,D.shortestPathDist(0,1));

        //connected graph:
        //from 0 to 1 result expected: 1.3
        assertEquals(1.3,D2.shortestPathDist(0,1));


        //from 0 to 0 result expected: 0
        assertEquals(0.0,D2.shortestPathDist(0,0));

    }

    @Test
    void shortestPath() {
        SetGraph();
        DirectedWeightedGraph G = new DirectedWeightedGraph(this.nodes,5,0);
        DirectedWeightedGraphAlgorithms D = new DirectedWeightedGraphAlgorithms(G);//not connected Graph

        DirectedWeightedGraph G2 = new DirectedWeightedGraph(this.nodes2,3,0);
        DirectedWeightedGraphAlgorithms D2 = new DirectedWeightedGraphAlgorithms(G2);//connected Graph
        //connected graph:
        //from 0 to 2 result expected: 0-1-2
        assertEquals(0,D2.shortestPath(0,2).get(0).id);
        assertEquals(1,D2.shortestPath(0,2).get(1).id);
        assertEquals(2,D2.shortestPath(0,2).get(2).id);
        //from 0 to 1 result expected: 0-1
        assertEquals(0,D2.shortestPath(0,1).get(0).id);
        assertEquals(1,D2.shortestPath(0,1).get(1).id);
        //not connected graph:
        //from 0 to 5 result expected: 0-5
        assertEquals(0,D.shortestPath(0,5).get(0).id);
        assertEquals(5,D.shortestPath(0,5).get(1).id);

        //from 0 to 0 result expected: null
        assertNull(D.shortestPath(0,0));

        //from 0 to 2 result expected: 0-5-2
        assertEquals(0,D.shortestPath(0,2).get(0).id);
        assertEquals(5,D.shortestPath(0,2).get(1).id);
        assertEquals(2,D.shortestPath(0,2).get(2).id);
    }



    @Test
    void center() {
        SetGraph();
        DirectedWeightedGraph G = new DirectedWeightedGraph(this.nodes,5,0);
        DirectedWeightedGraphAlgorithms D = new DirectedWeightedGraphAlgorithms(G);//not connected Graph

        DirectedWeightedGraph G2 = new DirectedWeightedGraph(this.nodes2,3,0);
        DirectedWeightedGraphAlgorithms D2 = new DirectedWeightedGraphAlgorithms(G2);//connected Graph

        assertNull(D.center());
        assertEquals(2,D2.center().id);
    }

    @Test
    void tsp() {
        SetGraph();
        DirectedWeightedGraph G = new DirectedWeightedGraph(this.nodes,5,0);
        DirectedWeightedGraphAlgorithms D = new DirectedWeightedGraphAlgorithms(G);//not connected Graph

        DirectedWeightedGraph G2 = new DirectedWeightedGraph(this.nodes2,3,0);
        DirectedWeightedGraphAlgorithms D2 = new DirectedWeightedGraphAlgorithms(G2);//connected Graph
        List<NodeData> list = new LinkedList<NodeData>();
        list.add(D.graph.nodes.get(5));
        list.add(D.graph.nodes.get(2));
        assertEquals(D.graph.nodes.get(5),D.tsp(list).get(0));
        assertEquals(D.graph.nodes.get(2),D.tsp(list).get(1));
        List<NodeData> list2 = new LinkedList<NodeData>();
        list2.add(D.graph.nodes.get(5));
        list2.add(D.graph.nodes.get(2));
        list2.add(D.graph.nodes.get(0));
        assertEquals(D.graph.nodes.get(5),D.tsp(list2).get(0));
        assertEquals(D.graph.nodes.get(2),D.tsp(list2).get(1));
        assertEquals(D.graph.nodes.get(0),D.tsp(list2).get(2));
        List<NodeData> list3 = new LinkedList<NodeData>();
        List<NodeData> list4 = new LinkedList<NodeData>();
        list4.add(D.graph.nodes.get(5));
        list4.add(D.graph.nodes.get(2));
        list4.add(D.graph.nodes.get(0));
        list4.add(D.graph.nodes.get(1));
        assertNull(D.tsp(list4));

    }

}