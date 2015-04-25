package FeedForwardNeuralNetwork;
import java.util.Random;


public class Connections {

	private int INN;
	private int ONN;
	private int NOHL;
	private int[] NONIHL;
	private Double[][][] connections;
	
	public  static final int MAX_NUM_NEURONS = 40; // maximum number in any Layer

	// constructor
	public Connections(int INN,int ONN,int NOHL,int[] NONIHL){
		 
		
		this.INN= INN;
		this.ONN= ONN;
		this.NOHL= NOHL;
		this.NONIHL= NONIHL;
		 
	    connections = new Double[NOHL+1][MAX_NUM_NEURONS][MAX_NUM_NEURONS];	
		 
        init();	
	}//Connections
	

	
	void init(){
     	// Weights from input neurons to 1'st Layer
		for(int i =0;i<INN;i++){
			for(int k=0;k<NONIHL[0]+1;k++){	
			connections[0][i][k]= new Random().nextDouble()-0.5;  // from -0.5 to + 0.5 ; recall that scaling 
																  // the weight vectors does not matter. 
						}
					}

			
        // weights between layers
	for(int i =1;i<NOHL;i++){
		for(int k=0;k<NONIHL[i-1]+1;k++)           //starting from index zero of the HiddenLayers
			for(int z =0;z<NONIHL[i]+1;z++){
		connections[i][k][z]=new Random().nextDouble()-0.5;
						}
					}
				
		
	 
		//weights from last layer to the ouput
	for(int i = 0;i<NONIHL[NOHL-1]+1;i++){
		for(int k = 0;k<ONN;k++){       /// problem ???
			connections[NOHL][i][k]=new Random().nextDouble()-0.5;
			}
		}
	 
	
	
	}// end of init();

	public double get(int layer, int fromNeuron,int toNeuron){
		return connections[layer][fromNeuron][toNeuron];


	}
	public double set(int layer, int fromNeuron,int toNeuron, double weight){
		return connections[layer][fromNeuron][toNeuron]= weight;


	}
}//end of class