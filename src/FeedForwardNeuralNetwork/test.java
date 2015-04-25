package FeedForwardNeuralNetwork;

import javafx.stage.FileChooser;





public class test {


	private static double trainInputs[][] = new double[][] {    {167 ,131}, 
																{27 ,28},
																{10000 ,99}, 
																{3,10000 }	};
	private static double test[] = new double[] {67 ,68};

private static double trainOutput[][] = new double[][] 
   {{1},
	{0},
	{1},
	{0}};
	public static void main(String[] args) {
	
		int[] array = {3,4,5,6};
		FFNN nn = new FFNN(array, 4, 1, 1,0.01);
		nn.setINN(2);
		nn.setONN(1);
		nn.setNOHL(3);
		nn.setNONIHL(array);
		
		nn.init();
		nn.loadWeight("e:\\brain.hnn");
		for(int k =0;;k++){
			double error =0;
			for(int i =3;i>=0;i--){
			
				
	nn.setInput(trainInputs[i]);
			nn.feedForward();
			nn.setTargetoutput(trainOutput[i]);
			
		
		
		    for(int j = 0;j<1;j++){
		    	
			nn.backPropagate();
			nn.updateWeights();
		    }
			
			
			
		
			
		

				
			}
			
			for(int i = 0;i<4;i++){
				nn.feedForward();
				nn.setTargetoutput(trainOutput[i]);
				error = error+nn.getError();	
				
			}
			
			
			if(error/4<=0.11|| k>100000){
				break;
				
			}
			}
			System.out.println("Output after training ");
			for(int j=0;j<4;j++){
				nn.setInput(trainInputs[j]);
				nn.feedForward();
				
				
				System.out.println("input");
				for(int i =0;i<2;i++){
					System.out.print(trainInputs[j][i]+" ");
					}
				System.out.println();
				System.out.println("output");
				for(int i =0;i<1;i++){
					System.out.print(nn.outputLayer.get(i).getOut()+" ");
					}
				System.out.println("===========>");
			}
			
			nn.setInput(test);
			nn.feedForward();
			System.out.println(nn.outputLayer.get(0).getOut());
			System.out.println(Math.sin(3.14/4));
	nn.saveweight("e:\\brain.hnn");
		
		}
	}


