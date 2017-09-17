package OrderStatisticsAndSorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Quicksort {

	public static void quickSort(int[] array, int l, int r) {
		int p = 0;
		if (l < r) {
		p=partition(array, l, r);
		quickSort(array, l, p-1);
		quickSort(array, p + 1, r);
		}
		
	}
	/*
	 * This method is invoked recursively to partition the array centered at pivot.
	 */
	public static int partition(int[] array, int l, int r) {
		int elementToBeComparedOrPivot= array[l];
		int startIndex = l+1,lastIndex = r;
		/**
		 * This is the base case for the recursion.
		 */
		if(startIndex>lastIndex)
		     return lastIndex;
		/**
		 * Loop through the complete array from the startIndex Until the lastIndex performing comparison in order to swap the elements to be placed in the right position.
		 */
		while (startIndex<=lastIndex) {
			//moving from  the beginning of the array comparing the pivot and incrementing the index.
			if (array[startIndex] <= elementToBeComparedOrPivot){
				startIndex++;
			}
			//similarly moving from the end of the array comparing the pivot and decrement the index
			if (array[lastIndex] >= elementToBeComparedOrPivot){
				lastIndex--;
			}
			//The indexes are crossed and the elements are to be swapped.
			if (startIndex <= lastIndex){
				//Swap the elements
				swap(array, startIndex, lastIndex);
				
			}
		}
		//The array is completely traversed we need to place the pivot in the right place by swapping the elements
		swap(array, l, lastIndex);
		//return the index of the pivot
		return lastIndex;
	}

	public static void swap(int[] b, int i, int j) {
		int temp = 0;
		temp = b[i];
		b[i] = b[j];
		b[j] = temp;
	}
	
	public static void main(String[] args) throws IOException {
		String FILENAME = "/Users/deekshabhat/Documents/Test_Cases/10Input.txt";
		File file = new File(FILENAME);
		if (!file.exists()) {
			System.out.println("File not found");
			return;
		}
		BufferedReader br = new BufferedReader(new FileReader(FILENAME));
		ArrayList<Integer> input = new ArrayList<Integer>();
		try{
		if(br!=null){
			String s="";
			while((s=br.readLine()) != null){
				 input.add(Integer.valueOf(s));
			}
		}
		}finally{
			br.close();
		}
		int[] inputArray = new int[input.size()];
		//inputArray=input.toArray(inputArray);
		int i=0;
		for (int f : input) {
			inputArray[i++] =f;
		}

		
		long start = System.nanoTime(); // starting timer
		quickSort(inputArray, 0, inputArray.length - 1);
		long time = System.nanoTime() - start; // ending timer
		System.out.println("Time taken:" + time + " nano second");
		br.close();
		System.out.println("started writing to the file");
		File newFile = createNewFile(file.getParent());
		FileWriter fw = new FileWriter(newFile);
		int arrayLength = inputArray.length;
		for (int j = 0; j <arrayLength  ; j++) {
			fw.write(inputArray[j] + "\n");

		}
		System.out.println("Finished writing to the file");
		fw.close();		
		
	}
	private static File createNewFile(String parent) throws IOException {
		// TODO Auto-generated method stub
		File newOutputFile = new File(parent+"/output.txt");
		if(newOutputFile!= null && newOutputFile.exists()){
			return newOutputFile;
		}else{
			 newOutputFile.createNewFile();
			 return newOutputFile;
		}
	}

}
