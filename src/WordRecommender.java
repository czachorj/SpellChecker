import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class WordRecommender {

	String fileName; //creating instance variable for dictionary
	String textFileName; //creating instance variable for textFile
	
	WordRecommender(String fileName){
		this.fileName = fileName;
	}

	ArrayList<String> engDict = new ArrayList<>();  //creating an arraylist for the dictionary words
	ArrayList<String> testFile = new ArrayList<>();  //creating an arraylist for the paragraph to be tested 
	ArrayList<String> typos = new ArrayList<>(); //creating typos arraylist

	// read from dictionary file
	String[] readDict(){
		File dictionary = new File (fileName);

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
		File textFile = new File (textFileName);

		try {
			Scanner scnr = new Scanner(textFile);
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

	public double getSimilarity (String word1, String word2) {

		int leftSimilarity = 0; // number of letters that match between word1 and word two as we go from left to right
		int rightSimilarity = 0; // number of letters that match between word1 and word two as we go from right to left
		double similarity = 0; //similarity score we want returned


		/**
		 * 
		 * using the length of the shortest word, 
		 * going from left to right 
		 * and then from right to left
		 * compare each character at each index 
		 * to the character at the same index of the other word
		 * add to the relevant counter
		 */

		if(word1.length() < word2.length()) { 

			for(int j=0; j<word1.length(); j++) {
				if (word1.charAt(j) == word2.charAt(j)) {
					++leftSimilarity;
				}
			}
			for (int i=word1.length(); i>0; i--) {
				if(word1.charAt(i) == word2.charAt(i)) {
					++rightSimilarity;
				}
			}
		}
		else if(word2.length() < word1.length()) {

			for(int j=0; j<word2.length(); j++) {
				if (word2.charAt(j) == word1.charAt(j)) {
					++leftSimilarity;
				}
			}
			for (int i=word2.length(); i>0; i--) {
				if(word2.charAt(i) == word1.charAt(i)) {
					++rightSimilarity;
				}
			}
		}
		similarity = (leftSimilarity + rightSimilarity)/2.0; //calculating similarity score
		return similarity;

	}
	/**
	 * print out arraylist of suggested words
	 * @param list
	 * @return
	 */

	public String prettyPrint (ArrayList<String> list) {
		for(int i =0; i<list.size(); i++) {
			System.out.print(i+"."+list.get(i));
		}
		return null;

	}


}
