package FeedForwardNeuralNetwork;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Maths.NMaths;


public class FFNN {

private int INN;   //number of input neurons
private int ONN;   // number of output neurons
private int NOHL;  // number of hidden layres 
public InputLayer inputLayer;
public ArrayList<HiddenLayer> hiddenLayers;
private double[] input;
public OutputLayer outputLayer;
private int[] NONIHL; // number of neurons in hidden layers
public Connections connections;
private double[] targetTrainingOutput;
private double learningRate = 0.05;


public  static final int MAX_NUM_NEURONS = 40;


         public FFNN(int[] NONIHL,int NOHL, int INN, int ONN, double learningRate){
        	// if(INN>MAX_NUM_NEURONS || ONN > MAX_NUM_NEURONS || NMaths.maxInt(NONIHL)>MAX_NUM_NEURONS){
        		 
        	//?????????????????????????????????//	 
        		 
        	// }
    this.learningRate = learningRate;
	this.INN =INN+1;
	this.NOHL =NOHL;
	this.ONN =ONN;
	this.NONIHL = NONIHL;
	 // set up the architecture
                       }
         public FFNN(){
         	// if(INN>MAX_NUM_NEURONS || ONN > MAX_NUM_NEURONS || NMaths.maxInt(NONIHL)>MAX_NUM_NEURONS){
         		 
         	//?????????????????????????????????//	 
         		 
         	// }

 	 // set up the architecture
                        }
         
  //initialisation    of the network    
  public void init() {
		 
		connections = new Connections(INN,ONN,NOHL,NONIHL);
		 
	    inputLayer = new InputLayer(INN);
		outputLayer = new OutputLayer(ONN);
		hiddenLayers = new ArrayList<>(NOHL);  //initialise the hidden layer List
   

	
	
		for(int i=0;i<NOHL;i++){
			hiddenLayers.add(new HiddenLayer(NONIHL[i]+1));
			hiddenLayers.get(i).setNumberOfNeurons(NONIHL[i]+1);
		}
		
  }// end init()

  
  
  public  void feedForward()       // feedForward and calculate Output
    {
	  
	  double sum ;
      //calculate the output of the first hiddenlayer
	  for(int i=0;i<hiddenLayers.get(0).size()-1;i++){ //loop throw the neurons
		                                             //of the 1'st Layer
		  sum =0;
		  for(int j = 0 ;j<inputLayer.size();j++){
			  sum += inputLayer.get(j).getOut()*
					  connections.get(0,j,i);
			  }//input loop
		  hiddenLayers.get(0).get(i).setTotalInput(sum);
	  
	  }//output loop
	  hiddenLayers.get(0).get(hiddenLayers.get(0).size()-1).setTotalInput(-1); // bias
	  
/********************************************************/    
// calculate the output of the all hiddenlayers but the first	  
for(int i=1;i<hiddenLayers.size() ;i++){// loop  throw hidden Layers
	  for(int j=0;j<hiddenLayers.get(i).size()-1;j++){ //loop throw neurons in each layer
		 sum =0;
		  for(int k =0;k<hiddenLayers.get(i-1).size();k++){ // loop throw the neurons in the p
			  												//	previous layer 
			  sum += hiddenLayers.get(i-1).get(k).getOut()
					  *connections.get(i, k, j);
			    }// end loop throw the i-1 hidden layer
		  
		  hiddenLayers.get(i).get(j).setTotalInput(sum);
	  }// end loop throw the i hiddenlayer
	  hiddenLayers.get(i).get(hiddenLayers.get(i).size()-1).setTotalInput(-1);
}// end loop throw hidden layers

/***********************************************************/
// calculate the outputs of the output layer	  
	  for(int i=0;i<outputLayer.size();i++){// loop  throw the output layer
		  sum =0;
		  for(int j=0;j<hiddenLayers.get(NOHL-1).size();j++){ //loop throw the last hidden layer
			                                            		
				  sum += hiddenLayers.get(NOHL-1).get(j).getOut()
						  *connections.get(NOHL, j, i);
		  }// end loop throw the last hiddenlayer
		  outputLayer.get(i).setTotalInput(sum);
	  }// end loop throw outputLayer
	  
    }
  
 public void setInput(double[] input){
	 inputLayer.setInput(input);
	  this.input = input;
	 }
  
  public void setTargetoutput(double[] targetTrainingOutput){
	  this.targetTrainingOutput = targetTrainingOutput;
	  }
 public double[]  gettInput(){
	  return this.input; 
	 }
  
  public double[]  gettTargetoutput(){
	  return this.targetTrainingOutput ;
	  }
  
  
  
    public  void backPropagate()   //backpropagate and adjust the weights
    {
    	 
    	for(int i = 0; i < ONN; i++)
         {
                outputLayer.setError(i, targetTrainingOutput[i] );
         }
    	
    	 
    	for(int i = 0; i<hiddenLayers.get(NOHL-1).size();i++){
    		double outputError =0;
    		for(int j=0; j< outputLayer.size();j++){
    			
    		outputError +=	connections.get(NOHL, i, j)* outputLayer.get(j).getError();
    			
    		}
    		hiddenLayers.get(NOHL-1).setError(i, outputError);
    		
    	}
    	
    	for (int i = NOHL-2;i>=0;i-- ){
    		
    		for(int j =0;j<hiddenLayers.get(i).size();j++){
    			double outputError =0;
    			for(int k =0;k<hiddenLayers.get(i+1).size();k++ ){
    				outputError +=	connections.get(i+1, j, k)* hiddenLayers.get(i+1).get(k).getError();		
    			}
    			hiddenLayers.get(i).setError(j, outputError);
    		}
    		
    	}
    	
    	
    	for(int i=0;i<inputLayer.size();i++){
    		double outputError =0;
    		for(int j =0 ;j<hiddenLayers.get(0).size();j++){
    			
    			outputError += connections.get(0, i, j)*hiddenLayers.get(0).get(j).getError(); 
    		}
    		inputLayer.setError(i, outputError);
    	}
    	

    
    

    }
    
    
    
    public void updateWeights(){ // update the weights corresponds to the errors
    double newWeight=0;	

    for(int i=0;i<outputLayer.size();i++){
    		for(int j =0;j<hiddenLayers.get(NOHL-1).size();j++){
    			
    newWeight = hiddenLayers.get(NOHL-1).get(j).getOut()*outputLayer.get(i).getError()*learningRate
                + connections.get(NOHL,j,i);
    			connections.set(NOHL, j,i, newWeight);		
    			}
    		}
    	
    for(int i = NOHL-1;i>=1;i--){
    	for(int j=0;j<hiddenLayers.get(i).size();j++){
    		for(int k =0;k<hiddenLayers.get(i-1).size();k++){
    			
    		    newWeight = hiddenLayers.get(i-1).get(k).getOut()*
    		    		hiddenLayers.get(i).get(j).getError()*learningRate
    	                + connections.get(i,k,j);
    	    			connections.set(i, k,j, newWeight);		
    			
    		}
    	}
    }
    	for(int i=0;i<hiddenLayers.get(0).size();i++){
			for(int j =0;j<inputLayer.size();j++){
				newWeight = inputLayer.get(j).getOut()*
    		    		hiddenLayers.get(0).get(i).getError()*learningRate
    	                + connections.get(0,j,i);
    	    			connections.set(0, j,i, newWeight);			
				
			}
			
		}
    	
    	
    }
    	
    public double getError(){
    	
   
    		    double error=0;
    		    for(int i = 0;i<ONN;i++){
    			
    			   error =  error +((outputLayer.get(i).getOut()-
    					targetTrainingOutput[i])* (outputLayer.get(i).getOut()-
    	    					targetTrainingOutput[i]));
    			   
    		   
    		
    		
    			}
    	
    	
    	return  error/(2*ONN);
    }
public void printOutput(){
	System.out.print("Output:");
	for(int i =0;i<getOutputSize();i++){
		
		System.out.print(" "+outputLayer.get(i).getOut());
		
	}
	System.out.println();
}
    
public void printInput(){
	System.out.print("Input:");
	for(int i =0;i<input.length;i++){
		
		System.out.print(" "+gettInput()[i]);
		
	}
	System.out.println();
}
public void printtargetOutput(){
	System.out.print("TargetOutput:");
	for(int i =0;i<getOutputSize();i++){
		
		System.out.print(" "+gettTargetoutput()[i]);
		
	}
	System.out.println();
}
public void setINN(int INN){
	this.INN = INN + 1;
}
public void setONN(int ONN){
	this.ONN = ONN;
}
public void setNONIHL(int[] NONIHL){
	this.NONIHL = NONIHL;
}
public void setNOHL(int NOHL){
	this.NOHL = NOHL;
	}
public int  getOutputSize() {
	
	return ONN;
}

public boolean loadWeight(String path) {
	File file = new File(path);
    Scanner in;
	try {
		in = new Scanner(file);

    for(int i=0;i<NOHL+1;i++){
    	if(i==0){
    	for(int j=0;j<INN;j++){
    		for(int k =0;k<NONIHL[0]+1;k++){
    		connections.set(i, j, k, in.nextDouble());
    			}
    		
    	}
    	
    	}else if(i==NOHL){
    	        	for(int j =0;j<NONIHL[NOHL-1]+1;j++){
    	        		for(int k =0;k<ONN;k++){
    	        			connections.set(i, j, k, in.nextDouble());
    	        			}
    	        	}
    	        
    	        	
    	        }else{
    	        	
    	        	for(int j=0;j<NONIHL[i-1]+1;j++){
    	        		for(int k =0;k<NONIHL[i]+1;k++){
    	        			
    	        			connections.set(i, j, k, in.nextDouble());
    	        		}
    	        		
    	        		
    	        	}
    	       
    	        	
    	        	
    	        }
    	
    	
    }

    return true;
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
	return false;
	}
}

public boolean saveweight(String path){
	File file = new File(path);
	
  
	try {
	
		  FileWriter out = new FileWriter(file);
	
    
    for(int i=0;i<NOHL+1;i++){
    	if(i==0){
    	for(int j=0;j<INN;j++){
    		for(int k =0;k<NONIHL[0]+1;k++){
    			out.write(connections.get(i, j, k)+ " ");
    			}
    		out.write("\n");
    	}
    	out.write("\n");
    	}else if(i==NOHL){
    	        	for(int j =0;j<NONIHL[NOHL-1]+1;j++){
    	        		for(int k =0;k<ONN;k++){
    	        			out.write(connections.get(i, j, k)+ " ");
    	        			}
    	        		out.write("\n");
    	        	}
    	        	out.write("\n");
    	        	
    	        }else{
    	        	
    	        	for(int j=0;j<NONIHL[i-1]+1;j++){
    	        		for(int k =0;k<NONIHL[i]+1;k++){
    	        			
    	        			out.write(connections.get(i, j, k)+ " ");
    	        		}
    	        		
    	        		out.write("\n");
    	        	}
    	        	out.write("\n");
    	        	
    	        	
    	        }
    	
    	
    }
	out.flush();
	out.close();
	
	return true;
	
	} catch (IOException e) {
	
	e.printStackTrace();
	return false;
	}
	
}



}//end of class
