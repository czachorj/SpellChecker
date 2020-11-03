import java.util.*;
import java.io.*;

public class WordRecommender {

	String fileName; //creating instance variable for dictionary
	String textFileName; //creating instance variable for textFile

	
	WordRecommender(String fileName, String textFileName){
		this.fileName = fileName;
		this.textFileName = textFileName;
	}

	ArrayList<String> engDict = new ArrayList<>();  //creating an arraylist for the dictionary words
	ArrayList<String> testFile = new ArrayList<>();  //creating an arraylist for the paragraph to be tested 
	ArrayList<String> typos = new ArrayList<>(); //creating typos arraylist

	
	// read from dictionary file
	String[] readDict(){
		File dictionary = new File(fileName);

		try {
			Scanner scnr = new Scanner(dictionary);
			while (scnr.hasNext()) {
				engDict.add(scnr.next());
			}
			// engDict contains the entire dictionary in an arraylist

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	// read from test file to spellcheck
	String[] readFile(){
		File textFile = new File(textFileName);

		try {
			Scanner scnr = new Scanner(textFile);
			while (scnr.hasNext()) {
				testFile.add(scnr.next());
			}
		// testFile contains the entire user input file in an arraylist
		
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
		System.out.println(typos);
		return typos;
	}

	
	public double getSimilarity (String word1, String word2) {

		int leftSimilarity = 0; // number of letters that match between word1 and word2 as we go from left to right
		int rightSimilarity = 0; // number of letters that match between word1 and word2 as we go from right to left
		double similarity = 0; //similarity score we want returned
		int word1LastIndex = word1.length()-1;     // variable to track the reverse looping through word1
		int word2LastIndex = word2.length()-1;     // variable to track the reverse looping through word2
		int runningCounter1 = word1.length();      // variable to track for how long to loop for
		int runningCounter2 = word2.length();      // variable to track for how long to loop for

		// using the length of the shortest word, from left to right, compare each char at each index to the char at the same index of the other word, add to counter
		if(word1.length() < word2.length()) { 
			for(int j=0; j<word1.length(); j++) {
				if (word1.charAt(j) == word2.charAt(j)) {
					++leftSimilarity;
				}
			}
			// only loop a total of the shortest word's length time and compare the last index of each word to the other, decrement indexes
			while (runningCounter1 > 0) {
				if(word2.charAt(word2LastIndex) == word1.charAt(word1LastIndex)) {
					++rightSimilarity;
				}
				--word1LastIndex;
				--word2LastIndex;
				--runningCounter1;
			}
			
			
		} else if(word2.length() < word1.length()) {
			for(int j=0; j<word2.length(); j++) {
				if (word2.charAt(j) == word1.charAt(j)) {
					++leftSimilarity;
				}
			}
			while (runningCounter2 > 0) {
				if(word2.charAt(word2LastIndex) == word1.charAt(word1LastIndex)) {
					++rightSimilarity;
				}
				--word1LastIndex;
				--word2LastIndex;
				--runningCounter2;
			}
			
		} else { // if word1 and word2 are equal in length
			for(int j=0; j<word2.length(); j++) {
				if (word2.charAt(j) == word1.charAt(j)) {
					++leftSimilarity;
				}
			}
			while (runningCounter2 > 0) {
				if(word2.charAt(word2LastIndex) == word1.charAt(word1LastIndex)) {
					++rightSimilarity;
				}
				--word1LastIndex;
				--word2LastIndex;
				--runningCounter2;
			}
		}
		
		similarity = (leftSimilarity + rightSimilarity)/2.0; //calculating similarity score
		System.out.println(similarity);
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
