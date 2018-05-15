package algoDynamicProgramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.rmi.server.Operation;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("Enter the file name with extension, with is located in the same folder as program: ");
		Scanner input = new Scanner(System.in);
		File file = new File(input.nextLine());
        input = new Scanner(file);
        
        int row = input.nextInt();
        input.nextLine();
		int col = input.nextInt();
		
		input.nextLine();
		//hg
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
		
		for(int r=0; r<row; r++ ) {
			for(int c=0; c<col; c++) {
				System.out.print(Diergasies[r][c]);
			   }System.out.println();
		}
		System.out.println();
		for(int c=0; c<col; c++) {
			for(int i=0; i<col; i++) {
				System.out.print(Mixanes[c][i]);
			}System.out.println();
		}
		
		int [][]Final = new int[row][col];
		Final = dynamicProgramming(Diergasies,Mixanes); 
		
		System.out.println("The Final cost is:");
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				System.out.print(Final[r][c]+" ");
			   }System.out.println();
		}		
		
	}
	
	public static int[][] dynamicProgramming(int Operations[][], int Machines[][]){
		int oRows = Operations.length;
		int oCol = Operations[0].length;
		int i;
		int j;
		int [][]Costs = new int [oRows][oCol];
		
		System.out.println("row and col"+oRows+""+oCol+"amd"+Operations.length+""+Operations[0].length);
		
		for (i = 0; i < oRows; i++) {
            for (j = 0; j < oCol; j++) {
                Costs[i][j] = 0;
            }
        }
		for (j = 0; j < oCol; j++) {
            Costs[0][j]=Operations[0][j];
            System.out.print("cost string"+Costs[0][j]);
        }
		
		
	
		for(i=1; i<oRows; i++){
			for(j=0; j<oCol; j++){
				System.out.println("testing for Cost:"+i+" "+j);
				Costs[i][j]=findMin(Operations,Machines,Costs,i,j);
				System.out.println("cost is"+Costs[i][j]);
			}	
		}
		
		return Costs;
		
		
	}
	
	public static int findMin(int Op[][], int Ma[][],int Co[][],int i, int j){
		int minimum;
		
		int []routes = new int [Ma.length];
		
		for(int k=0; k<Ma.length; k++){
			routes[k]=Op[i][j]+Co[i-1][k]+Ma[k][j];
			System.out.println("routes no."+k+" for Op."+Op[i][j]+" +Co."+Co[i-1][k]
					+" +Ma."+Ma[k][j]+" ="+routes[k]);
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