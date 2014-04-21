package projectAES;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/** 
* This class implements the GF2.
*
* @author Zachary Bensley
*/

public class AES{
	private static int[] mX;
	
	public static void main(String[] args) {
		String[] sa = inputFile("input.txt");
		System.out.println(toString(sa));
		
		//Make sa[0] a polynomial
		System.out.println(sa[0]);
		mX = getPoly(sa[0]);
		
		//Add into an array of size 16 every 2 bits for sa[1 through 3]
		//sa[1] = key
		String[] key = breakIntoPairs(sa[1]);
		//sa[2] = plaintext
		String[] plaintext = breakIntoPairs(sa[2]);
		//sa[3] = ciphertext
		String[] ciphertext = breakIntoPairs(sa[3]);
		System.out.println("Key: " + toString(key));
		System.out.println("Plaintext: " + toString(plaintext));
		System.out.println("Ciphertext: " + toString(ciphertext));
		
		//Key Expansion
		//System.out.println("Length: " + keyExpansion(key).length);

		
		System.out.println("Testing: ");
		//String[] testKey = breakIntoPairs("0f1571c947d9e8590cb7add6af7f6798");
		//System.out.println(toString(keyExpansion(testKey)));
		String s = "af7f6798";
		System.out.println(s);
		System.out.println("rotWord: " + toString(rotWord(s)));
		System.out.println("subWord: " + subWord(rotWord(s)));
		System.out.println("rCon: " + rCon(4));
		System.out.println("XOR: " + XORStrings("10", "2"));
		System.out.println("XOR: " + XORStrings("1000000", "d2854679"));
		
	}
    /** 
    * inputFile Receives the parameter  filename that is the name of the file to be input.
    * 
    * @param fileName the name of the file to be input
    *
    * @return polynomial an array containing the polynomial
    *
    */
	public static String[] inputFile(String fileName){
		String[] sa = new String[4];
		String line = "";
		//Added to file location
		fileName = "/Users/zacharybensley/Dropbox/COSC_620_SecurityInComputing/Assignments/8AES_Project/" + fileName;
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            int i = 0;
            while((line = in.readLine()) != null){
            	//System.out.println("Line: " + line);
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
    * toString Receives an array and prints the polynomial according to the following format:
    * ax^2 + bx + c = a b c
    *
    * @param int[] outArray The array to be formatted to a string
    *
    * @return A formatted string that represents the outArray polynomial
    */
    public static String toString(String[] outArray){
    	String s = "";
		for(int k = 0; k < outArray.length; k++){
			s += outArray[k] + " ";
		}
		return s;
    }
    public static String toString(int[] outArray){
    	String s = "";
		for(int k = 0; k < outArray.length; k++){
			s += outArray[k] + " ";
		}
		return s;
    }
	public static int[] getPoly(String unparsedPoly){
		//get degree by counting spaces
		int space = 0;
		System.out.println(unparsedPoly.length());
		for(int j = 0; j < unparsedPoly.length(); j++){
			if(unparsedPoly.charAt(j) == ' ' && j + 1 < unparsedPoly.length()){
				space++;
			}
		}
		
		//Create an array of size degree
		int[] poly = new int[space + 1];
		System.out.println("Poly Length: " + poly.length);
		String[] sa = unparsedPoly.split(" ");
		String stud = "";
		for(String s: sa){
			stud += s + " ";
		}
		//Fill array from size to 0 with unparsedPoly
		for(int i = 0; i < space + 1; i++){
			poly[i] = Integer.parseInt(sa[i]);
		}
		//return polyArray
		return poly;
	}
	public static String[] breakIntoPairs(String unparsedPoly){
		String[] outArray = new String[16];
		int j = 0;
		for(int i = 0; i < outArray.length; i++){
			outArray[i] = unparsedPoly.substring(j, j+2);
			j += 2;
		}
		return outArray;
	}
	public static String[] keyExpansion(String[] key){
		String[] words = new String[44];
		String temp = "";
		for(int i = 0; i < 4; i++){
			words[i] = key[4 * i] + key[4 * i + 1] + key[4 * i + 2] + key[4 * i + 3];	
		}
		for(int j = 4; j < 44; j++){
			temp = words[j - 1];
			if(j % 4 == 0){
				temp = XORStrings(subWord(rotWord(temp)), rCon(j)); //XOR with rCON(i/4);
			}
			words[j] = words[j - 4]; //xor with temp
		}
		return words;
	}
	public static String[] rotWord(String wordStr){
		String[] word = new String[4];
		word[0] = wordStr.substring(0, 2);
		word[1] = wordStr.substring(2, 4);
		word[2] = wordStr.substring(4, 6);
		word[3] = wordStr.substring(6, 8);

		
		String temp = word[0];
		for(int i = 0; i < word.length - 1; i++){
			word[i] = word[i+1];
		}
		word[3] = temp;
		return word;
	}
	public static String XORStrings(String word1, String word2){
		String outWord = "";
		
		while(word1.length() > word2.length()){
			word2 = "0" + word2;
		}
		while(word2.length() > word1.length()){
			word1 = "0" + word1;
		}
		for(int i = 0; i < word1.length() - 1; i += 2){
			outWord += Integer.toHexString(Integer.parseInt(word1.substring(i, i + 2), 16) ^ Integer.parseInt(word2.substring(i, i + 2), 16));
			
		}
		//newWord = Integer.toString(Integer.parseInt(word1, 16) ^ Integer.parseInt(word2, 16));

		/*
		for(int i = 0; i < word1.length(); i += 2){
			int word1Bin = (Integer.parseInt(word1.substring(i, i + 1), 16));
			int word2Bin = (Integer.parseInt(word2.substring(i, i + 1), 16));
			int result = word1Bin ^ word2Bin;
			System.out.println(result);
		}	
		*/
		return outWord;
		
	}
	public static String rCon(int i){
		int j = i / 4;
		String s = "000000";
//		String jMult = Integer.toBinaryString(j);
		int RC = RC(j);
		String RC_Hex = Integer.toHexString(RC);
 
		System.out.println("RC: " + RC);
		return RC_Hex + s;
	}
	public static int RC(int j){
		//base case
		if(j == 1){
			return 1;	
		}
		//multiply binary representation of position j * position j -1
		int[] two = {1, 0};
		return convertPolyToInt(PLDA(multiply(two, convertBinToPoly(Integer.toBinaryString(RC(j - 1))), 2), mX, 2)[1]);
	}
	public static int[] convertBinToPoly(String binary){
		int[] poly = new int[binary.length()];
		for(int i = 0; i < binary.length(); i++){
			poly[i] = Integer.parseInt(binary.substring(i, i+1));
		}
		//System.out.println("Poly: " + toString(poly));
		return poly;
		
	}

	public static int convertPolyToInt(int[] poly){
		String s = "";
		for(int i = 0; i <poly.length; i++){
			s+= poly[i];	
		}
		//System.out.println("s: " + s);

		return Integer.parseInt(s, 2);
	}
	public static int[] hexPairToColVector(String hexPair){
		String s = Integer.toBinaryString(Integer.parseInt(hexPair, 16));
		System.out.println("s: " + s);
		while(s.length() < 8){
			s = "0" + s;
		}
		int[] outArray = convertBinToPoly(s);
		System.out.println("outArray: " + toString(outArray));
		/*
		for(int i = 0; i < outArray.length / 2; i++) {
			  int temp = outArray[i];
			  outArray[i] = outArray[outArray.length - 1 - i];
			  outArray[outArray.length - 1 - i] = temp;
			}
		System.out.println("outArray: " + toString(outArray));
		*/
		return outArray;
	}
	public static String subWord(String[] words){
		int[][] defaultMatrix=	{{1,1,1,1,1,0,0,0},
				{0,1,1,1,1,1,0,0},
				{0,0,1,1,1,1,1,0},
				{0,0,0,1,1,1,1,1},
				{1,0,0,0,1,1,1,1},
				{1,1,0,0,0,1,1,1},
				{1,1,1,0,0,0,1,1},
				{1,1,1,1,0,0,0,1}};

		int[] addMatrix = 		{1,1,0,0,0,1,1,0};	
		System.out.println("Start: " + toString(words));
		
		
		int[][] columnVec = new int[1][8];
		for(int i = 0; i < words.length; i++){
			columnVec[0][i] = Integer.parseInt(words[i],16);
		}
		//System.out.println("cV: " + toString(columnVec[0]));
		
		//System.out.println("defaultMatrix: \n" + toString(defaultMatrix));
		//System.out.println("columnVec: \n" + toString(columnVec));
		//System.out.println("addMatrix: \n" + toString(addMatrix));

		int[]  multMatrix = matrixMultiplication(columnVec, defaultMatrix, addMatrix);	
		//System.out.println("Out matrix: \n" + toString(multMatrix));
		
		String s = "";
		for(int j = multMatrix.length - 1; j >= 0; j--){
			s += multMatrix[j];
		}
		return Integer.toHexString(Integer.parseInt(s, 2));

	}
	public static void inverseSubWord(int[] words){		
		int[][] columnVec = new int[1][8];
		for(int i = 0; i < words.length; i++){
			columnVec[0][i] = words[i];
		}
		
		int[][] defaultMatrix=	{{0,1,0,1,0,0,1,0},
								{0,0,1,0,1,0,0,1},
								{1,0,0,1,0,1,0,0},
								{0,1,0,0,1,0,1,0},
								{0,0,1,0,0,1,0,1},
								{1,0,0,1,0,0,1,0},
								{0,1,0,0,1,0,0,1},
								{1,0,1,0,0,1,0,0}};
		
		int[] addMatrix = 		{1,0,1,0,0,0,0,0};
		
		
		//System.out.println("defaultMatrix: \n" + toString(defaultMatrix));
		//System.out.println("columnVec: \n" + toString(columnVec));
		//System.out.println("addMatrix: \n" + toString(addMatrix));

		int[]  multMatrix = matrixMultiplication(columnVec, defaultMatrix, addMatrix);	
		//System.out.println("Out matrix: \n" + toString(multMatrix));
	}
    public static int[] matrixMultiplication(int[][] matrixA, int[][] matrixB, int[] addMatrix){
    	int mA = matrixA.length;
    	int nA = matrixA[0].length;
    	int mB = matrixB.length;
    	int nB = matrixB[0].length;
    	
    	//if(nA != mB) throw new RuntimeException("ERROR: Incorrect matrix dimensions");
		int[][] matrixC = new int[1][8];
		for(int i = 0; i < mB; i++){
			for(int j = 0; j < nA; j++){
				matrixC[0][i] += matrixA[0][j] * matrixB[i][j];
    		}
    	}
		int[] outMatrix = new int[8];
		//System.out.println("Matrix c: \n" + toString(matrixC));
		for(int q = 0; q < matrixC[0].length; q++){
			outMatrix[q] = (matrixC[0][q] + addMatrix[q]) % 2;
		}
    	return outMatrix;	
    }
    public static String toString(int[][] matrix){
    	String outputString = "";
    	for(int i = 0; i < matrix.length; i++){
    		for(int j = 0; j < matrix[i].length; j++){
    			outputString += matrix[i][j] + " ";
    		
    		}
    		outputString += "\n";
    	}
    	
    	
    	return outputString;
    
    }
	 /** 
    * add	adds the fX polynomial from the gX polynomial and then calls the
    * makeModPositive and removeLeadingZeroes on the product array.
    *
    * @param fX a polynomial  
    * @param gX another polynomial
    * @param p a prime number   
    *
    * @return polynomial sum array
    */
	public static int[] add(int[] fX, int[] gX, int mod){
		int i = 0;
		int j = 0;
		int[] outArray;

		if(fX.length > gX.length){
			outArray = new int[fX.length];
			while(i < fX.length - gX.length){
				outArray[i] = fX[i++];
			}
			while(i < outArray.length){
				outArray[i] = fX[i++] + gX[j++];

			}
		}
		else if(gX.length > fX.length){
			outArray = new int[gX.length];
			//System.out.println("outArray.length: " + outArray.length);

			while(i < gX.length - fX.length){
				outArray[i] = gX[i++];
			}

			while(i < outArray.length){
				//System.out.println("i: " + i);
				//System.out.println("j: " + j);
				outArray[i] = fX[j++] + gX[i++];

			}		
		}
		else{
			outArray = new int[fX.length];
			while(j < outArray.length){
				outArray[j] = fX[j] + gX[j];
				j++;
			}
		}

		makeModPositive(outArray, mod);
		outArray = removeLeadingZeros(outArray);
	
		return outArray;
	}
    /** 
    * subtract	subtracts the gX polynomial from the fX polynomial and then calls the
    * makeModPositive and removeLeadingZeroes on the product array.
    *
    * @param fX a polynomial  
    * @param gX another polynomial
    * @param p a prime number   
    *
    * @return polynomial difference array
    */
	public static int[] subtract(int[] fX, int[] gX, int mod){
		int i = 0;
		int j = 0;
		int[] outArray;
		//finds if x is larger than y
		if(fX.length > gX.length){
			outArray = new int[fX.length];
			while(i < fX.length - gX.length){
				outArray[i] = fX[i++] - 0;
			}


			while(i < outArray.length){
				outArray[i] = fX[i++] - gX[j++];

			}
		}
		else if(gX.length > fX.length){
			outArray = new int[gX.length];
			while(i < gX.length - fX.length){
				outArray[i] = 0 - gX[i++];
			}

			while(i < outArray.length){
				outArray[i] = fX[j++] - gX[i++];

			}		
		}
		else{
			outArray = new int[fX.length];
			while(j < outArray.length){
				outArray[j] = fX[j] - gX[j++];
			}
		}
		
		makeModPositive(outArray, mod);
		outArray = removeLeadingZeros(outArray);
	
		return outArray;
		}
	    /** 
	    * multiply	multiplies the two polynomials fX and gX and then calls the
	    * makeModPositive and removeLeadingZeroes on the product array.
	    *
	    * @param fX a polynomial  
	    * @param gX another polynomial
	    * @param p a prime number   
	    *
	    * @return polynomial sum array
	    */
	    public static int[] multiply(int[] fX, int[] gX, int p){
			//Find size of new array
			int[] multArr = new int[(fX.length - 1) + (gX.length - 1) + 1];
			for(int i = fX.length - 1; i >= 0; i--){
				for(int j = gX.length - 1; j >= 0; j--){
					multArr[i + j] += fX[i] * gX[j];
				}
			}
			makeModPositive(multArr, p);//just added
			multArr = removeLeadingZeros(multArr);//just added
			return multArr;
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
	    * Performs the modulo operation on the a paramter. If the modulo value is negative, it adds the 
	    * modulo value to it again.
	    * 
	    * @param a int[] The array to be made positive and to have the modulo operation performed.
	    * @param mod The modulo value for the equation.
	 
	    */
	    public static int[] makeModPositive(int[] a, int mod){        
	    	for(int i = 0; i < a.length; i++){
	    		a[i] = a[i] % mod;
	    	}
	    	for(int j = 0; j < a.length; j++){
	    		if(a[j] < 0){
	    			a[j] = a[j] + mod;
	    		}
	    	}
	    	return a;
	    }
	    
		/** 
	    * Removes the leading zeroes in an array. For example, if the array has the terms 0 0 1. It will 
	    * remove 0 and 0 and leave just 1. the array is only 0s, it will leave 1 0. 
	    * 
	    * @param int[] a is a integer that may require its leading terms removed. 
	    * 
	    * @return An integer array with leading zeros removed
	    */
	    public static int[] removeLeadingZeros(int[] a){ 
			int i = 0;
			boolean swap = false;
			//{0,0,1}
			//i = 0 & a[0] = 0
			//i = 1 & a[0] = 0
			//i = 2
			while(i < a.length && a[i] == 0){
				swap = true;
				if(i++ == a.length - 1){
					return new int[] {0};
				}
			}

			if(swap){
				int[] newA = new int[a.length - i];
				//a.length = 3 - 2 = 1;
				int j = 0;
				while(i < a.length){
					newA[j++] = a[i++]; 
				}
				return newA;
			}
			return a;
	    }

	    /**
	    * PLDA (Polynomial long division algorithm) over GF(p)
	    *
	    * @param n(x) a polynomial 
	    * @param d(x) a non-zero polynomial 
	    * @param p a prime number 
	    *
	    * @return quotient q(x) and remainder r(x) such that n(x) = q(x)*d(x) + r(x) mod p where deg(r(x)) < deg(d(x))
	    */
	    public static int[][] PLDA(int[] nX, int[] dX, int p){
			makeModPositive(nX, p);
			makeModPositive(dX, p);
			//System.out.println("nX: " + toString(nX)); //test
			//System.out.println("dX: " + toString(dX)); //test
			int[] qX = {0};
			int[] rX = nX.clone();
			int i = 0; //TEST
			while(rX[0] != 0 && (rX.length-1) >= (dX.length-1) && i < 5){
				//System.out.println("Loop" + i);
				int[] tX = leadDivision(rX, dX);
				//System.out.println("tX.length: " + tX.length);
				//System.out.println("tx: " + toString(tX)); //test
				qX = add(qX, tX, p);
				rX = subtract(rX, multiply(tX, dX, p), p);	
				i++; //TEST
			}
			//System.out.println("{qX, rX}: " + toString(qX) + ", " + toString(rX)); //test
			return new int[][] {qX, rX};
	    }
	    /** 
	    * leadDivision is used by the PLDA algorithm to calculate rX / dX. 
	    *
	    * @param rX a polynomial  
	    * @param dX another polynomial
	    *
	    * @return int[] Returns the result of dividing the leading term in rX by the leading term in dX
	    */	    
	    public static int[] leadDivision(int[] rX, int[] dX){
	    	if(dX.length > rX.length){
	    		throw new IllegalArgumentException("leadDivision: dX > rX"); 		
	    	}
			int[] outArr = new int[Math.abs((rX.length - 1) - (dX.length - 1)) + 1];
			if(rX[0] % dX[0] != 0){
				outArr[0] = EEA(rX[0], dX[0])[0];
			}
			else{
				outArr[0] = rX[0] / dX[0];
				
			}
			//System.out.println("leadDiv: " + toString(outArr));
			return outArr;
						
			//Write code that replaces all values after leading digit with 0s.
			//Then call PLDA on leading digits
			//Return resultOfPLDA[1];
	    }

	    /** 
	    * EEAP (Extended Euclidean Algorithm for Polynomials) over GF(p)
	    *
	    * @param a(x) a polynomial  
	    * @param b(x) another polynomial
	    * @param p a prime number   
	    *
	    * @return polynomial array (u(x),v(x)) satisfying u(x)*a(x) + v(x)*b(x) = gcd(a(x),b(x)) mod p
	    */
	    public static int[][] EEAP(int[] aX, int[] bX, int p){
	  	    aX = makeModPositive(aX, p);
	    	bX = makeModPositive(bX, p);
    		//System.out.println("aX: " + toString(aX));	    		
    		//System.out.println("bX: " + toString(bX));
	    	if(bX[0] == 0){
	    		//System.out.println("1 / aX[0]: 1 /" + aX[0]);
	    		if(1 % aX[0] != 0){
	    			int s = EEA(aX[0], p)[0];
	    			//System.out.println("s: " + s);
	    			return new int[][] {{s}, {0}};
	    		}
	    		return new int[][] {{1 / aX[0]}, {0}};//{[1/aX[0], 0]};  ///NNEEEEDDDSSSS FIXED
	    	}
	    	else{
	    		int[][] Q = PLDA(aX, bX, p);
	    		//System.out.println("Q[0]: " + toString(Q[0]));
	    		//System.out.println("Q[1]: " + toString(Q[1]));

	    		int[] qX = removeLeadingZeros(Q[0]);
	    		int[] rX = removeLeadingZeros(Q[1]);
	    		//System.out.println("qX: " + toString(qX));
	    		//System.out.println("rX: " + toString(rX));
	    		int[][] R = EEAP(bX, rX, p);
	    		//System.out.println("Return: " + toString(makeModPositive(R[1], p)) + ", " + toString(subtractMod(R[0], multiply(qX, R[1], p),p)));
		    	return new int[][] {makeModPositive(R[1], p), subtract(R[0], multiply(qX, R[1], p),p)};
	    	}
	    }	    
	    /** 
	    * outputFile outputs the parameter string to the current file directory with the name output.txt
	    *
	    * @param String	outputString	The string to be saved to the to the file 
  	    */
		public static void outputFile(String outputString){
	        PrintWriter out = null;
	        try {
	            out = new PrintWriter("outputTest.txt");
	            out.print(outputString);
	        } catch (FileNotFoundException ex) {
	            Logger.getLogger(AES.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	            out.close();
	        }
	    }   
}
