package application;


import java.net.URL;

import FeedForwardNeuralNetwork.*;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;


public class Controller implements Initializable {
	@FXML
	Pane drawPane,ANNPane;
	@FXML
	private Button start,init,SetTarget,Reset,Train,Run,Data;
	@FXML
    Label Cordinates,RoboTC,RobotR,RobotS,SensorDR,SensorDL;
	@FXML
	public  CheckBox ANNChoice;
	@FXML
	private TextField hiddenLayersText,learningRateText;
	public static  FFNN ann;
	public static Algorithm Algorithm ;
	private double w;
	private double h;
	private robot robot;
	private boolean Init;
	private int[] NONIHL;
	private int NOHL,INN,ONN;
	
	private Line mvl;
	private Line mhl;
	private Circle TargetPoint;
	private boolean setTarget;
	private int learningRate;
	private int train;
	static public int maxTrainingCases = 1000000;
	public static ArrayList<double[]> input = new ArrayList<double[]>();
	public static ArrayList<double[]> output = new ArrayList<double[]>();
	public static boolean annChoice;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		w = drawPane.getPrefWidth();
		h = drawPane.getPrefHeight();
		
		Algorithm = new Algorithm(w,h);
		int[] ar = {1,1};
		ann = new FFNN(ar, 2, 2, 2,0.5);
	
		
		init();
		loop();
		event();

	}

	
	



/**************************************************/
public void init(){
    mvl = new Line(0, 0, 0, h);
	mvl.setStrokeWidth(1);
	mvl.setStroke(Color.RED);
    mhl = new Line(0, 0, w, 0);
	mhl.setStrokeWidth(1);
	mhl.setStroke(Color.BLUE);
	TargetPoint= new Circle(5);
	TargetPoint.setFill(Color.CADETBLUE);
	drawPane.getChildren().add(TargetPoint);
	hiddenLayersText.setText("3");
	
	
	Line vl = new Line(w/2, 0, w/2, h);
	vl.setStrokeWidth(0.2);
	Line hl = new Line(0, h/2, w, h/2);
	vl.setStrokeWidth(0.2);
    robot =new robot(35, 45);
    robot.get().setFill(Color.BROWN);
    robot.get().setStroke(Color.BLACK);
    robot.setTranslateX((w-35)/2);
robot.setTranslateY(h-90);
Train.setDisable(true);
Run.setDisable(true);
Data.setDisable(true);
hiddenLayersText.setDisable(true);
learningRateText.setDisable(true);
drawPane.getChildren().addAll(mhl,mvl,hl,vl,robot);
Algorithm.setRobot(robot);
Algorithm.setSpeed(0);
Static.RoboTC = RoboTC;
Static.RobotS = RobotS;
Static.RobotR = RobotR;
Static.SensorDR = SensorDR;
Static.SensorDL = SensorDL;

	}
	

/***************************************/
	private void loop() {
		
		
		
		Algorithm.Start();	
	}

/***************************************/
	
	
	
	
	
/**
 ********************************/	
	
	private  void event() {
		
		
		ANNChoice.selectedProperty().addListener(new ChangeListener<Boolean>() {
	          

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				
		   
		  // Train.setDisable(oldValue);
		  
		   Data.setDisable(oldValue);
		   hiddenLayersText.setDisable(oldValue);
		   learningRateText.setDisable(oldValue);
				
			}
	        });
		Data.setOnAction(e->{
			// input.clear();
			 //output.clear();
			  // eventhandler.cases =0;
			   hiddenLayersText.setDisable(true);
			   learningRateText.setDisable(true);
		       Static.timeline.play();
			   Algorithm.Run = false;
			   annChoice = true;
			   Algorithm.Run2=true;
	
			   

			  
			   
			   Train.setDisable(false);
			  // Data.setDisable(true);
		});
Train.setOnAction(e->{
	Algorithm.Run2=false;
Static.timeline.stop();
	Train.setDisable(true);
	Data.setDisable(true);
    Run.setDisable(true);
   
  

	if(train == 0){
		 NOHL = Integer.parseInt(hiddenLayersText.getText());
		   
		    NONIHL = new int[NOHL];
		    for(int i=0;i<NONIHL.length;i++){
		    	NONIHL[i]=6;	
		    }
		    
		  INN = 2;
		    ONN = 2;
			ann.setINN(INN);
			ann.setONN(ONN);
			ann.setNOHL(NOHL);
			ann.setNONIHL(NONIHL);
	ann.init();
	
	train++;
	}
	//ann.loadWeight("e:\\brain2.hnn");
	   for(int k=0;k<10000;k++){
	   
		   for(int i =0;i<input.size();i++){
	ann.setInput(input.get(i));
	//ann.printInput();
	ann.feedForward();
	//ann.printOutput();
	ann.setTargetoutput(output.get(i));
	for(int j =0;j<1;j++){
		ann.backPropagate();
		ann.updateWeights();
	}
	
	
    }
	   }
	
	ann.saveweight("e:\\2brainObstacele.hnn");
	Train.setDisable(false);
	Data.setDisable(false);
    Run.setDisable(false);	


});

Run.setOnAction(e->{
	Algorithm.Run2=false;
	  hiddenLayersText.setDisable(false);
	   learningRateText.setDisable(false);
	   Static.timeline.play();
		  robot.setSpeed(0);
		  robot.setRotation(0);
		  robot.setTranslateX((w-35)/2);
		  robot.setTranslateY(h-90);
	   
	Algorithm.Run = true;
	
	annChoice = false;
	
});
			
			Reset.setOnAction(e->{
				
				  drawPane.getChildren().remove(6, drawPane.getChildren().size());	
				  Algorithm.obstacles.clear();
				  Static.timeline.pause();
				  robot.setSpeed(0);
				  robot.setRotate(0);
				  robot.setTranslateX((w-35)/2);
				  robot.setTranslateY(h-90);
				
				
			});
			

start.setOnAction(e->{
	if(Static.timeline.getStatus()==Status.PAUSED||Static.timeline.getStatus()==Status.STOPPED)
		Static.timeline.play();  
	else Static.timeline.pause(); 
	
	});
init.setOnAction(e->{
	Init = !Init;
	setTarget = false;
	});
SetTarget.setOnAction(e->{
	
	setTarget = !setTarget;
	Init = false;
});
drawPane.setOnMouseMoved(e->{
Cordinates.setText("X "+e.getX()+" Y"+e.getY());
mvl.setStartX(e.getX());
mvl.setEndX(e.getX());
mhl.setStartY(e.getY());
mhl.setEndY(e.getY());

});



setObstacles();
	}

	
	
	
/*************************************************************/	
public void setObstacles(){
		drawPane.setOnMouseClicked(e->{
			
if(Init){
			object obj= new  object(33,33);
	   obj.get().setX(e.getX());
	   obj.get().setY(e.getY());
	    obj.get().setFill(new Color(0.1, 0.1, 0.7, 0.5));
	    obj.setSpeed(3);
	   
	    drawPane.getChildren().add(obj);
	 
	    Algorithm.add(obj);
}else if(setTarget){
	TargetPoint.setCenterX(e.getX());
	TargetPoint.setCenterY(e.getY());
	
	Algorithm.setTarget(e.getX(),e.getY());
	
}
		});	
	}
}
