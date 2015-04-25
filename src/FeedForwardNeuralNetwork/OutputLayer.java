package FeedForwardNeuralNetwork;
import java.util.ArrayList;


public class OutputLayer extends Layer {

    public OutputLayer(int size) {
	    super(size);
	    setNumberOfNeurons(size);
	}

    public 	double getRMSError(){
	    double error=0;
	    for(int i = 0;i<neurons.size();i++){
		
		   error += 1/2*neurons.get(i).getError()*
				neurons.get(i).getError();
		   
	   }
	
	return error;
		}
	

	public void setError(int index,double targetValue){
		
		neurons.get(index).setOError(targetValue);
		
	}
	
}
