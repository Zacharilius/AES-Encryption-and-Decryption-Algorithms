/*
 *
 *
 */
import java.io.*;
public class polynomialCalculator{	
	public static void main(String[] args){
		//Read input file and store as string
		String inputFileString = inputFile();
		System.out.println(inputFileString);
		
		//format inputFileString
		int[][] formattedInputFileArray = formatInputFileString(inputFileString);
	
		//add, subtract, and multiply arrays
		String outputFileString = "";
		
		String add = addLines(formattedInputFileArray);
		outputFileString += add + "\n";
		
		String subtract = subtractLines(formattedInputFileArray);
		outputFileString += subtract + "\n";
		
		String multiply = multiplyLines(formattedInputFileArray);
		outputFileString += multiply + "\n";
		
		outputFile(outputFileString);
	}
	//Input File - Reads from current folder
	public static String inputFile(){
		String inputString = "";
		FileReader file = null;
		
		try {
			file = new FileReader("input(8).txt");
			BufferedReader reader = new BufferedReader(file);
			String line = "";
			//for(int i = 0: reader.readLine() != null; i++){
			//
			//}
			while((line = reader.readLine()) != null){
				inputString += line + "\n";
			}
		}
		catch (Exception e){
				throw new RuntimeException(e);
		}
		finally{
			if (file != null){
				try{
					file.close();
				}
				catch(IOException e){
					//Ignore
				}
			}
		}

		
		return inputString;
	}
	
	//Output File = Outputs to current folder
	public static void outputFile(String s){
		FileWriter output = null;
		try{
			output = new FileWriter("output1.txt");
			BufferedWriter writer = new BufferedWriter(output);
			writer.write(s);
			System.out.println("Here it is \n" + s);
		}
		catch(IOException e){
			System.out.println("Error: RuntimeException");
			throw new RuntimeException(e);
		} 
		finally{
			if(output != null){
				try{
					output.close();
				}
				catch(IOException e){
					//Ignore
				}
			}
		}
	}
	//Read File Into Array
	public static int[][] formatInputFileString(String s){
		String[] sa = s.split("\n");
		
		//Add catch in case sa.length is exactly 4
		
		System.out.println("formatInputFileString: ");
		
		//get f(x) degree
		int fDegree = Integer.parseInt(sa[0]);
		
		//get g(x) degree
		int gDegree = Integer.parseInt(sa[2]);
		
		//Reads into f(x) array
		String[] fa = sa[1].split(" ");
		
		//Reads into g(x) array
		String[] ga = sa[3].split(" ");
		
		//Makes sure same size arrays
		if(fa.length != ga.length){
			System.out.println("Improperly formatted input file");
		}	
		//convert from String to int array
		int[] faInt = new int[fDegree + 1];
		int[] gaInt = new int[gDegree + 1];
		for(int i = 0; i < fa.length; i++){
			faInt[i] = Integer.parseInt(fa[i]);
			gaInt[i] = Integer.parseInt(ga[i]);
			System.out.println(gaInt[i]);
		}
		
		int[][] returnIntArray = 
		{
		faInt,
		gaInt
		};
		
		return returnIntArray;
	}
	public static String addLines(int[][] arrays){		
		String addString = "";
		int[] fx = arrays[0];
		int[] gx = arrays[1];
		
		for(int i = 0; i < fx.length; i++){
			int number = fx[i] + gx[i];	
			addString += number + " ";
		}

		return addString;
	}
	public static String subtractLines(int[][] arrays){
		String subtractString = "";
		int[] fx = arrays[0];
		int[] gx = arrays[1];
		
		for(int i = 0; i < fx.length; i++){
			int number = fx[i] - gx[i];	
			subtractString += number + " ";
		}

		return subtractString;
	}
	public static String multiplyLines(int[][] arrays){
		String multiplyString = "";
		int[] fx = arrays[0];
		int[] gx = arrays[1];
		
		for(int i = 0; i < fx.length; i++){
			int number = fx[i] * gx[i];	
			multiplyString += number + " ";
		}

		return multiplyString;
	}

}