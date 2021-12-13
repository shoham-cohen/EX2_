import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DirectedWeightedGraph implements DirectedWeightedGraph_Interface {

    public HashMap<Integer, NodeData> nodes;
    public int NumOfEdge;
    public int MC;

    public DirectedWeightedGraph(HashMap<Integer, NodeData> nodes, int NumOfEdges, int MC){
        this.nodes = nodes;
        this.NumOfEdge = NumOfEdges;
        this.MC = MC;
    }

    @Override
    public NodeData getNode(int key) {
        if(this.nodes.containsKey(key)) {
            return this.nodes.get(key);
        }
        return null;
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        String s = Integer.toString(src);
        String a = Integer.toString(dest);
        String coma = ",";
        StringBuilder sb = new StringBuilder(s);
        sb.append(coma);
        sb.append(a);
        String res = sb.toString();
        if (this.nodes.get(src).edg.containsKey(res)) {
            return this.nodes.get(src).edg.get(res);
        }
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        this.nodes.put(n.id, n);
        this.MC++;
    }

    @Override
    public void connect(int src, int dest, double w) throws Exception {
        if(this.nodes.containsKey(src)&&this.nodes.containsKey(dest)) {
            if (w > 0 && src != dest) {
                EdgeData first = new EdgeData(src, w, dest);
                EdgeData second = new EdgeData(dest, w, src);
                String s = Integer.toString(src);
                String a = Integer.toString(dest);
                String coma = ",";
                StringBuilder firstsb = new StringBuilder(s);
                StringBuilder secondsb = new StringBuilder(a);
                firstsb.append(coma);
                secondsb.append(coma);
                firstsb.append(a);
                secondsb.append(s);
                String firstS = firstsb.toString();
                String secondS = secondsb.toString();
                this.nodes.get(src).edg.put(firstS, first);
                this.nodes.get(dest).edg.put(secondS, second);
                NumOfEdge++;
                this.MC++;
            }
        }
        else {
            throw new Exception("Node doesnt exists");
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return new Iterator<NodeData>() {
            Iterator<NodeData> iterator = nodes.values().iterator();
            int currentMC = MC;

            @Override
            public NodeData next() {
                if (iterator.hasNext() && currentMC == MC) {
                    return iterator.next();
                }
                throw new ConcurrentModificationException();
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }
        };
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return new Iterator<EdgeData>() {
            Iterator<NodeData> Nodeiterator = nodeIter();
            int currentMc = MC;
            Iterator<EdgeData> EdgeIterator = Nodeiterator.next().edg.values().iterator();

            @Override
            public EdgeData next() {
                if (EdgeIterator.hasNext() && currentMc == MC){
                    return EdgeIterator.next();
                }
                else if (Nodeiterator.hasNext() && currentMc == MC){
                    while (Nodeiterator.hasNext()) {
                        NodeData node = Nodeiterator.next();
                        EdgeIterator = node.edg.values().iterator();
                        if (EdgeIterator.hasNext()) {
                            return EdgeIterator.next();
                        }
                    }
                    throw new ConcurrentModificationException();
                }
                    throw new ConcurrentModificationException();
            }

            @Override
            public boolean hasNext() {
                if (EdgeIterator != null) {
                    if (currentMc != MC) {
                        throw new ConcurrentModificationException();
                    }
                    if (EdgeIterator.hasNext()) {
                        return true;
                    }
                    if (Nodeiterator.hasNext()) {
                        while (Nodeiterator.hasNext()) {
                            NodeData node = Nodeiterator.next();
                            EdgeIterator = node.edg.values().iterator();
                            if (EdgeIterator.hasNext()) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        };
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return new Iterator<EdgeData>() {
            Iterator <EdgeData> EdgeIterator = nodes.get(node_id).edg.values().iterator();
            int currMc = MC;

            @Override
            public EdgeData next() {
                if (currMc != MC){
                    throw new ConcurrentModificationException();
                }
                return EdgeIterator.next();
            }

            @Override
            public boolean hasNext() {
                if (currMc != MC){
                    throw new ConcurrentModificationException();
                }
                return EdgeIterator.hasNext();
            }
        };
    }

    @Override
    public NodeData removeNode(int key) throws Exception {
        if (this.nodes.containsKey(key)) {
            Iterator<Map.Entry<String, EdgeData>> it = this.nodes.get(key).edg.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, EdgeData> entry = it.next();
                int d = entry.getValue().des;
                String a = Integer.toString(d);
                String coma = ",";
                String b = Integer.toString(key);
                String res = a + coma + b;
                this.nodes.get(d).edg.remove(res);
                NumOfEdge = NumOfEdge - 2;
            }
            NodeData res = this.nodes.get(key);
            this.nodes.remove(key);
            this.MC++;
            return res;
        }
        throw new Exception("Node doesnt exists");
    }

    @Override
    public EdgeData removeEdge(int src, int dest) throws Exception {
        String s = Integer.toString(src);
        String d = Integer.toString(dest);
        String coma = ",";
        String SrcToDes = s+coma+d;
        String DesToSrc = d+coma+s;
        EdgeData res = this.nodes.get(src).edg.get(SrcToDes);
        if (this.nodes.get(src).edg.containsKey(SrcToDes)) {
            this.nodes.get(src).edg.remove(SrcToDes);
            this.nodes.get(dest).edg.remove(DesToSrc);
            this.NumOfEdge--;
            this.MC++;
            return res;
        }
        throw new Exception("Edge doesnt exists");
    }

    @Override
    public int nodeSize() {
        return this.nodes.size();
    }

    @Override
    public int edgeSize() {
        return this.NumOfEdge;
    }

    @Override
    public int getMC() {
        return this.MC;
    }
}
