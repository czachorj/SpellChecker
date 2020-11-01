import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class WordRecommender {

	// read from dictionary file

	String[] readDict(){
		File dictionary = new File ("engDictionary.txt");
		ArrayList<String> engDict = new ArrayList<>(); //creating an arraylist for the dictionary words
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

	
	//read test file to spellcheck
	String[] readFile(){
		File dictionary = new File ("filetotest.txt");
		ArrayList<String> testFile = new ArrayList<>(); //creating an arraylist for the paragraph to be tested 
		try {
			Scanner scnr = new Scanner(dictionary);
			while (scnr.hasNext()) {
				testFile.add(scnr.next());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	String []
	

}
