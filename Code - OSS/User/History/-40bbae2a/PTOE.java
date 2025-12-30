package mountain;

import java.nio.channels.Pipe;
import fractal.*;
import mountain.RandomUtilities;

public class Mountain extends Fractal {

    private Point p1;
    private Point p2;
    private Point p3;
    private double alpha, beta, gama, d1, d2, d3;

    
	public Mountain(Point p1, Point p2, Point p3, int dev) {
		super();
		//this.dev = dev;
        
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;

                


        alpha = getAngle(p1, p2);
        beta = getAngle(p2, p3);
        gama = getAngle(p3, p1);
        

        d1 = getDistans(p1, p2);
        d2 = getDistans(p2, p3);
        d3 = getDistans(p3, p1);
	}



    @Override
	public String getTitle() {
		return "Mountain";
	}

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
    



    @Override
	public void draw(TurtleGraphics turtle) {
		
		fractalMount(turtle, order,2, 0, p1, p1, p2, p3, 2);
	
	}

    private Point midPoint (Point p1, Point p2, double dev) {
        int mx = (p1.getX() + p2.getX()) / 2;
        int my = (int)(2*RandomUtilities.randFunc(dev)) + (p1.getY() + p2.getY()) / 2;
        return new Point(mx, my);
    }

    


    private void fractalMount(TurtleGraphics turtle, int order, int divader, int reverse, Point start_point, Point a_Point, Point b_Point, Point c_Point, double dev) {

        if (order == 0) {
            turtle.moveTo(start_point.getX(),start_point.getY());

            turtle.penDown();
            turtle.forwardTo(b_Point.getX(), b_Point.getY());

            turtle.forwardTo(c_Point.getX(), c_Point.getY());

            turtle.forwardTo(a_Point.getX(), a_Point.getY());
            /*
            turtle.setDirection(alpha+reverse);
			turtle.forward(d1/divader);
            
            turtle.setDirection(beta+reverse);
            turtle.forward(d2/divader);
           
            turtle.setDirection(gama+reverse);
            turtle.forward(d3/divader);
            */
            
            
		} else {
            dev = 
			fractalMount(turtle, order-1, divader*2, reverse,  start_point, a_Point, midPoint(a_Point, b_Point, dev), midPoint(a_Point, c_Point, dev), dev/2);

            
            fractalMount(turtle, order-1, divader*2, reverse, midPoint(a_Point, b_Point, dev), midPoint(a_Point, b_Point, dev), b_Point, midPoint(b_Point, c_Point, dev), dev/2);


            fractalMount(turtle, order-1, divader*2, reverse+ 180,  midPoint(b_Point, c_Point, dev), midPoint(b_Point, c_Point, dev), midPoint(a_Point, b_Point, dev), midPoint(a_Point, c_Point, dev), dev/2);


            fractalMount(turtle, order-1, divader*2, reverse, midPoint(a_Point, c_Point, dev),  midPoint(a_Point, c_Point, dev), midPoint(b_Point, c_Point, dev), c_Point, dev/2);
            
            
            

           
		}
	}





}
