import java.util.HashMap;

public class NodeData implements NodeData_Interface {
    public int id;
    public GeoLocation pos = new GeoLocation();
    public HashMap<String, EdgeData> edg = new HashMap<String, EdgeData>();

    public NodeData(GeoLocation pos, int id, HashMap<String, EdgeData> edg){
        this.pos.x = pos.x;
        this.pos.y = pos.y;
        this.pos.z = pos.z;
        this.id = id;
        this.edg = edg;
    }

    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public GeoLocation getLocation() {
        return this.pos;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.pos.x = p.x;
        this.pos.y = p.y;
        this.pos.z = p.z;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public void setWeight(double w) {

    }

    @Override
    public String getInfo() {
        String s = new String("id = "+Integer.toString(id));
        return s;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }
}
