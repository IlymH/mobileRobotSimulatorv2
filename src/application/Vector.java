package application;

public class Vector {
	
	
	public double x;
	public double y;
	public double angle;
	public Vector(){
		
	}
public Vector(double x, double y){
	this.x = x;	
	this.y = y;	
	
	}

	public double toCartX(double magnitude, double angle) {
		return magnitude*Math.cos(angle/360*2*Math.PI);
	}
	public double toCartY(double magnitude, double angle) {
		
		return magnitude*Math.sin(angle/360*2*Math.PI);
		}
	public   double getDist(double x1,double y1,double x2,double y2){
		return 0;
	}
	
	public   double getMag(double x,double y){


		
		return Math.sqrt(x*x+y*y);
	}
	public   void  add(Vector v){
		this.x =x+ v.x;
		this.y =x+ v.y;
	}
	
	public void scale(double scale){
		
		x=x*scale;
		y=y*scale;
		
	}
	
public Vector getForce(Vector vector){
	double mag =vector.getMag();
	
	
	double sqrt = 1/(mag*mag);	
		//double angle = vector.getAngle();
	
	
	return new Vector(sqrt*vector.x/vector.getMag(), sqrt*vector.y/vector.getMag());
		
	}
	
public double getAngle() {
	
	return getAngle(x,y);
}

public double getAngle(double x, double y) {
	// TODO Auto-generated method stub
	
	return Math.atan2(y, x);
}
double  getMag() {
	// TODO Auto-generated method stub

	return getMag(x, y);
}
@Override
public String toString() {
	// TODO Auto-generated method stub

	return new String(x+"  "+y);
}
public Vector getForce() {
	// TODO Auto-generated method stub
	
	return getForce(this);
}
public void nullIt() {
	// TODO Auto-generated method stub
this.x =0;
this.y =0;
}
}
