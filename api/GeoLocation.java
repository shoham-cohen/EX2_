public class GeoLocation {
    public double x;
    public double y;
    public double z;

    public GeoLocation(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public GeoLocation(String location){
        String[] arr = location.split(",");
        String x =  arr[0];
        String y =  arr[1];
        String z =  arr[2];
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.z = Double.parseDouble(z);
    }

    public GeoLocation() {

    }

    public double distance(GeoLocation g){
       double dis = Math.sqrt((g.x - this.x) * (g.x - this.x) + (g.y - this.y) * (g.y - this.y));
       return dis;
    }
}