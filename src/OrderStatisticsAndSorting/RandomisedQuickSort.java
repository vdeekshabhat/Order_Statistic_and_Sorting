package OrderStatisticsAndSorting;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RandomisedQuickSort {
	public static void quickSort(int[] array, int l, int r) {
		Random rand = new Random();

		if (l<r) {
			int randomIndex = l+rand.nextInt(r-l+1);
			int p = partition(array, l, randomIndex, r);

			quickSort(array, l, p);
			quickSort(array, p + 1, r);

		}
	}
	public static int partition(int[] array, int left, int rand, int right) {
		int x = 0;
		int i = left - 1;
		int j = right + 1;
		x = array[rand];
		while (true) {
			// iterate over the entire loop

			i++;
			
			//if the value in the ith position is greater then pivot then i should stop right there else increment it.

			while (i < right && array[i] < x)
				i++;
			j--;
			
			//if the value in the jth position is lesser then pivot then j should stop right there else increment it.

			while (j > left && array[j] > x)
				j--;
			
			//until i is lesser than j swap i and j

			if (i < j)
				swap(array, i, j);
			else
				return j;

		}
	}

	private static void swap(int[] a, int i, int j) {
		int temp = 0;
		temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void main(String[] args) throws IOException {
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
		quickSort(inputArray, 0, inputArray.length - 1);
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
