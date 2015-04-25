package application;


import java.util.ArrayList;
import java.util.List;



public abstract class scenaroManager {

public	List<object> obstacles = new ArrayList<>();
private double envWith;
protected Vector v;
private double envHeight;
protected robot robot;
protected int FPS=30;
	
	public scenaroManager(double With, double Height) {
	// TODO Auto-generated constructor stub
		this.envWith=With;
		this.envHeight=Height;
		
        v = new Vector();
	
	
        
        
	         }
		
protected double getMinLeftDistance(){
	double dist=1000000,Tdist=0,x = 0,y = 0;
	for(int i =0; i<obstacles.size();i++){
		if( ObstacleInRange(i)){
			
		y = robot.getCenterY()-v.toCartY(robot.getRadius(), 180-50 -robot.getRotation());
			 x = robot.getCenterX()+v.toCartX(robot.getRadius(),180- 50 -robot.getRotation());
			
			 Tdist = v.getMag(obstacles.get(i).getCenterX()-x,
			         obstacles.get(i).getCenterY()-y);	
			 if(Tdist<dist){
			dist= Tdist-obstacles.get(i).getRadius();
			
			
		}
		}
		}
	if(dist<0 )dist=0;
	robot.distL = dist;
	return dist;
	}

protected double getMinRightDistance(){
	double dist=1000000,Tdist=0,y=0,x=0;
	for(int i =0; i<obstacles.size();i++){
		if(ObstacleInRange(i)){
		y =robot.getCenterY()-v.toCartY(robot.getRadius(), 45 -robot.getRotation());
		x = robot.getCenterX()+v.toCartX(robot.getRadius(), 45 -robot.getRotation());
Tdist = v.getMag(obstacles.get(i).getCenterX()-x,
		         obstacles.get(i).getCenterY()-y);
		if(Tdist<dist ){
			
			dist=  Tdist-obstacles.get(i).getRadius();
			
		}
		}
		}
	
	if(dist<0 )dist=0;
	robot.distR = dist;
	return dist;
	}	

protected boolean ObstacleInRange(int index){
	
	double  ang = v.getAngle(robot.getCenterX()-obstacles.get(index).getCenterX(), robot.getCenterY()-obstacles.get(index).getCenterY());

	double ro = robot.getRotation()-(-(180-ang*360/2/Math.PI -90 ));
	if (( ro % 360 < 50  || ro % 360 > 310) && (fromPoint(index)<150))
	//System.out.println("hurray");
	
	
	
	
	return true;
	return false;
}
protected int targetInRange(){
	
	double  ang = v.getAngle(robot.getCenterX()-getXTarget(),
			robot.getCenterY()-getYTarget());

	double ro = robot.getRotation()-(-(180-ang*360/2/Math.PI -90 ));
	if (( ro % 360 < 20  || ro % 360 > 340))
	{
	
	
	
	
	return 100;
	}
	else{ 
	return -100;
	}
}


public double getSpeed(){
		return robot.getSpeed();
		}
protected  void move(){
		robot.setTranslateX(robot.getTranslateX()-v.toCartX(robot.getSpeed(),-robot.getRotation()-90));
		robot.setTranslateY(robot.getTranslateY()+v.toCartY(robot.getSpeed(),-robot.getRotation()-90));
		}	
protected  void brake(){
robot.setSpeed(0);;
}
protected void rotate(double rotation) {

	robot.rotate(rotation);
}
public double  getvX(){
	return robot.vX;
	}
public double  getvY(){
	return robot.vX;
	}
public double  getRotation(){
	return robot.getRotation();
	}

public void setSpeed(double speed){
robot.setSpeed(speed);
}

public void setRotation(double rotation){
	robot.setRotation(rotation);
	}
public void setRotation(double x, double y){
	setRotation(-(Math.atan2(robot.getCenterX() -x ,robot.getCenterY() -y))*360/(2*Math.PI));
	}

public boolean checWallX() {
	boolean bool = getMaxX(robot)>envWith || getMinX(robot)<0 ;
	//if(bool)handleWallXCollision();
	return (bool);
}
protected void handleWallYCollision() {
	// TODO Auto-generated method stub
	setRotation(180-getRotation());
	setSpeed(getSpeed()*0.867);
}
protected void handleWallXCollision() {
	// TODO Auto-generated method stub
	setRotation(-getRotation());
	setSpeed(getSpeed()*0.867);
}

protected void invet() {
	// TODO Auto-generated method stub
	robot.invet();
}

public boolean checWallY() {
	boolean bool = getMaxY(robot)>envHeight || getMinY(robot)<0 ;
	//if(bool)handleWallYCollision();
	return bool;
}
public double getMaxX(object obj){
	
 return   obj.getBoundsInParent().getMaxX();

	
}
public double getMinX(object obj){
	return  obj.getBoundsInParent().getMinX();
		}
public double getMaxY(object obj){
	return   obj.getBoundsInParent().getMaxY();
	}
public double getMinY(object obj){
	return   obj.getBoundsInParent().getMinY();
	}
public void add(object r){
	obstacles.add(r);
		}
public void setRobot(application.robot robot) {
	this.robot = robot;
	
}
protected void checCollision(){
	
	for(int i = 0;i<obstacles.size();i++){
		
		if(robot.getBoundsInParent().intersects(obstacles.get(i).getBoundsInParent())){
			
			handleCollision();
		}
		
	}
	
}
protected  void handleCollision() {
	// TODO Auto-generated method stub
	robot.blink();
	//invet();
	

}

protected void Start(){
	
	displaysStatus();
	getMinRightDistance();
	getMinLeftDistance();
	if(checWallX())
		//handleWallXCollision();;
		if(checWallY())
			//handleWallYCollision();;
	checCollision();
}

private void displaysStatus()
{
	Static.RoboTC.setText("Robot Cordinates: "+"X "+(int)robot.getCenterX()+" Y "+(int)robot.getCenterY());
	Static.RobotS.setText("Robot speed: " + robot.getSpeed());
	Static.RobotR.setText("Robot rotation: "+ robot.getRotation());
	Static.SensorDR.setText("Left distance: "+ (int)robot.distR);
	Static.SensorDL.setText("Right distance: "+ (int) robot.distL);
	

}
protected void setTarget(double d, double e){
	robot.Xtarget=d;
	robot.Ytarget=e;
}
protected double getXTarget() {
	// TODO Auto-generated method stub
	return robot.Xtarget;
}

protected double getYTarget() {
	// TODO Auto-generated method stub
	return robot.Ytarget;
}


protected double fromTarget(){
	return fromPoint(getXTarget(),getYTarget());
}
protected Vector getVectorToObstavle(int index){
	double x = (robot.getCenterX()-obstacles.get(index).getCenterX());
	double y = ((robot.getCenterY()-obstacles.get(index).getCenterY()));
	return new Vector((x), (y));
}

protected Vector getVectorToTarget(){
	double x = (robot.getCenterX()-getXTarget());
	double y = ((robot.getCenterY()-getYTarget()));
	return new Vector((x), (y));

}
protected double fromPoint(double xtarget,double ytarget) {
	// TODO Auto-generated method stub
	return v.getMag(robot.getCenterX()-xtarget,robot.getCenterY()-ytarget);
}
protected double fromPoint(int index) {
	// TODO Auto-generated method stub
	return fromPoint(obstacles.get(index).getCenterX(),obstacles.get(index).getCenterY());
}
}
