package FeedForwardNeuralNetwork;
import java.util.ArrayList;


public class InputLayer extends Layer {

	private double[] InputVector;
	public InputLayer(int size) {
		super(size);
		
	setNumberOfNeurons(size);
	
	}
	
/*********************************************/	
	public void  setInputI(int index, double input){
		get(index).setInput(input);
		}
	
	
/*************************************************/	
    public void setInput(double[] inputVector){
    	this.InputVector = inputVector;
		for(int i=0;i<size()-1;i++){
				get(i).setInput(InputVector[i]);
			}
		get(size()-1).setInput(-1);

	
 
			}

/***************************************************/
		public void setError(int index,double outputError){
			
			 neurons.get(index).setHError(outputError);
			
		}



}
