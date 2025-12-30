import javax.management.MBeanAttributeInfo;

import fractal.Fractal;
import fractal.FractalView;
import koch.Koch;
import mountain.Mountain;
import mountain.Point;

public class FractalApplication {
	static Point p1 = new Point(300, 100);
	static Point p2 = new Point(150, 400);
	static Point p3 = new Point(400, 380);
	public static void main(String[] args) {
		Fractal[] fractals = new Fractal[2];
		fractals[1] = new Koch(100);
		fractals[0] = new Mountain(p1, p2, p3, 4  );
		new FractalView(fractals, "Fraktaler", 600, 600);
		
	}

}