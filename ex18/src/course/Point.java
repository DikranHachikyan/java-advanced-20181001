package course;

public class Point {
    private int x;
    private int y;
    
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString(){
        return "(" + x + "," + y + ")";
    }
    
    @Override
    public boolean equals(Object ob){
        if( ob != null && ob instanceof Point ){
            Point t = (Point)ob;
            return t.x == x && t.y == y;
        }
        return false;
    }
}
