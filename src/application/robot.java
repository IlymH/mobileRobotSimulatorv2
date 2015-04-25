package application;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineJoin;

public class robot extends object{
public double distR;
public double distL;
public double Xtarget=0;
public double Ytarget=0;
	

	public robot(int w, int h) {
		super(w, h);
		
	
	Line l = new Line(w/2, h/2, w, 0);
	l.setStroke(Color.BLACK);
	l.setStrokeLineJoin(StrokeLineJoin.ROUND);
	l.setStrokeWidth(2);
	Line l2 = new Line(w/2, h/2, 0, 0);
	l2.setStroke(Color.BLACK);
	l2.setStrokeLineJoin(StrokeLineJoin.ROUND);
	l2.setStrokeWidth(2);
			
	//////////////////////////
        Group group =  new Group();
        group.getChildren().addAll(l,l2);
getChildren().addAll(group);		
	
}

public double fromTarget(){
	return fromPoint(Xtarget,Ytarget);
}

public double fromPoint(double xtarget2,double ytarget2) {
// TODO Auto-generated method stub
	return vector.getMag(getCenterX()-Xtarget, getCenterY()-Ytarget);
}
	
		
		}


