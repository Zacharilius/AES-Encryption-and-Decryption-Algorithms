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
		//String[] sa = inputFile("input.txt");
		String[] sa = inputFile("input.txt");

		
		//Parse input file into correct variables
		int mod = Integer.parseInt(sa[0]); //prime number p
		int mXDegree = Integer.parseInt(sa[1]); //degree of irreducible polynomial
		int[] mX = getPoly(mXDegree, sa[2]); //coefficients of m(x) from leading to constant
		int fXDegree = Integer.parseInt(sa[3]); //degree of f(x)
		int[] fX = getPoly(fXDegree, sa[4]); // coefficients of f(x),from leading coefficient to the constant
		int gXDegree = Integer.parseInt(sa[5]); //degree of g(x)
		int[] gX = getPoly(gXDegree, sa[6]); // coefficients of g(x),from leading coefficient to the constant
		
		//Add a check to see if they all have values
		
		// fX _ gX
		/*
		String outputText = "Output Text: \n";
		outputText += toString(addMod(fX, gX, mod)) + "\n";		
		outputText += toString(subtractMod(fX, gX, mod)) + "\n";
		outputText += toString(multiplyMod(multiply(fX, gX, mod), mX, mod)) + "\n";
		System.out.println(outputText);
		*/
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
		
		
		//Testing
		int[] lDivTest = leadDivision(new int[] {2,2,2}, new int[] {1,1,1});
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
	public static int[] addMod(int[] x, int[] y, int mod){
		int i = 0;
		int j = 0;
		int[] outArray;
		//finds if x is larger than y
		if(x.length > y.length){
			outArray = new int[x.length];
			while(i < y.length - 1){
				outArray[i] = x[i++];
			}
			while(i < outArray.length){
				outArray[i] = x[i++] + y[j++];

			}
		}
		else if(y.length > x.length){
			outArray = new int[y.length];
			while(i < x.length - 1){
				outArray[i] = y[i++];
			}

			while(i < outArray.length){
				System.out.println("outArray.length: " + outArray.length);
				System.out.println("x.length: " + x.length);
				System.out.println("y.length: " + y.length);

				outArray[i] = x[j++] + y[i++];

			}		
		}
		else{
			outArray = new int[x.length];
			while(j < outArray.length){
				outArray[j] = x[j] + y[j];
				j++;
			}
		}

		makeModPositive(outArray, mod);
		outArray = removeLeadingZeros(outArray);
	
		return outArray;
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
	public static int[] subtractMod(int[] x, int[] y, int mod){
		int i = 0;
		int j = 0;
		int[] outArray;
		//finds if x is larger than y
		if(x.length > y.length){
			outArray = new int[x.length];
			while(i < y.length - 1){
				outArray[i] = x[i++] - 0;
			}


			while(i < outArray.length){
				outArray[i] = x[i++] - y[j++];

			}
		}
		else if(y.length > x.length){
			outArray = new int[y.length];
			while(i < x.length - 1){
				outArray[i] = 0 - y[i++];
			}

			while(i < outArray.length){
				outArray[i] = x[j++] - y[i++];

			}		
		}
		else{
			outArray = new int[x.length];
			while(j < outArray.length){
				outArray[j] = x[j] - y[j++];
			}
		}
		
		makeModPositive(outArray, mod);
		outArray = removeLeadingZeros(outArray);
	
		return outArray;
	}

	/**  This method multiplies the x integer and the y integer and then performs modulo with
	*  the mod variable mod.
	*
	* @param int 	f	A number to be multiplied
	* @param int	g	A number to be multiplied
	* @param int	mod	The modulo value 
	*
	* @return int	Returns the result
	*/ 
	public static int[] multiply(int[] f, int[] g, int mod){
		//Find size of new array
		int[] multArr = new int[(f.length - 1) + (g.length - 1) + 1];
		for(int i = f.length - 1; i >= 0; i--){
			for(int j = g.length - 1; j >= 0; j--){
				multArr[i + j] += f[i] * g[j];
			}
		}
		return multArr;
		
	}
	public static int[] multiplyMod(int[] multArray, int[] mX, int mod){
		//PLDA(outArray,mX,p)[1]; //Need remainder. See page 132 book PLDA(mX, outArray)
		int[] outArray = PLDA(mX, multArray, mod)[1];
		makeModPositive(outArray, mod);
		outArray = removeLeadingZeros(outArray);
		return outArray;
	
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
	public static int[] division(int[] fX, int[] gX, , int[] mX, int mod){
		//Compute 1 g(x)
		PLDA(gX, mX){
		
		}
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
		while(i < a.length && a[i] == 0){
			swap = true;
			++i;
		}
		if(swap){
			int[] newA = new int[a.length - i];
			while(i < a.length){
				newA[i-1] = a[i]; 
				i++;
			}
			return newA;
		}
		return a;
    }
    public static String toString(int[] outArray){
    	String s = "";
		for(int k = 0; k < outArray.length; k++){
			s += outArray[k] + " ";
		}
		return s;
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
	/**
	* PLDA (Polynomial long division algorithm) over GF(p)
	*
	* @param n(x) a polynomial 
	* @param d(x) a non-zero polynomial 
	* @param m(x) a ??????????
	* @param p a prime number 
	*
	* @return quotient q(x) and remainder r(x) such that n(x) = q(x)*d(x) + r(x) mod p where deg(r(x)) < deg(d(x))
	*/

	public static int[][] PLDA(int[] nX, int[] dX, int p){
		modPLDA(nX, p);
		modPLDA(dX, p);
		
		int[] qX = {0};
		int[] rX = nX;
		
		while(rX != null && rX.length >= dX.length){
			int[] tX = leadDivision(rX , dX);			
			qX = addMod(qX, tX, p);
			modPLDA(qX, p);
			rX = subtractMod(rX, multiply(tX, dX, p), p);
			modPLDA(rX, p);
		} 
		return new int[][] {qX, rX};	
	}

	private static int[] modPLDA(int[] ia, int p){
		int i = 0;
		while(i < ia.length){
			ia[i] %=  p;
			if(ia[i] < 0){
				ia[i] += p;
			}
			i++;
		} 
		return ia;
	}

	private static int[] leadDivision(int[] a, int[] b){
		System.out.println("a.length: " + a.length);
		System.out.println("b.length: " + b.length);
		int[] outArr = new int[Math.abs((a.length - 1) - (b.length - 1)) + 1];
		System.out.println("outArr.length: " + outArr.length);
		int mult;
		if(a[0] % b[0] != 0){
			mult =  EEA(a[0], b[0])[1];
			outArr[0] = mult * ;
		}
		else{
			mult[0] = a[0] / b[0];
		}
		return outArr;
	}
/*
	public static int[] EEAP(int[] aX, int[] bX, int p){
		modPLDA(aX, p);
		modPLDA(bX, p);
		
		int i = 0;
		boolean b = true;
		while(i < bX.length && b){
			if(bX[i] != 0){
				b = false;
			}
		} 
		if(b){ //Not right. did to compile
			return new int[] {1 / aX[0], 0};
		}
		else{
			int[][] Q = PLDA(aX, bX, p);
			int[] qX = Q[0];
			int[] rX = Q[1];
			int[] R = EEAP(bX, rX, p);
			int[] outR1 = modPLDA(R[1], p);
			int[] outR0 = subMod(R[0], multiplyMod(qX, R[1], p), p);
			return new int[] {outR1, outR0};
			//R[0]-q*R[1] mod p)
		}
		
		
	}
*/
/**
	PLDA(n(x), d(x))
		n(x) = n(x) mod p // perform mod p to every coefficient of n(x)
		d(x) = d(x) mod p // perform mod p to every coefficient of d(x) 
		(q(x), r(x)) <- (0, n(x))            
		while r(x) != 0 and deg(r(x)) >= deg(d(x)) do
			t(x) <- lead(r(x))/lead(d(x))	
					//lead() returns the leading term of a polynomial; the division requires EEA. 
				 	//E.g. if r(x) = 2x^2 + x + 1, d(x) = 3x + 2, and p = 7,   
				 	//then lead(r(x)) = 2x^2, lead(d(x)) = 3x,
				 	//thus t(x) = 2x^2/3x = (2/3)(x^2/x) = 3x, 
					//here it needs EEA to compute 2/3 mod 7 = 3.
				 
			(q(x), r(x)) <- (q(x) + t(x) mod p, r(x) - t(x)*d(x) mod p)			
		return (q(x), r(x))
*/
	
/** 
* EEAP (Extended Euclidean Algorithm for Polynomials) over GF(p)
*
* @param a(x) a polynomial  
* @param b(x) another polynomial
* @param p a prime number   
*
* @return polynomial array (u(x),v(x)) satisfying u(x)*a(x) + v(x)*b(x) = gcd(a(x),b(x)) mod p
*/
/**
	EEAP(a(x), b(x))
		a(x) = a(x) mod p // perform mod p to the coefficients of a(x)
		b(x) = b(x) mod p // perform mod p to the coefficients of b(x)
		if b(x) = 0 then
			return (1/(leading coefficient of a(x)), 0) // gcd(a(x),0) should be monic 
		else
			Q = PLDA(a(x), b(x))
			q(x) = Q[0] and r(x) = Q[1]
			R = EEAP(b(x), r(x))			
			return (R[1] mod p, R[0]-q*R[1] mod p)   
			
*/			
}