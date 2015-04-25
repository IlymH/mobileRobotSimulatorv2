package application;
import javafx.animation.Animation;

import java.io.ObjectInputStream.GetField;

import FeedForwardNeuralNetwork.FFNN;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Algorithm extends scenaroManager {

	

	public static boolean Run = false;
	private Vector forceVector;
	private Vector atrf;
	private Vector forceVector2;
	public boolean Run2 = false;
	public double d;
	private double angle;
	private double pd;
	static public int cases =0;


	public Algorithm(double With, double Height) {
		super(With, Height);
		
		 forceVector = new Vector(0,0);
		
	}
	
	public void Start(){
//setSpeed(2);

		Static.timeline = new Timeline(new KeyFrame(Duration.millis(1000/FPS), e->{
	
			//Data();
			
if(Run){		
//test();

run();
}	
//eventhandler.pSpeed = getSpeed();
//eventhandler.pAngle = getRotation();

move();	
		
		
			super.Start();
		})) ;
		Static.timeline.setCycleCount(-1);
}

	

	


	

private void run() {
		// TODO Auto-generated method stub
	double[] input ={/*getVectorToTarget().y,getVectorToTarget().x
,*/ getMinLeftDistance(),getMinLeftDistance()/*,targetInRange(),getSpeed(),getRotation()*/};
	
	
	Controller.ann.setInput(input);
	Controller.ann.feedForward();
	//Controller.ann.printOutput();
setSpeed((Controller.ann.outputLayer.get(0).getOut()-0.5)*10);

rotate((Controller.ann.outputLayer.get(1).getOut()-0.5)*8);
//setRotation((Controller.ann.outputLayer.get(1).getOut()-0.1)*360 );
System.out.println((Controller.ann.outputLayer.get(1).getOut()-0.5)*8);	
	

	}

private void test() {
	checCollision();
	
angle = angle+d;
	atrf = VectorToTarget().getForce();
	atrf.scale(100);
	
	/*forceVector = repForce();
	if(forceVector.getMag()!=0)
	{
		forceVector=forceVector.getForce();
		forceVector.scale(-100);
		atrf.add(forceVector);
	}*/
	
	
	

if(fromTarget()<robot.getRadius()){
	
	setSpeed(0);
	rotate(0);


}else if(fromTarget()<robot.getRadius()*2){
	setSpeed(fromTarget());
	rotate(0);
}
else{
	
setRotation2(atrf.x, atrf.y);
	
setSpeed(getSpeed()+atrf.getMag());
	   	    System.out.println("yyy");
}

	}

private void setRotation2(double x, double y) {
	setRotation(-(Math.atan2(x ,y))*360/(2*Math.PI)+angle);
}




private Vector repForce(){
	Vector v = new Vector(0,0);
	for(int i=0;i<obstacles.size();i++){
	if(ObstacleInRange(i))
		v.add(getVectorToObstavle(i));
		
	
	}
	return v;
}
private Vector VectorToTarget(){
	return new Vector(((robot.getCenterX()-getXTarget())),
			((robot.getCenterY()-getYTarget())));
	
}

private boolean obstacleInRage(){
	
	for(int i =0;i<obstacles.size();i++){
		if(ObstacleInRange(i))
			return true;
	}
	
	return false;
}

		

	


}
