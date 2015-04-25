package FeedForwardNeuralNetwork;
import java.util.ArrayList;


public class Layer {
protected ArrayList<Neuron> neurons = new ArrayList<>();
protected int numberOfNeurons;
	
	Layer(int size){
		//setNumberOfNeurons(size);
	
	}
	

   public void setInput(int index ,double inputs){
	   
	   neurons.get(index).setTotalInput(inputs);
   }
   
	
	public Neuron get(int index){
		
		return neurons.get(index);
	}
	// size of neurons in the layer
	public	int size(){   
			
			
			return numberOfNeurons;
		}
		
		public void setNumberOfNeurons(int numberOfNeurons) {
			neurons = new ArrayList<>(numberOfNeurons);
			this.numberOfNeurons = numberOfNeurons;	
			for(int i=0 ;i<numberOfNeurons;i++){
				neurons.add(new Neuron());
			}//end loop
		}

}
