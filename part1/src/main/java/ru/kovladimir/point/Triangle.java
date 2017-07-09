package ru.kovladimir.point;

public class Triangle {
	public Point a;
	public Point b;
	public Point c;
	private double aToB;
	private	double aToC;
	private double bToC;

   public Triangle(Point a, Point b, Point c) {
      this.a = a;
      this.b = b;
      this.c = c;
	  aToB = a.distanceTo(b);
	  aToC = a.distanceTo(c);
	  bToC = b.distanceTo(c);
   }
   
   public static void main(String[] args) {
	   Triangle triangle = new Triangle(new Point(1, 1), 
			new Point(-2, 4), new Point(-2, -2));
		System.out.println("Area = " + triangle.area());
		double max = Calculation.max(Calculation.max(triangle.aToB, triangle.aToC), triangle.bToC);
		System.out.println("Max side is :" + max);
   }

   public double area() {
      //calculate the triangle area
	  if ((aToB + aToC > bToC) &&
			(aToB + bToC > aToC) &&
			(bToC + aToC > aToB)) {
				double semiperimeter = (aToB + aToC + bToC) / 2;
				return Math.sqrt(semiperimeter * 
					(semiperimeter - aToB) * 
					(semiperimeter - aToC) * 
					(semiperimeter - bToC));
			}
      return -1;
   }
}