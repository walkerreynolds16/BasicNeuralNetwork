
public class HelperMethods {

	private static double maxOfArray(double[][] arr){
		double res = 0;
		
		for(int i = 0; i < arr.length; i++){
			for(int k = 0; k < arr[i].length; k++){
				if(arr[i][k] > res){
					res = arr[i][k];
				}
			}
		}
		
		return res;
	}
	
	public static double[] twoDToOneD(double[][] arr){
		double[] res;
		
		if(arr[0].length > 1){
			res = new double[arr[0].length];
			
			for(int i = 0; i < res.length; i++){
				res[i] = arr[0][i];
			}
			
		}else {
			res = new double[arr.length];
			
			for(int i = 0; i < arr.length; i++){
				res[i] = arr[i][0];
			}
		}
		
		return res;
	}
	
	public static double[][] dot(double[][] arr1, double[][] arr2){
		double[][] res = new double[arr1.length][arr2[0].length];
		
		int arr1Col = 0;
		
		
		for(int arr1Row = 0; arr1Row < arr1.length; arr1Row++){
			
				
				for(int arr2Row = 0; arr2Row < arr2.length; arr2Row++){
					for(int arr2Col = 0; arr2Col < arr2[arr2Row].length; arr2Col++){
						double one = arr1[arr1Row][arr1Col];
						double two = arr2[arr2Row][arr2Col];
						
						res[arr1Row][arr2Col] += arr1[arr1Row][arr1Col] * arr2[arr2Row][arr2Col];
					}
					arr1Col++;
				}
				
			arr1Col = 0;
		}
		
		return res;
		
	}
	
	public static String[][] dotTest(double[][] arr1, double[][] arr2){
		String[][] res = new String[arr1.length][arr2[0].length];
		
		
		for(int i = 0; i < res.length; i++){
			for(int k = 0; k < res[i].length; k++){
				res[i][k] = "";
			}
		}
		
		int arr1Col = 0;
		
		
		for(int arr1Row = 0; arr1Row < arr1.length; arr1Row++){
			
				
				for(int arr2Row = 0; arr2Row < arr2.length; arr2Row++){
					for(int arr2Col = 0; arr2Col < arr2[arr2Row].length; arr2Col++){
						double one = arr1[arr1Row][arr1Col];
						double two = arr2[arr2Row][arr2Col];
						
						res[arr1Row][arr2Col] += one + " * " + two + " | ";
					}
					arr1Col++;
				}
				
			arr1Col = 0;
		}
		
		return res;
		
	}

	public static double[][] fillArrayWithRandom(int rows, int cols){
		double[][] res = new double[rows][cols];
		
		for(int i = 0; i < res.length; i++){
			for(int k = 0; k < res[i].length; k++){
				res[i][k] = (Math.random() * 10) - 5;
			}
		}
		
		return res;
	}
	
	public static void printArray(double[][] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print("[ ");
			for(int k = 0; k < arr[i].length-1; k++){
				System.out.print(arr[i][k] + " , ");
			}
			
			System.out.print(arr[i][arr[i].length-1] + " ]\n");
		}
		
		
	}
	
	public static void printArray(double[] arr){
		System.out.print("[ ");
		for(int i = 0; i < arr.length-1; i++){
			System.out.print(arr[i] + " , ");
		}
		
		System.out.print(arr[arr.length-1] + " ]\n");
		
		
	}
	
	public static void printArrayString(String[][] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print("[ ");
			for(int k = 0; k < arr[i].length-1; k++){
				System.out.print(arr[i][k] + " , ");
			}
			
			System.out.print(arr[i][arr[i].length-1] + " ]\n");
		}
		
		
	}
	
	public static double sigmoid(double num){
		return 1/(1 + Math.pow(Math.E, (-1 * num)));
	}
	
	public static double[] sigmoid1D(double[] arr){
		double[] res = new double[arr.length];
		
		for(int i = 0; i < arr.length; i++){
			res[i] = 1/(1 + Math.pow(Math.E, (-1 * arr[i])));
		}
		return res;
	}
	
	public static double[][] sigmoid2D(double[][] arr){
		double[][] res = new double[arr.length][arr[0].length];
		
		for(int i = 0; i < arr.length; i++){
			res[i] = sigmoid1D(arr[i]);
		}
		return res;
	}
	
	public static double sigmoidPrime(double num){
		return sigmoid(num) * (1 - sigmoid(num));
	}
	
	public static double[] sigmoid1DPrime(double[] arr){
		double[] res = sigmoid1D(arr);
		
		for(int i = 0; i < arr.length; i++){
			res[i] = res[i] * (1 - res[i]);
		}
		return res;
	}
	
	public static double[][] sigmoid2DPrime(double[][] arr){
		double[][] res = sigmoid2D(arr);
		
		for(int i = 0; i < arr.length; i++){
			res[i] = sigmoid1DPrime(arr[i]);
		}
		return res;
	}
	
	public static double[][] scale(double[][] arr){
		double[][] res = new double[arr.length][arr[0].length];
		
		double max = maxOfArray(arr);
		
		for(int i = 0; i < arr.length; i++){
			for(int k = 0; k < arr[i].length; k++){
				res[i][k] = arr[i][k] / max;
			}
		}
		
		return res;
	}
	
	public static double[][] scale(double[][] arr, double max){
		double[][] res = new double[arr.length][arr[0].length];
		
		for(int i = 0; i < arr.length; i++){
			for(int k = 0; k < arr[i].length; k++){
				res[i][k] = arr[i][k] / max;
			}
		}
		
		return res;
	}
	
	public static double[] minusMatrix1D(double[] x, double[] y){
		double[] res = new double[x.length];
		
		for(int i = 0; i < res.length; i++){
			res[i] = x[i] - y[i];
		}
		return res;
	}
	
	public static double[][] oneDToTwoD(double[] arr){
		double[][] res = new double[arr.length][1];
		
		for(int i = 0; i < arr.length; i++){
			res[i][0] = arr[i];
		}
		
		return res;
	}

	public static double[][] minusMatrix2D(double[][] x, double[][] y) {
		
		double[][] res = new double[x.length][x[0].length];
		
		for(int i = 0; i < res.length; i++){
			for(int k = 0; k < res[0].length; k++){
				res[i][k] = x[i][k] - y[i][k];
			}
		}
		
		
		return res;
	}

	public static double[][] multiply(double[][] arr1, double[] arr2) {
		double[][] res = new double[arr1.length][arr1[0].length];
		
		for(int i = 0; i < res.length; i++){
			for(int k = 0; k < res[i].length; k++){
				res[i][k] = arr2[k] * arr1[i][k];
			}
		}
		
		return res;
	}
	
	public static double[][] transposeMatrix(double [][] m){
        double[][] temp = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }
	
	public static double[][] multiply(double[][] arr1, double[][] arr2){
		double[][] res = new double[arr1.length][arr1[0].length];
		
		for(int i = 0; i < res.length; i++){
			for(int k = 0; k < res[0].length; k++){
				res[i][k] = arr1[i][k] * arr2[i][k];
			}
		}
		
		return res;
	}
	
	public static double[] ravel(double[][] arr1){
		
		if(arr1.length == 1){
			return twoDToOneD(arr1);
		}
		
		double[] res = new double[arr1.length * arr1[0].length];
		
		int count = 0;
		
		for(int k = 0; k < arr1[0].length; k++){
			for(int i = 0; i < arr1.length; i++){
				res[count] = arr1[k][i];
				count++;
			}
		}
		
		return res;
	}
	
	public static double[][] concatenate(double[][] arr1, double[][] arr2){
		double[][] res = new double[arr1.length + arr2.length][arr1[0].length];
		
		for(int i = 0; i < arr1.length; i++){
			for(int k = 0; k < arr1[0].length; k++){
				res[i][k] = arr1[i][k];
			}
		}
		
		for(int i = 0; i < arr2.length; i++){
			for(int k = 0; k < arr2[0].length; k++){
				res[i + arr1.length][k] = arr2[i][k];
			}
		}
		
		return res;
	}
	
	public static double[] concatenate(double[] arr1, double[] arr2){
		double[] res = new double[arr1.length + arr2.length];
		
		for(int i = 0; i < arr1.length; i++){
			res[i] = arr1[i];
		}
		
		for(int i = 0; i < arr2.length; i++){
			res[i + arr1.length] = arr2[i];
		}
		
		return res;
	}
	
	
}
