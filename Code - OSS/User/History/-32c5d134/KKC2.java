package mountain;

import java.util.ArrayList;
import java.util.List;
import mountain.Point;

public class Side {
    //private List<Point> end_points = new ArrayList<>();
    public Point a;
    public Point b;
    public Side(Point a, Point b){
        this.a = a;
        this.b = b;
    }


     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Side)) return false;

        Side other = (Side) obj;

        
        return (a.equals(other.a) && b.equals(other.b)) ||
               (a.equals(other.b) && b.equals(other.a));
    }


    @Override
    public int hashCode() {
        
        return a.hashCode() + b.hashCode();
        
    }
    /* 
    public boolean is_it_this_side(Point test_p_1, Point test_p_2) {
        if (test_p_1.equals(test_p_2)) {
            return false;
        }

        return end_points.contains(test_p_1) && end_points.contains(test_p_2);
    }
    */
}
