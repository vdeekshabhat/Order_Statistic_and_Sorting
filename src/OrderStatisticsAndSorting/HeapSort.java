package OrderStatisticsAndSorting;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.BufferedWriter;


public class HeapSort {
	public static int n;
	public static void heapsort(int[] inputArray){
		
		int n=inputArray.length;
		for (int i = n / 2-1 ; i >= 0; i--)
            heapify(inputArray, n, i);
		for (int i=n-1; i>=0; i--)
        {
			swap(0,i,inputArray);
            heapify(inputArray, i, 0);
        }
		
	}
	   public static void heapify(int[] inputArray, int n, int i)
	    {
	        int max = i;  
	        int left = 2*i + 1;  
	        int right = 2*i + 2;  
	 
	        if (left < n && inputArray[left] > inputArray[max])
	            max = left;
	 
	        if (right < n && inputArray[right] > inputArray[max])
	            max = right;
	 
	        if (max != i)
	        {
	        	swap(i,max,inputArray);
	            heapify(inputArray, n,  max);
	        }
	    }
	   public static void swap(int i,int j, int[] inputArray){
		   int temp = inputArray[i];
		   inputArray[i] = inputArray[j];
		   inputArray[j] = temp;
	   }
	
	public static void main(String[] args) throws IOException {
		String FILENAME = "/Users/deekshabhat/Documents/Test_Cases/unifrom100000.txt";
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
		heapsort(inputArray);
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