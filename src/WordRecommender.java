import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class WordRecommender {

	// read from dictionary file

	String[] readFile(){
		File dictionary = new File ("engDictionary.txt");
		ArrayList<String> engDict = new ArrayList<>();
		try {
			Scanner scnr = new Scanner(dictionary);
			while (scnr.hasNext()) {
				engDict.add(scnr.next());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}




}
