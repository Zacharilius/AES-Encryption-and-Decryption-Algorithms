import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 * 	GF1 Algorithm
 *	
 *  @author Zachary Bensley & Liangliang Xiao(EEA Algorithm)
 */
public class GF1 {
	/**
	 * The main method of the GF1 class
	 */
	public static void main(String[] args){		
		//Input Files
		int[] ia = inputFile("input.txt");
		
		String outputText = "";
		outputText += addMod(ia[1], ia[2], ia[0]) + "\n";
		outputText += subtractMod(ia[1], ia[2], ia[0]) + "\n";
		outputText += multiplyMod(ia[1], ia[2], ia[0]) + "\n";
		outputText += divideMod(ia[1], ia[2], ia[0]);
				
		outputFile(outputText);
		System.out.println(EEA(2,3)[0]);
	}
	/**
	 * Inputs the selected file and stores it in an integer array
	 *
	 * @param	String	fileName	The name of the file to be inputted.
	 *
	 * @return	int[]	Returns an integer array with the contents of the selected file
	 */
	public static int[] inputFile(String fileName){
		int[] ia = new int[3];
		String line = "";
		
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            int i = 0;
            while((line = in.readLine()) != null){
                ia[i] = Integer.parseInt(line);
                i++;
            }
        }
        catch (IOException e) {
            System.out.println("inputFile() ERROR");
        }
        finally {
            return ia;
        }
        
        
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
	public static int addMod(int x, int y, int mod){
		int r = (x + y) % mod;
		if(r < 0){
			r += mod; 
		}
		return r;
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
	public static int subtractMod(int x, int y, int mod){
		int r = (x - y) % mod;
		if(r < 0){
			r += mod; 
		}
		return r;
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
	public static int multiplyMod(int x, int y, int mod){
		int r = (x * y) % mod;
		if(r < 0){
			r += mod; 
		}
		return r;
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
            Logger.getLogger(GF1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
}