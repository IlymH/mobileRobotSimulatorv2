package FeedForwardNeuralNetwork;
import Maths.NMaths;


public class Neuron {

	private double input;
	private double output;
	private double targetValue;
	private double error;
	
	
	
	
	
	
	public double getOut() {
		// TODO Auto-generated method stub
		return output;
	}

	public void setTotalInput(double inputs) {
		input = inputs;
      calculateOut();	
	}
	
	// use this method for setting the value 
	//of the input of the neurons in the input Layer
public void setInput(double input){
	this.input = input;
	this.output =input;//   input ->"pass the value"-> output
}
	
	private void calculateOut() {
		// TODO Auto-generated method stub
		output=NMaths.sigmoid(input);
	}

	 
   public double getTarget() {  // for the use on the output Layer

		return targetValue;
	}
	
	void setOError(double targetValue){
		this.targetValue = targetValue;
		
		this.error = (targetValue-getOut())*NMaths.sigmoidDerivative(getOut());
		
	}


	public double getError(){
		
	return	this.error;
		
	}

	public void setHError(double outputError) {
		this.error = outputError *  NMaths.sigmoidDerivative(getOut());
		
	}
}
