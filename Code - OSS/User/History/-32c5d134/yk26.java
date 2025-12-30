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

    /* 
    public boolean is_it_this_side(Point test_p_1, Point test_p_2) {
        if (test_p_1.equals(test_p_2)) {
            return false;
        }

        return end_points.contains(test_p_1) && end_points.contains(test_p_2);
    }
    */
}
