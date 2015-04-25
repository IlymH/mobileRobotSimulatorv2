package Maths;

public class NMaths {

	public static float maxInt(int[] array) {
	
		
		int max =0;
		for(int i = 0;i<array.length;i++){
		if(array[i]>max)
			max = array[i];
		}
		return max;
	}//end max It

	  public  static  double sigmoid(final double val)
	    {
	        return (1.0 / (1.0 + Math.exp(-val)));
	    }
	 public static  double sigmoidDerivative(final double val)
	    {
	        return (val * (1.0 - val));
	    }
	
	
	
	
}
