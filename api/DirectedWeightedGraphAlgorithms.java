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

public class DirectedWeightedGraphAlgorithms implements DirectedWeightedGraphAlgorithms_Interface {

    public DirectedWeightedGraph graph;

    public DirectedWeightedGraphAlgorithms(DirectedWeightedGraph graph){
        this.graph = graph;
    }
    public DirectedWeightedGraphAlgorithms(){
        this.graph = null;
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph co = new DirectedWeightedGraph(this.graph.nodes, this.graph.NumOfEdge, this.graph.MC);
        return co;
    }

    @Override
    public boolean isConnected() {
        Iterator<Map.Entry<Integer, NodeData>> SlowIterator = this.graph.nodes.entrySet().iterator();
        Iterator<Map.Entry<String, EdgeData>> FastIterator;
        Map.Entry<Integer, NodeData> entry = SlowIterator.next();
        NodeData SourceNode = entry.getValue();
        while (SlowIterator.hasNext()) {
            boolean[] visited = new boolean[this.graph.nodes.size()];
            visited[SourceNode.id] = true;
            boolean flag = false;
            NodeData currdes = SlowIterator.next().getValue();
            LinkedList<NodeData> queue = new LinkedList<NodeData>();
            queue.add(SourceNode);
            while (queue.size() != 0 && !flag) {
                NodeData s = queue.poll();
                int nei;
                FastIterator = s.edg.entrySet().iterator();
                while (FastIterator.hasNext() && !flag) {
                    nei = FastIterator.next().getValue().des;
                    if (nei == currdes.id) {
                        flag = true;
                    }
                    if (!visited[nei]) {
                        visited[nei] = true;
                        queue.add(this.graph.nodes.get(nei));
                    }
                }
            }
            if (!flag) {
                return false;
            }
        }
        Iterator<Map.Entry<Integer, NodeData>> Slow2Iterator = this.graph.nodes.entrySet().iterator();
        Iterator<Map.Entry<String, EdgeData>> Fast2Iterator;
        Map.Entry<Integer, NodeData> entry2 = Slow2Iterator.next();
        NodeData Source2Node = entry2.getValue();
        while (Slow2Iterator.hasNext()) {
            boolean[] visited = new boolean[this.graph.nodes.size()];
            visited[Source2Node.id] = true;
            boolean flag = false;
            NodeData currdes = Slow2Iterator.next().getValue();
            LinkedList<NodeData> queue = new LinkedList<NodeData>();
            queue.add(Source2Node);
            while (queue.size() != 0 && !flag) {
                NodeData s = queue.poll();
                int nei;
                Fast2Iterator = s.edg.entrySet().iterator();
                while (Fast2Iterator.hasNext() && !flag) {
                    nei = Fast2Iterator.next().getValue().src;
                    if (nei == currdes.id) {
                        flag = true;
                    }
                    if (!visited[nei]) {
                        visited[nei] = true;
                        queue.add(this.graph.nodes.get(nei));
                    }
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
        }


    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest){
            return 0;
        }
        int finishedNodes = 0;
        int INFINITY = 1000000;
        int[] prev = new int[this.graph.nodes.size()];
        double[] distance = new double[this.graph.nodes.size()];
        for (int i = 0; i < distance.length; i++){
            distance[i] = INFINITY;
            prev[i] = -1;
        }
        distance[src] = 0;
        prev[src] = 0;
        boolean[] visited = new boolean[this.graph.nodes.size()];
        Iterator<Map.Entry<String, EdgeData>> srcEdgIt = this.graph.nodes.get(src).edg.entrySet().iterator();
        while(srcEdgIt.hasNext()){
            EdgeData currEdge = srcEdgIt.next().getValue();
            if (currEdge.src == src){
                if (currEdge.w < distance[currEdge.des]){
                    distance[currEdge.des] = currEdge.w;
                    prev[currEdge.des] = currEdge.src;
                }
            }
        }
        visited[src] = true;
        finishedNodes++;
        while (finishedNodes != this.graph.nodes.size()){
            double min = INFINITY;
            int res = 0;
            for (int i = 0; i < distance.length; i++){
                if (distance[i] < min && visited[i] == false){
                    min = distance[i];
                    res = i;
                }
            }
            Iterator<Map.Entry<String, EdgeData>> currEdgIt = this.graph.nodes.get(res).edg.entrySet().iterator();
            while (currEdgIt.hasNext()){
                EdgeData curr = currEdgIt.next().getValue();
                if (curr.src == res){
                    if (distance[curr.src] + curr.w < distance[curr.des]){
                        distance[curr.des] = distance[curr.src] + curr.w;
                        prev[curr.des] = curr.src;
                    }
                }
            }
            visited[res] = true;
            finishedNodes++;
        }
        if (distance[dest] == 1000000.0){
            return -1;
        }
        return distance[dest];
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if(src == dest){
            return null;
        }
        int finishedNodes = 0;
        int INFINITY = 1000000;
        int[] prev = new int[this.graph.nodes.size()];
        double[] distance = new double[this.graph.nodes.size()];
        for (int i = 0; i < distance.length; i++){
            distance[i] = INFINITY;
            prev[i] = -1;
        }
        distance[src] = 0;
        prev[src] = 0;
        boolean[] visited = new boolean[this.graph.nodes.size()];
        Iterator<Map.Entry<String, EdgeData>> srcEdgIt = this.graph.nodes.get(src).edg.entrySet().iterator();
        while(srcEdgIt.hasNext()){
            EdgeData currEdge = srcEdgIt.next().getValue();
            if (currEdge.src == src){
                if (currEdge.w < distance[currEdge.des]){
                    distance[currEdge.des] = currEdge.w;
                    prev[currEdge.des] = currEdge.src;
                }
            }
        }
        visited[src] = true;
        finishedNodes++;
        while (finishedNodes != this.graph.nodes.size()){
            double min = INFINITY;
            int res = 0;
            for (int i = 0; i < distance.length; i++){
                if (distance[i] < min && visited[i] == false){
                    min = distance[i];
                    res = i;
                }
            }
            Iterator<Map.Entry<String, EdgeData>> currEdgIt = this.graph.nodes.get(res).edg.entrySet().iterator();
            while (currEdgIt.hasNext()){
                EdgeData curr = currEdgIt.next().getValue();
                if (curr.src == res){
                    if (distance[curr.src] + curr.w < distance[curr.des]){
                        distance[curr.des] = distance[curr.src] + curr.w;
                        prev[curr.des] = curr.src;
                    }
                }
            }
            visited[res] = true;
            finishedNodes++;
        }
        int[] arr = new int[this.graph.nodes.size()];
        arr[0] = dest;
        int i = dest;
        int index = 1;
        while(i != src){
            if(i == -1){
                return null;
            }
            arr[index] = prev[i];
            i = arr[index];
            index++;
        }
        List<NodeData> list = new LinkedList<NodeData>();
        index--;
        while(index != -1){
            list.add(this.graph.nodes.get(arr[index]));
            index--;
        }
        return list;
    }

    @Override
    public double[] distToAllNodes(int src){
        int finishedNodes = 0;
        int INFINITY = 1000000;
        int[] prev = new int[this.graph.nodes.size()];
        double[] distance = new double[this.graph.nodes.size()];
        for (int i = 0; i < distance.length; i++){
            distance[i] = INFINITY;
            prev[i] = -1;
        }
        distance[src] = 0;
        prev[src] = 0;
        boolean[] visited = new boolean[this.graph.nodes.size()];
        Iterator<Map.Entry<String, EdgeData>> srcEdgIt = this.graph.nodes.get(src).edg.entrySet().iterator();
        while(srcEdgIt.hasNext()){
            EdgeData currEdge = srcEdgIt.next().getValue();
            if (currEdge.src == src){
                if (currEdge.w < distance[currEdge.des]){
                    distance[currEdge.des] = currEdge.w;
                    prev[currEdge.des] = currEdge.src;
                }
            }
        }
        visited[src] = true;
        finishedNodes++;
        while (finishedNodes != this.graph.nodes.size()){
            double min = INFINITY;
            int res = 0;
            for (int i = 0; i < distance.length; i++){
                if (distance[i] < min && visited[i] == false){
                    min = distance[i];
                    res = i;
                }
            }
            Iterator<Map.Entry<String, EdgeData>> currEdgIt = this.graph.nodes.get(res).edg.entrySet().iterator();
            while (currEdgIt.hasNext()){
                EdgeData curr = currEdgIt.next().getValue();
                if (curr.src == res){
                    if (distance[curr.src] + curr.w < distance[curr.des]){
                        distance[curr.des] = distance[curr.src] + curr.w;
                        prev[curr.des] = curr.src;
                    }
                }
            }
            visited[res] = true;
            finishedNodes++;
        }
        return distance;
    }

    @Override
    public NodeData center() {
        if(!this.isConnected()){
            return null;
        }
        Iterator<Map.Entry<Integer, NodeData>> SlowIterator = this.graph.nodes.entrySet().iterator();
        double resmax = 100000;
        int resId = -1;
        while (SlowIterator.hasNext()){
            double currmax = 0;
            NodeData curr = SlowIterator.next().getValue();
            double[] arr = this.distToAllNodes(curr.id);
            for (int i = 0; i < arr.length; i++){
                if (arr[i] > currmax){
                    currmax = arr[i];
                }
            }
            if (currmax < resmax){
                resmax = currmax;
                resId = curr.id;
            }
        }
        return this.graph.nodes.get(resId);
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
            List<NodeData> resultList = new ArrayList<>();
            List<List<NodeData>> HelperList = new ArrayList<>();
            List<NodeData> temp;
            for (int i = 0; i < cities.size() - 1; i++) {
                int mod = (i+1)%cities.size();
                temp = this.shortestPath(cities.get(i).getKey(), cities.get(mod).getKey());
                if (temp == null) {
                    return null;
                }
                HelperList.add(temp);
            }
        for (int j = 0; j < HelperList.get(0).size(); j++) {
            resultList.add(HelperList.get(0).get(j));
        }
            for (int i = 1; i < HelperList.size(); i++) {
                for (int j = 1; j < HelperList.get(i).size(); j++) {
                    resultList.add(HelperList.get(i).get(j));
                    }
                }
            return resultList;
        }


    @Override
    public boolean save(String file){
        JSONObject jo = new JSONObject();
        JSONArray ja = new JSONArray();
        JSONArray ja2 = new JSONArray();

        for (Map.Entry<Integer, NodeData> entry : this.graph.nodes.entrySet()) {
            for (Map.Entry<String, EdgeData> entry2 : entry.getValue().edg.entrySet()) {
                Map m = new LinkedHashMap(3);
                m.put("src",entry2.getValue().src);
                m.put("w",entry2.getValue().w);
                m.put("dest",entry2.getValue().des);

                ja.add(m);

                jo.put("Edges", ja);
            }
        }
        for (Map.Entry<Integer, NodeData> entry : this.graph.nodes.entrySet()) {
            Map m = new LinkedHashMap(2);
            m.put("pos",entry.getValue().pos.x +"," + entry.getValue().pos.y +","+ entry.getValue().pos.z);
            m.put("id",entry.getValue().id);

            ja2.add(m);

            jo.put("Nodes", ja2);

        }


        try (PrintWriter pw = new PrintWriter(file)){
            pw.write(jo.toJSONString());

            pw.flush();
            pw.close();
            return true;
        }
        catch (FileNotFoundException e){
            return false;
        }
    }


    @Override
    public boolean load(String file) {
        try (FileReader reader = new FileReader(file))
        {
            // parsing file "JSONExample.json"
            Object obj = new JSONParser().parse(reader);

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            JSONArray ja2 = (JSONArray) jo.get("Nodes");
            JSONArray ja1 = (JSONArray) jo.get("Edges");

            HashMap<Integer, NodeData> nodes = new HashMap<Integer, NodeData>();

            for (int i = 0; i < ja2.size(); i++) {

                // store each object in JSONObject
                JSONObject explrObject = (JSONObject) ja2.get(i);
                GeoLocation location = new GeoLocation((String) explrObject.get("pos"));
                HashMap<String, EdgeData> edgedata = new HashMap<String, EdgeData>();
                NodeData node = new NodeData(location,(int)(long) explrObject.get("id"),edgedata);
                nodes.put((int)(long)explrObject.get("id"),node);
                // get field value from JSONObject using get() method
            }
            int counter = 0;
            for (int i = 0; i < ja1.size(); i++) {
                counter++;
                JSONObject explrObject = (JSONObject) ja1.get(i);
                String st = Integer.toString((int)(long)explrObject.get("src"));
                st+=",";
                st+=explrObject.get("dest");
                if(explrObject.get("src")!=explrObject.get("dest")){
                    nodes.get((int)(long)explrObject.get("src")).edg.put(st,new EdgeData((int)(long)explrObject.get("src"),(double)explrObject.get("w"),(int)(long)explrObject.get("dest")));
                    nodes.get((int)(long)explrObject.get("dest")).edg.put(st,new EdgeData((int)(long)explrObject.get("src"),(double)explrObject.get("w"),(int)(long)explrObject.get("dest")));
                }
                else
                    nodes.get((int)(long)explrObject.get("src")).edg.put(st,new EdgeData((int)(long)explrObject.get("src"),(double)explrObject.get("w"),(int)(long)explrObject.get("dest")));
            }
            DirectedWeightedGraph D = new DirectedWeightedGraph(nodes,counter,0);
            this.graph = D;
            return true;

        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
}
