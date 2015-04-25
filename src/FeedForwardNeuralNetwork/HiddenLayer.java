package FeedForwardNeuralNetwork;
import java.util.ArrayList;


public class HiddenLayer extends Layer {


	public HiddenLayer(int size) {
		super(size);
		
		}

public void setError(int index,double outputError){
		
		 neurons.get(index).setHError(outputError);
		
	}

}
