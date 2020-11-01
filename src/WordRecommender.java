import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class WordRecommender {


	ArrayList<String> engDict = new ArrayList<>();  //creating an arraylist for the dictionary words

	ArrayList<String> testFile = new ArrayList<>();  //creating an arraylist for the paragraph to be tested 

	ArrayList<String> typos = new ArrayList<>(); //creating typos arraylist

	// read from dictionary file
	String[] readDict(){
		File dictionary = new File ("engDictionary.txt");

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


	//read from test file to spellcheck
	String[] readFile(){
		File dictionary = new File ("filetotest.txt");

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

	//adding words that don't exist in the dictionary into the typos arraylist
	ArrayList<String> getTypos () {
		for (int i=0; i<testFile.size(); i++) { //looping through text file and comparing against the dictionary file
			if	(!engDict.contains(testFile.get(i))){
				typos.add(testFile.get(i));
			}
		}

		return typos;
	}




}
