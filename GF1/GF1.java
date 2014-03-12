import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 * 
 */
public class GF1 {
	public static void main(String[] args){		
		//Input Files
		int[] ia = inputFile("input.txt");
		
		String outputText = "";
		outputText += addMod(ia[1], ia[2], ia[0]) + "\n";
		outputText += subtractMod(ia[1], ia[2], ia[0]) + "\n";
		outputText += multiplyMod(ia[1], ia[2], ia[0]) + "\n";
		outputText += divideMod(ia[1], ia[2], ia[0]);
		
		outputFile(outputText);
	}
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
	public static int addMod(int x, int y, int mod){
		int r = (x + y) % mod;
		if(r < 0){
			r += mod; 
		}
		return r;
	}
	public static int subtractMod(int x, int y, int mod){
		int r = (x - y) % mod;
		if(r < 0){
			r += mod; 
		}
		return r;
	}
	public static int multiplyMod(int x, int y, int mod){
		int r = (x * y) % mod;
		if(r < 0){
			r += mod; 
		}
		return r;
	}
	public static int divideMod(int x, int y, int mod){
		return (x * EEA(y, mod)[0]) % mod;
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