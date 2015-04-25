package application;

import javafx.scene.input.KeyCode;

public  class eventhandler {


	static public double pSpeed =0;
	static public double pAngle =0;
	private static int cases;

static void handle(KeyCode keyCode){
	
	if(Controller.annChoice ){
		
		if(keyCode == KeyCode.UP){		
			Controller.Algorithm.setSpeed(Controller.Algorithm.getSpeed()+ 0.05);	
             Data(0);
			
		}
		if(keyCode == KeyCode.LEFT){
			Controller.Algorithm.rotate(-4);
			 Data(-4);
		}
		if(keyCode == KeyCode.RIGHT){
			Controller.Algorithm.rotate(4);
			 Data(4);
		}
		if(keyCode == KeyCode.DOWN){
			Controller.Algorithm.setSpeed(Controller.Algorithm.getSpeed()- 0.05);
			 Data(0);
		}
		if(keyCode == KeyCode.N){
			Controller.Algorithm.setSpeed(0);
			 Data(0);
		}
	}
}
	
	 static  void Data(double d){
	
		 cases++;
			
			if(cases<Controller.maxTrainingCases){
			double[] input ={/*Controller.Algorithm.getVectorToTarget().y,Controller.Algorithm.getVectorToTarget().x
					,*/Controller.Algorithm.getMinLeftDistance(),Controller.Algorithm.getMinLeftDistance()
				/*,pSpeed,pAngle*/};
			
			double[] output = {(Controller.Algorithm.getSpeed()*0.1)+0.5,d/8+0.5};
			
			
			pSpeed = Controller.Algorithm.getSpeed();
			pAngle = Controller.Algorithm.getRotation();
			Controller.input.add(input);
			Controller.output.add(output);
			}
			
		}
		
		
		
	
	
	 public static void printArray(double[] array,String s){
			System.out.print(s);
			for(int i =0;i<array.length;i++){
				
				System.out.print(" "+array[i]);
				
			}
			System.out.println();
		}
}
