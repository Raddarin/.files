package mountain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.nio.channels.Pipe;
import fractal.*;
import mountain.RandomUtilities;
import mountain.Point;

public class Mountain extends Fractal {

    private Point p1;
    private Point p2;
    private Point p3;
    private double dev;
    HashMap<Side, Point> voide_clenser = new HashMap<>();
    //private double alpha, beta, gama, d1, d2, d3;

    
	public Mountain(Point p1, Point p2, Point p3, int dev) {
		super();
		this.dev = dev;
        
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

                
        
        /* 
        alpha = getAngle(p1, p2);
        beta = getAngle(p2, p3);
        gama = getAngle(p3, p1);
        

        d1 = getDistans(p1, p2);
        d2 = getDistans(p2, p3);
        d3 = getDistans(p3, p1);
        */
	}



    @Override
	public String getTitle() {
		return "Mountain";
	}

    /*
    private double getAngle(Point p_here, Point p_there) {
        double dx = p_there.getX() - p_here.getX();
        double dy = p_here.getY() - p_there.getY(); // OBS: här är det p_here - p_there!

        return Math.toDegrees(Math.atan2(dy, dx));
    }
      



    private double getDistans(Point p_heare, Point p_thare) {
        double dx = p_thare.getX() - p_heare.getX();
        double dy = p_thare.getY() - p_heare.getY();
            

        return 2* Math.sqrt(dx*dx + dy*dy);
    }
    */



    @Override
	public void draw(TurtleGraphics turtle) {
		
		fractalMount(turtle, order,2, 0, p1, p1, p2, p3, dev);
	
	}

    private Point midPoint (Point p1, Point p2, int maount_val) {
        
        
        for ( var entry : voide_clenser.entrySet()) {
            Side side = entry.getKey();
            

            if (side.is_it_this_side(p1, p2)){
                
                //voide_clenser.remove(side);
                return entry.getValue();
            }
        }
        
        int mx = (p1.getX() + p2.getX()) / 2;
        int my = (maount_val) + (p1.getY() + p2.getY()) / 2;

        Side side = new Side(p1, p2);
        Point mid_p = new Point(mx, my);
        voide_clenser.put(side, mid_p);
        return new Point(mx, my);
    }

    


    private void fractalMount(TurtleGraphics turtle, int order, int divader, int reverse, Point start_point, Point a_Point, Point b_Point, Point c_Point, double dev) {

        if (order == 0) {
            turtle.moveTo(start_point.getX(),start_point.getY());

            turtle.penDown();
            turtle.forwardTo(b_Point.getX(), b_Point.getY());

            turtle.forwardTo(c_Point.getX(), c_Point.getY());

            turtle.forwardTo(a_Point.getX(), a_Point.getY());
            
            
            
		} else {
            int mount_val = (int)(7*mountain.RandomUtilities.randFunc(dev)); 
			fractalMount(turtle, order-1, divader*2, reverse,  start_point, a_Point, midPoint(a_Point, b_Point, mount_val), midPoint(a_Point, c_Point, mount_val), dev/2);

            
            fractalMount(turtle, order-1, divader*2, reverse, midPoint(a_Point, b_Point, mount_val), midPoint(a_Point, b_Point, mount_val), b_Point, midPoint(b_Point, c_Point, mount_val), dev/2);


            fractalMount(turtle, order-1, divader*2, reverse+ 180,  midPoint(b_Point, c_Point, mount_val), midPoint(b_Point, c_Point, mount_val), midPoint(a_Point, b_Point, mount_val), midPoint(a_Point, c_Point, mount_val), dev/2);


            fractalMount(turtle, order-1, divader*2, reverse, midPoint(a_Point, c_Point, mount_val),  midPoint(a_Point, c_Point, mount_val), midPoint(b_Point, c_Point, mount_val), c_Point, dev/2);
            
            //voide_clenser.remove(side);
            

           
		}
	}

    



}


