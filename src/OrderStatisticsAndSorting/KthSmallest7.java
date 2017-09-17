package OrderStatisticsAndSorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KthSmallest7 {
	static int indexvalue = 0;
	static int pivotvalue = 0;

	public static int ksmallest(int[] array, int left, int right, int k) {
		//algorithm if number of chunks is 7

		int numberofelements = (right - left) + 1;
		int division = 0; 
		
		//deciding the number of division

		if (numberofelements % 7 == 0)
			division = numberofelements / 7;

		else
			division = (numberofelements / 7) + 1;
		
		// variable to insert all the medians

		int[] medians = new int[division];

		int i = 1;
		if (numberofelements >= 7) {
			int x = 0;
			int y = 6;
			//for loop to iterate over each chunk

			for (i = 1; i <= numberofelements / 7; i++) {

				Quicksort.quickSort(array, x, y);
				
				// consider the middle elements


				int z = (int) Math.ceil((x + y) / 2);

				medians[i - 1] = array[z];
				
				//increment x and y to sort next set of elements

				x = x + 7;
				y = y + 7;

			}
			//Do quicksort and add the median for the remaining elements 

			if ((i - 1) * 7 < numberofelements) {
				Quicksort.quickSort(array, ((i - 1) * 7), right);
				medians[i - 1] = array[((i - 1) * 7) + (int) Math.ceil(((numberofelements - 1) - ((i - 1) * 7)) / 2)];

			}
		} else {
			Quicksort.quickSort(array, left, right);
			medians[i - 1] = array[(left + right) / 2];

		}
		for (int m = 0; m < medians.length; m++)
			
	 //logic for finding median of median

			
		if (medians.length > 2) {

			Quicksort.quickSort(medians, 0, medians.length - 1);

		} else
			

			for (int j = left; j <= right; j++) {
				if (array[j] == pivotvalue) {
					indexvalue = j;
					break;
				}
			}
		if (k > 0 && k <= (right - left) + 1) {
			
			//swap and carryout partition.


			swap(array, left + indexvalue, right);
			int p=partition(array, left, right);

			
			if (p - left == k - 1)
				return array[p];
			else if (p - left > k - 1)
				return ksmallest(array, left, p - 1, k);
			else
				return ksmallest(array, p + 1, right, k - p + left - 1);
		} else {
			return 1;
		}
	}

	//swap the element

	public static void swap(int[] array, int i, int j) {
		int value = 0;
		value = array[i];
		array[i] = array[j];
		array[j] = value;
	}

	
	//carry out partition
	static int partition(int array[], int left, int right) {
		int x = 0, i = 0;
		x = array[right];
		i = left;
		for (int j = left; j <= right - 1; j++) {
			if (array[j] <= x) {
				swap(array, i, j);
				i++;
			}
		}
		swap(array, i, right);
		return i;
	}
	
	
	// main program to take input and call the method

	public static void main(String arg[]) throws NumberFormatException, IOException {
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
		System.out.println("Enter the value of k");
		int k = Integer.parseInt(inp.readLine());
		String FILENAME = "/Users/deekshabhat/Documents/Test_Cases/SetOf100(1).txt";
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
		ksmallest(inputArray, 0, inputArray.length - 1,k);
		long time = System.nanoTime() - start; // ending timer
		System.out.println("time taken:" + time + " nano second");
		br.close();
		System.out.println("started writing to the file");
		File newFile = createNewFile(file.getParent());
		FileWriter fw = new FileWriter(newFile);
		int arrayLength = inputArray.length;
		
			fw.write(inputArray[k-1] + "\n");

		
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
