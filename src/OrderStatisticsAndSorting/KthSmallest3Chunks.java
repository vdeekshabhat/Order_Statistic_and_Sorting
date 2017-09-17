package OrderStatisticsAndSorting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class KthSmallest3Chunks {
	static int indexvalue = 0;
	static int pivotelement = 0;

	public static int ksmallest(int[] array, int left, int right, int k) {
		//algorithm if number of chunks is 3
		int numberofelements = (right - left) + 1;
		int division = 0; 
		
		//deciding the number of division
		if (numberofelements % 3 == 0)
			division = numberofelements / 3;

		else
			division = (numberofelements / 3) + 1;
		
		// variable to insert all the medians
		int[] setofmedians = new int[division];
		
		//sort the elements

		int i = 1;
		if (numberofelements >= 3) {
			int firstvalue = 0;
			int lastvalue = 2;
			
			//for loop to iterate over each chunk

			for (i = 1; i <= numberofelements / 3; i++) {

				Quicksort.quickSort(array, firstvalue, lastvalue);

				int middlevalue = (int) Math.ceil((firstvalue + lastvalue) / 2);

				setofmedians[i - 1] = array[middlevalue];
				firstvalue = firstvalue + 3;
				lastvalue = lastvalue + 3;

			}

			if ((i - 1) * 3 < numberofelements) {
				Quicksort.quickSort(array, ((i - 1) * 3), left);
				setofmedians[i - 1] = array[((i - 1) * 3) + (int) Math.ceil(((numberofelements - 1) - ((i - 1) * 3)) / 2)];

			}
		} else {
			Quicksort.quickSort(array, left, right);
			setofmedians[i - 1] = array[(left + right) / 2];

		}
		for (int m = 0; m < setofmedians.length; m++)
			
		if (setofmedians.length > 2) {

			Quicksort.quickSort(setofmedians, 0, setofmedians.length - 1);

		} else

			for (int j = left; j <= right; j++) {
				if (array[j] == pivotelement) {
					indexvalue = j;
					break;
				}
			}
		if (k > 0 && k <= (right - left) + 1) {
			
			//finding pivot
			swap(array, left + indexvalue, right);
			int p= partition(array, left, right);
			
			

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
	//partitioning
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


	public static void swap(int[] array, int i, int j) {
		int value = 0;
		value = array[i];
		array[i] = array[j];
		array[j] = value;
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
