import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 	GF2 Algorithm
 *	
 *  @author Zachary Bensley & Liangliang Xiao(EEA Algorithm)
 */
public class GF2 {
	/**
	 * The main method of the GF1 class
	 */
	public static void main(String[] args){		
		//Input Files
		String[] sa = inputFile("input.txt");
		
		//Parse input file into correct variables
		int mod = Integer.parseInt(sa[0]); //prime number p
		int mXDegree = Integer.parseInt(sa[1]); //degree of irreducible polynomial
		int[] mX = getPoly(mXDegree, sa[2]); //coefficients of m(x) from leading to constant
		int fXDegree = Integer.parseInt(sa[3]); //degree of f(x)
		int[] fX = getPoly(fXDegree, sa[4]); // coefficients of f(x),from leading coefficient to the constant
		int gXDegree = Integer.parseInt(sa[5]); //degree of g(x)
		int[] gX = getPoly(gXDegree, sa[6]); // coefficients of g(x),from leading coefficient to the constant
		
		// fX _ gX
		String outputText = "Output Text: \n";
		outputText += addMod(fX, gX, mod) + "\n";		
		outputText +=subtractMod(fX, gX, mod) + "\n";
		//outputText += multiplyMod(fX, gX, mod) + "\n";
		System.out.println(outputText);
		/*
		String divide = divdeMod(fX, Gx, mod);
		String outputText = "";
		outputText += addMod(ia[1], ia[2], ia[0]) + "\n";
		outputText += subtractMod(ia[1], ia[2], ia[0]) + "\n";
		outputText += multiplyMod(ia[1], ia[2], ia[0]) + "\n";
		outputText += divideMod(ia[1], ia[2], ia[0]);
				
		outputFile(outputText);
		System.out.println(EEA(2,5)[0]);
		*/
	}
	/**
	 * Inputs the selected file and stores it in an integer array
	 *
	 * @param	String	fileName	The name of the file to be inputted.
	 *
	 * @return	int[]	Returns an integer array with the contents of the selected file
	 */
	public static String[] inputFile(String fileName){
		String[] sa = new String[7];
		String line = "";
		
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            int i = 0;
            while((line = in.readLine()) != null){
            	System.out.println("Line: " + line);
                sa[i] = line;
                i++;
            }
        }
        catch (IOException e) {
            System.out.println("inputFile() ERROR");
        }
        finally {
            return sa;
        }
        
        
	}
	/**
	*  This method 
	*  
	*
	* @param int 	
	* @param int	
	*
	* @return int[]	
	*/ 
	public static int[] getPoly(int degree, String unparsedPoly){
		//Create an array of size degree
		int[] poly = new int[degree + 1];
		
		String[] sa = unparsedPoly.split(" ");
		String stud = "";
		for(String s: sa){
			stud += s + " ";
		}
			System.out.println(stud);
		//Fill array from size to 0 with unparsedPoly
		for(int i = 0; i <= degree; i++){
			poly[i] = Integer.parseInt(sa[i]);
		}
		//return polyArray
		return poly;
	}
	/**
	*  This method adds the x integer and the y integer and then performs modulo with
	*  the mod variable mod.
	*
	* @param int 	x	A number to be added
	* @param int	y	A number to be added
	* @param int	mod	The modulo value 
	*
	* @return int	Returns the result
	*/ 
	public static String addMod(int[] x, int[] y, int mod){
		//finds if x is larger than y
		if(x.length > y.length){
			int[] temp = y;
			y = x;
			x = temp;
		}
		
		//Create array of larger degree
		int[] outArray = new int[y.length];
		//while int i = degree > lowerDegree add into newArray
		int i = y.length - 1;
		while(i > x.length){
			outArray[i] = y[i];
			i--;
		}

		//while i >= 0 add both other arrays
		while(i >= 0){
			outArray[i] = x[i] + y[i];
			i--;
		}
		
		makeModPositive(outArray, mod);
		outArray = removeLeadingZeros(outArray);
	
		String s = "";
		for(int j = outArray.length - 1; j >= 0; j --){
			s += outArray[j] + " ";
		}
		return s;
	}
	
	/**
	*  This method subtracts the y integer from the x integer and then performs modulo with
	*  the mod variable mod.
	*
	* @param int 	x	The number being subtracted from 
	* @param int	y	The number subtracting from x
	* @param int	mod	The modulo value 
	*
	* @return int	Returns the result
	*/ 
	public static String subtractMod(int[] x, int[] y, int mod){
		//finds if x is larger than y
		if(x.length > y.length){
			int[] temp = y;
			y = x;
			x = temp;
		}
		
		//Create array of larger degree
		int[] outArray = new int[y.length];
		//while int i = degree > lowerDegree add into newArray
		int i = y.length - 1;
		while(i > x.length){
			outArray[i] = y[i];
			i--;
		}

		//while i >= 0 add both other arrays
		while(i >= 0){
			outArray[i] = x[i] - y[i];
			i--;
		}
		
		makeModPositive(outArray, mod);
		outArray = removeLeadingZeros(outArray);
	
		String s = "";
		for(int j = 0; j < outArray.length; j ++){
			s += outArray[j] + " ";
		}
		return s;
	}
	/**  This method multiplies the x integer and the y integer and then performs modulo with
	*  the mod variable mod.
	*
	* @param int 	x	A number to be multiplied
	* @param int	y	A number to be multiplied
	* @param int	mod	The modulo value 
	*
	* @return int	Returns the result
	*/ 
	public static String multiplyMod(int[] x, int[] y, int mod){
		//finds if x is larger than y
		if(x.length > y.length){
			int[] temp = y;
			y = x;
			x = temp;
		}
		
		//Create array of larger degree
		int[] outArray = new int[y.length];
		//while int i = degree > lowerDegree add into newArray
		int i = y.length - 1;
		while(i > x.length){
			outArray[i] = y[i];
			i--;
		}

		//while i >= 0 add both other arrays
		while(i >= 0){
			outArray[i] = x[i] * y[i];
			i--;
		}
		
		makeModPositive(outArray, mod);
	
		String s = "";
		for(int j = outArray.length - 1; j >= 0; j --){
			s += outArray[j] + " ";
		}
		return s;
	}
	/**
	*  This method divides the x integer by the y integer and then performs modulo with
	*  the mod variable mod. If y == 0, it throws an Arithmetic Exception.
	*
	* @param int 	x	The numerator
	* @param int	y	The denominator
	* @param int	mod	The modulo value 
	*
	* @return int	Returns the result
	*
	* @throw 	ArithmeticException	If the y is 0. Throws because cannot divide by 0.
	*/
	public static void foil(int[] f, int[]g){
	
	
	}
	/**
	*  This method divides the x integer by the y integer and then performs modulo with
	*  the mod variable mod. If y == 0, it throws an Arithmetic Exception.
	*
	* @param int 	x	The numerator
	* @param int	y	The denominator
	* @param int	mod	The modulo value 
	*
	* @return int	Returns the result
	*
	* @throw 	ArithmeticException	If the y is 0. Throws because cannot divide by 0.
	*/ 
	public static int divideMod(int x, int y, int mod){
		if(y == 0){
			throw new ArithmeticException("ERROR: Cannot divide by Zero");
		}
		int r = (x * EEA(y, mod)[0]) % mod;
		if (r < 0){
			r = r + mod;
		}
		return r;
	}	
	/** 
    * Algorithm EEA (Extended Euclidean algorithm)
    * 
    * @param a an (> 0) integer 
    * @param b another (>= 0) integer 
    * 
    * @return int array (u,v) satisfying u*a + v*b = gcd(a,b)   
    */
    public static int[] EEA(int a, int b){        
        if(b == 0){             
            return new int[]{1,0};                        
        }else{
            int q = a/b; int r = a%b;
            int[] R = EEA(b,r);
            return new int[]{R[1], R[0]-q*R[1]};
        }
    }
	/** 
    * Makes mod positive
    * 
    * @param a int[] The array to be made positive.
    * @param mod The modulo value for the equation.
 
    */
    public static void makeModPositive(int[] a, int mod){        
    	for(int i = 0; i < a.length; i++){
    		a[i] = a[i] % mod;
    	}
    	for(int j = 0; j < a.length; j++){
    		if(a[j] < 0){
    			a[j] = a[j] + mod;
    		}
    	}
    }
    
	/** 
    * Remove leading zeroes
    * 
    * @param a an (> 0) integer  
    */
    public static int[] removeLeadingZeros(int[] a){ 
		int i = 0;
		boolean swap = false;
		System.out.println("Test: " + a[0]);
		System.out.println("Test: " + a[1]);
		while(i < a.length && a[i] == 0){
			System.out.println("Tada");
			swap = true;
			++i;
		}
		if(swap){
			int[] newA = new int[a.length - i];
			while(i < a.length){
				System.out.println("Here");
				newA[i-1] = a[i]; 
				i++;
			}

			return newA;
		}
		return a;
    }
	/**
	*  This method outputs the specified string to output.txt file in the same folder 
	*
	* @param String outputString The string to be saved to output.txt
	*/    
	public static void outputFile(String outputString){
        PrintWriter out = null;
        try {
            out = new PrintWriter("output.txt");
            out.print(outputString);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GF2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
}