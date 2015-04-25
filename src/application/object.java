package application;

import javafx.animation.FillTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class object extends Group {
public double vX;
public double PvX;
public double vY;
public double PvY;
public double speed=0;
public double rotation=0;
protected Rectangle rec;
protected Vector vector;
public object(int w,int h) {
	vector = new Vector();
rec = new Rectangle(w,h);
getChildren().add(rec);
}
public double getSpeed(){
	return speed;
}
public Rectangle get(){
	return rec;
}
public double getRotation(){
	return rotation;
}	
public double getvX(){
	return vX;
}
public double getvY(){
	return vY;
}
public void setSpeed(double speed){
	if(speed>5)speed=5;
	if(speed<-5)speed=-5;
	this.speed=speed;
}
public void setvX(double vX){
	this.vX=vX;
}
public void setvY(double vY){
	this.vY=vY;
}
public void setRotation(double rotation){

	rotation = Math.floorMod((long) rotation, 360);
	this.rotation=rotation;
	setRotate(rotation);

	
}
public void rotate(double rotation){
	setRotation(getRotation()+rotation);
}
protected void invet(){
	speed= speed * -1;
}
public  double getRadius(){
	return vector.getMag(rec.getWidth()/2, rec.getHeight()/2);
	
}
public void blink(){
	
	  FillTransition t = new FillTransition(Duration.millis(100), this.get());
      t.setFromValue(Color.YELLOW);
      t.setToValue(Color.BROWN);
     
      t.setCycleCount(2);
     
      t.play();
	
}

public  double getCenterX(){
	return getBoundsInParent().getMinX()+getBoundsInParent().getWidth()/2  ;
}
public  double getCenterY(){
	return getBoundsInParent().getMinY()+getBoundsInParent().getHeight()/2  ;
}


}
