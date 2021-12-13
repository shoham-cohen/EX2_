public class EdgeData implements EdgeData_Interface {
    public int src;
    public double w;
    public int des;

    public EdgeData(int src, double w, int des){
        this.src = src;
        this.w = w;
        this.des = des;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.des;
    }

    @Override
    public double getWeight() {
        return this.w;
    }

    @Override
    public String getInfo() {
        return null;
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
