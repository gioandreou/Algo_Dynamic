package algoDynamicProgramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.server.Operation;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("Enter the file name with extension, with is located in the same folder as program: ");
		long start = System.currentTimeMillis();
		Scanner input = new Scanner(System.in);
		File file = new File(input.nextLine());
        input = new Scanner(file);
        
        int row = input.nextInt();
        input.nextLine();
		int col = input.nextInt();
		
		input.nextLine();
		
		int[][] Diergasies = new int [row][col];
		int[][] Mixanes = new int [col][col];
		
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				Diergasies[r][c]=input.nextInt();
			   }
		}
		input.nextLine();
		
		for(int c=0; c<col; c++) {
			for(int i=0; i<col; i++){
				
				Mixanes[c][i]=input.nextInt();
			}
		}
		
		int [][]Final = new int[row][col];
		Final = dynamicProgramming(Diergasies,Mixanes); 
		
		System.out.println("The Final cost is:");
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				System.out.print(Final[r][c]+" ");
			   }System.out.println();
		}
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		
	}
	
	public static int[][] dynamicProgramming(int Operations[][], int Machines[][]){
		int oRows = Operations.length;
		int oCol = Operations[0].length;
		int i;
		int j;
		int [][]Costs = new int [oRows][oCol];
		
		
		for (i = 0; i < oRows; i++) {
            for (j = 0; j < oCol; j++) {
                Costs[i][j] = 0;
            }
        }
		for (j = 0; j < oCol; j++) {
            Costs[0][j]=Operations[0][j];
        }
		
			
		for(i=1; i<oRows; i++){
			for(j=0; j<oCol; j++){
				
				Costs[i][j]=findMin(Operations[i][j],Machines,Costs,i,j);
				
			}	
		}
		
		return Costs;
		
		
	}
	
	public static int findMin(int Op, int Ma[][],int Co[][],int i, int j){
		int minimum;
		
		int []routes = new int [Ma.length];
		
		for(int k=0; k<Ma.length; k++){
			routes[k]=Op+Co[i-1][k]+Ma[k][j];
		}
		
		 minimum=routes[0];
		
		for(int k=0; k<routes.length; k++){
			if(routes[k]<minimum){
				minimum=routes[k];
			}
		}
		
		return minimum;
		
	}


}