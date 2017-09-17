package OrderStatisticsAndSorting;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class QuickWithInsert {
	public static int y;
	
	public static void quickWithInsert(int[] array, int left, int right) {
		int p = 0;
		if((right-left)>y){
			p=Quicksort.partition(array, left, right);
			quickWithInsert(array, left, p);
			quickWithInsert(array, p + 1, right);
			
		}
		else{
			insertionSort(array,left,right);
		
		}
	}
	
	//Insertion sort if the value is smaller then y
	public static void insertionSort(int[] array, int left, int right){
		int i,j;
		

		for (i = left + 1; i <right+1; i++) {
			int element = array[i];
			j = i;

			while (j > left && array[j - 1] >= element) {
				swap(array,j,j-1);
				--j;
			}
		}
		
	}
	
	public static int partition(int[] array, int left, int right) {
		int pivot = array[left];
		int temp=left;
		int i = 0, j = 0;
		i = left-1;
		j = right+1;
		
		// iterate over the entire loop
		while (true) {
			i++;
			
			//if the value in the ith position is greater then pivot then i should stop right there else increment it.
			while (i<right && array[i] < pivot)
				i++;
			j--;
			
			//if the value in the jth position is lesser then pivot then j should stop right there else increment it.

			while (j>left && array[j] > pivot)
				j--;
			
			
			//until i is lesser than j swap i and j

			if (i < j)
			{
				swap(array, i, j);
				
			}
			else
			{
				//swap with the pivot element
				swap(array,temp,j);
				return j;
			}

		}
		

	}

	//swap elements in the position i and j.
	public static void swap(int[] array, int i, int j) {
		int value = 0;
		value = array[i];
		array[i] = array[j];
		array[j] = value;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
		System.out.println("Enter the value of y");
		y = Integer.parseInt(inp.readLine());
		String FILENAME = "/Users/deekshabhat/Documents/Test_Cases/SetOf100000(3).txt";
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
		quickWithInsert(inputArray, 0, inputArray.length - 1);
		long time = System.nanoTime() - start; // ending timer
		System.out.println("time taken:" + time + " nano second");
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
