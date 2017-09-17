package OrderStatisticsAndSorting;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

	public class MedianOf3Heuristics {
	
		public static void quickSort(int[] array, int l, int r) {
			int randomNumber;
			Random rand = new Random();
			if (l<r) {
				int[] randomIndex=new int[3];
				for(int i=0;i<3;i++){
					randomIndex[i] = l+rand.nextInt(r-l+1);
				}
				
				//find median of 3 random numbers.
				
				if (randomIndex[0] > randomIndex[1]) {
					  if (randomIndex[1] > randomIndex[2]) {
						  randomNumber=(int) randomIndex[1];
					  } else if (randomIndex[0] > randomIndex[2]) {
						  randomNumber=(int) randomIndex[2];
					  } else {
						  randomNumber=(int) randomIndex[0];
					  }
					} else {
					  if (randomIndex[0] > randomIndex[2]) {
						  randomNumber=(int) randomIndex[0];
					  } else if (randomIndex[1] > randomIndex[2]) {
						  randomNumber=(int) randomIndex[2];
					  } else {
						  randomNumber=(int) randomIndex[1];
					  }
					}
				
				int p = partition(array, l, randomNumber, r);

				quickSort(array, l, p);
				quickSort(array, p + 1, r);

			}
		}
		
		//partition the array
	public static int partition(int[] array, int left, int randomindex, int right) {
			int x = 0;
			int i = left - 1;
			int j = right + 1;
			x = array[randomindex];
			while (true) {
				i++;
				while (i < right && array[i] < x)
					i++;
				j--;
				while (j > left && array[j] > x)
					j--;
				if (i < j)
					swap(array, i, j);
				else
					return j;

			}
		}
	
	//carryout swap

	private static void swap(int[] array, int i, int j) {
			int value = 0;
			value = array[i];
			array[i] = array[j];
			array[j] = value;
		}
	public static void main(String[] args) throws IOException {
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

