package OrderStatisticsAndSorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KthSmallestLinear {

		public static int kthsmallestlinear(int[] array, int left, int right, int k) {
			
			//algorithm to find out kth smallest element in linear time
			int p = 0;
			if (k > 0 && k <= (right - left) + 1) {
				
				// find the pivot
				int n = right - left + 1;
				int pivotelement = (int) (Math.random()) % n;
				swap(array, left + pivotelement, right);
				p= partition(array, left, right);

				if (p - left == k - 1) {

					return array[p];
					
					//if the value is greater than k-1 then call kthsmallestlinear on left part of the array

				} else if (p - left > k - 1) {
					return kthsmallestlinear(array, left, p - 1, k);
					//else  call kthsmallestlinear on right part of the array

				} else {
					return kthsmallestlinear(array, p + 1, right, k - p + left - 1);
				}
			}

			else {
				return 1;
			}

		}

		// swapping the array
		public static void swap(int[] array, int i, int j) {
			int value = 0;
			value = array[i];
			array[i] = array[j];
			array[j] = value;
		}
		
		//partitioning the array.

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
		kthsmallestlinear(inputArray, 0, inputArray.length - 1,k);
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

