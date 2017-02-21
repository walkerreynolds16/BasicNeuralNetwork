import java.util.Random;

public class NeuralNetwork {

	
	public int inputLayerSize;
	public int outputLayerSize;
	public int hiddenLayerSize;
	
	/**
	 * Weight 1 Matrix
	 * 
	 * Rows = Input Layer Size
	 * Cols = Hidden Layer Size
	 * 
	 */
	public double[][] w1;
	
	/**
	 * Weight 2 Matrix
	 * 
	 * Rows = Hidden Layer Size
	 * Cols = Output Layer Size
	 * 
	 */
	public double[][] w2;
	
	/**
	 * Z2 is the activity of the second layer
	 * Each entry to z2 is a sum of weighted inputs to each neuron
	 * 
	 * Z2 is of size 3x3
	 * 
	 * Rows = number of examples in list
	 * Cols = Hidden Layer Size
	 * 
	 * Z2 = X(input list) * W1(weight 1 matrix)
	 * 
	 */
	public double[][] z2;
	
	/**
	 * A2 is the activity of the second layer after the sigmoid function is applied
	 * 
	 * A2 is the same size as Z2 
	 * 
	 * A2 = f(z2), where f(x) is the activation function(Sigmoid function)
	 * 
	 */
	public double[][] a2;
	
	/**
	 * Z3Temp is being used as an intermediate matrix for z3
	 * 
	 * It z3Temp is converted to a 1D matrix from a 2D matrix
	 */
	public double[][] z3Temp;
	
	/**
	 * Z3 is the activity of our third layer
	 * 
	 * Z3 is of size 3x1 since we Multiply A2(3x3) to W2(3x1)
	 * 
	 * Z3 = A2(list of activated values) * W2(weight 2 matrix)
	 * 
	 * Z3 will have 3 values, one for each example
	 * 
	 */
	public double[] z3;
	
	/**
	 * yHat is the final estimate of test score through system
	 * 
	 * yHat = f(z3), where f(x) is the activation function(Sigmoid function)
	 * 
	 */
	public double[] yHat;
	
	public NeuralNetwork(){
		
		//Define HyperParameters
		inputLayerSize = 2;
		outputLayerSize = 1;
		hiddenLayerSize = 3;
		
		//Define Weight Matrices
		w1 = HelperMethods.fillArrayWithRandom(inputLayerSize, hiddenLayerSize);
		w2 = HelperMethods.fillArrayWithRandom(hiddenLayerSize, outputLayerSize);
		
		
	}
	
	public double[] forward(double[][] x){
		//Propagate inputs through network
		
		z2 = HelperMethods.dot(x, w1);
		a2 = HelperMethods.sigmoid2D(z2);
		z3Temp = HelperMethods.dot(a2, w2);
		z3 = HelperMethods.twoDToOneD(z3Temp);
		yHat = HelperMethods.sigmoid1D(z3);
		return yHat;
		
	}
	
	public double[][][] costFunctionPrime(double[][] x, double[][] y){
		
		yHat = forward(x);
			
		double[][] delta3 = HelperMethods.multiply(HelperMethods.minusMatrix2D(y, HelperMethods.oneDToTwoD(yHat)), HelperMethods.sigmoid1DPrime(z3));
		double[][] djdw2 = HelperMethods.dot(HelperMethods.transposeMatrix(a2), delta3);
		
		double[][] delta2 = HelperMethods.multiply(HelperMethods.dot(delta3, HelperMethods.transposeMatrix(w2)), HelperMethods.sigmoid2DPrime(z2));
		double[][] djdw1 = HelperMethods.dot(HelperMethods.transposeMatrix(x), delta2);
		
		
		double[][][] res = {djdw1, djdw2};
		return res;
	}
	
	public double[] getParams(){
		double[] params = HelperMethods.concatenate(HelperMethods.ravel(w1), HelperMethods.ravel(w2));
		return params;
	}
	
	public void setParams(double[] params){
		int w1Start = 0;
		int w1End = inputLayerSize * hiddenLayerSize;
		
		
	}
	
	
	
	
	public static void main(String[] args){
		
		//Testing Sigmoid methods
		/*
		double[] arr = {-6, -4, -2, 0, 2, 4, 6};
		
		double[][] arr2 = new double[3][4];
		
		for(int i = 0; i < arr2.length; i++){
			for(int k = 0; k < arr2[i].length; k++){
				arr2[i][k] = (Math.random() * 10) - 5;
			}
		}
		
		int count = 1;
		
		double[] res = HelperMethods.sigmoid1D(arr);
		double[] resPrime = HelperMethods.sigmoid1DPrime(arr);
		HelperMethods.printArray(res);
		
		System.out.println("\n=========================\n");
		
		HelperMethods.printArray(resPrime);
		*/
		
		
		
		//testing dot product
		/*
		double[][] arr1 = {{3,5}, {5,1}, {10,2}};
		double[][] arr2 = {{11,12,13}, {21,22,23}};
		
		System.out.println(" *Array 1* \n");
		printArray(arr1);
		
		System.out.println("============================================\n");
			
		System.out.println(" *Array 2* \n");
		printArray(arr2);
		
		System.out.println("============================================\n");
		
		String[][] res = dotTest(arr1, arr2);
		
		System.out.println(" *Dot Product* \n");
		printArrayString(res);
		
		*/
		
		double[][] y = {{0, 1, 2},{3, 4, 5},{6, 7, 8}};
		double[][] yhat = {{0, 1, 2}};
		
		double[] yRavel = HelperMethods.ravel(y);
		double[] yhatRavel = HelperMethods.ravel(yhat);
		HelperMethods.printArray(yhatRavel);
		
		//numericalGradientChecking();
		
		HelperMethods.printArray(HelperMethods.concatenate(yRavel, yhatRavel));
		
	}
	
	public static void testNeuralNetwork(){
		double[][] x = {{3,5}, {5,1}, {10,2}};
		double[][] y = {{75},{82},{93}};
		
		x = HelperMethods.scale(x);
		y = HelperMethods.scale(y, 100);
		
		HelperMethods.printArray(x);
		System.out.println("\n===============================\n");
		HelperMethods.printArray(y);
		System.out.println("\n===============================\n");
		
		NeuralNetwork n = new NeuralNetwork();
		double[][][] res = n.costFunctionPrime(x, y);
		double[][] djdw1 = res[0];
		double[][] djdw2 = res[1];
		HelperMethods.printArray(djdw1);
		System.out.println("\n===============================\n");
		HelperMethods.printArray(djdw2);
	}
	
	public static void numericalGradientChecking(){
		double epsilon = .0001;
		double x = 1.5;
		
		double numericalGradient = (f(x+epsilon) - f(x-epsilon))/2*epsilon;
		
		System.out.println(numericalGradient + " : " + 2*x);
	}
	
	public static double f(double x){
		return Math.pow(x, 2);
	}
}
