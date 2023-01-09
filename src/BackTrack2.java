import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * @author TRA0004
 *
 */
public class BackTrack2 {
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		String file = "testTrack.txt"; // name of File
		Stack<int[]> stackIndex = new Stack<>(); // To save the possible path of @
		char[][] placeArray = fromFile(file); // brings array of element
		boolean stopSearch = false;
		boolean stopMove = false;
		boolean stopMoveAll = false;
//		for (int countLine = 0; countLine < placeArray.length; countLine++) {
//			System.out.println(Arrays.toString(placeArray[countLine]));
//		}
		System.out.println("\n");

		for (int countLine = 0; countLine < placeArray.length && !stopSearch; countLine++) {
			for (int countColoumn = 0; countColoumn < placeArray[countLine].length && !stopSearch; countColoumn++) {
				if (placeArray[countLine][countColoumn] == '@') {
					int[] index = new int[] { countLine, countColoumn };
					stopSearch = true;
					stackIndex.push(index);
				}

			}
		}
		while (!stackIndex.empty() && !stopMoveAll) {
			int[] index = stackIndex.peek();
			placeArray[index[0]][index[1]] = '@';
			stackIndex.pop();
			HashMap<String, int[]> directions = new HashMap<>();

			directions.put("directionUp", new int[] { index[0] - 1, index[1] });
			directions.put("directionDown", new int[] { index[0] + 1, index[1] });
			directions.put("directionRight", new int[] { index[0], index[1] + 1 });
			directions.put("directionLeft", new int[] { index[0], index[1] - 1 });

			for (String direct : directions.keySet()) {
				if (!stopMoveAll) {
					stopMove = false;
					if (directions.get(direct)[1] == -1 || directions.get(direct)[0] == -1
							|| directions.get(direct)[0] == placeArray.length
							|| directions.get(direct)[1] == placeArray[0].length) {
						stopMove = true;
					}

					if (!stopMove && placeArray[directions.get(direct)[0]][directions.get(direct)[1]] == 'E') {
						placeArray[directions.get(direct)[0]][directions.get(direct)[1]] = '@';
						System.out.println("You have successfully found the path!");
						stopMoveAll = true;
					}

					if (!stopMove && placeArray[directions.get(direct)[0]][directions.get(direct)[1]] == '0') {

						int[] setIndex = { directions.get(direct)[0], directions.get(direct)[1] };
						stackIndex.push(setIndex);

					}


				}
				
			}
			for (int countLine = 0; countLine < placeArray.length; countLine++) {
				for (int io=0; io< placeArray[countLine].length; io++) {
				System.out.print(placeArray[countLine][io]+"  ");}
				System.out.println("\n");	
		}
			//Here you can put clear function
			//cls();
			TimeUnit.SECONDS.sleep(1);
			System.out.println("\n");	

				}
		
			
		}

	/**
	 * This function reads the file and return two dimension array with the elements
	 * of the file in same order
	 */
	public static char[][] fromFile(String in) {
		HashMap<Integer, char[]> places = new HashMap<>();
		File myFile = new File(in);
		try (Scanner scanFile = new Scanner(myFile);) {
			int countLine = 0;
			while (scanFile.hasNext()) {
				// put the element in Hashmap
				String sos = scanFile.nextLine().replaceAll(" ", "");
				places.put(countLine, sos.toCharArray());
				countLine++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// from the Hashmap create the two dimension array
		char[][] placeArray = new char[places.size()][places.get(0).length];
		for (int s = 0; s < places.size(); s++) {
			placeArray[s] = places.get(s);
		}
		return placeArray;
	}
	/**
	 * clear the console
	 */
public static void cls() {
	for (int i=0; i<20; i++) {
		System.out.println("");
	}
	
}
}
