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
		return similarity;

	}
	
	
	
	public double getCommonPercent(String word1, String word2) {
		
		// S1 = the set of letters in w1 &&  S2 = the set of letters in w1
		List<Character> S1 = new ArrayList<Character>();		
		List<Character> S2 = new ArrayList<Character>();
		List<Character> numerator = new ArrayList<Character>();		
		List<Character> denominator = new ArrayList<Character>();

		int word1LastIndex = word1.length();     // variable to track the looping through word1
		int word2LastIndex = word2.length();     // variable to track the looping through word2

		// add the very first letter of the word to S1
		S1.add(word1.charAt(0));
		for(int z=0; z<word1LastIndex; z++) {		  
			if (S1.indexOf(word1.charAt(z)) == -1) {   // returns -1 if char does not exist in the set
					S1.add(word1.charAt(z));		   // add char to list
			}
		}
		
		S2.add(word2.charAt(0));
		for(int v=0; v<word2LastIndex; v++) {		  
			if (S2.indexOf(word2.charAt(v)) == -1) {   // returns -1 if char does not exist in the set
					S2.add(word2.charAt(v));		   // add char to list
			}
		}
		
		int s1length = S1.size();
		int s2length = S2.size();
		
		// numerator = all the letters that are common in both sets
		for(int a=0; a<s1length; a++) {
			if(S2.contains(S1.get(a))) {
				numerator.add(S1.get(a));
			}
		}
		
		// denominator = all the letters
		for(int u=0; u<s1length; u++) {			// add all of the elements from S1 to the denominator
			denominator.add(S1.get(u));
		}
		for(int d=0; d<s2length; d++) {
			if(denominator.contains(S2.get(d))) {
			} else {
				denominator.add(S2.get(d));			// for each element that does not already exist in denominator, add to denominator
			}
		}
		
		double numSize = numerator.size();			
		double denSize = denominator.size();
		double fraction = numSize/denSize;
		return fraction;
	}
	
	
	/**
	 * print out arraylist of suggested words
	 * @param word, tolerance, commonPercent, topN
	 * @return
	 */
	
	
	public ArrayList<String> getWordSuggestions(String word, int tolerance, double commonPercent, int topN) {

		// step 1: filter based on word length +/0 tolerance characters
		List<String> wordLengthFiltered = new ArrayList<String>();
		List<String> commonPercentFiltered = new ArrayList<String>();
		List<Double> filteredSimilarities = new ArrayList<Double>();
		ArrayList<String> getTopN = new ArrayList<String>();

		int wordLength = word.length();
		int wordLengthLower = word.length()-tolerance;
		int wordLengthUpper = word.length()+tolerance;
		
		// if the word is between lower and upper bounds, add to wordLengthFiltered
		for(int f=0; f<engDict.size(); f++) {
			int suggestionLength = engDict.get(f).length();
			if((suggestionLength >= wordLengthLower) && (suggestionLength <= wordLengthUpper)){
				wordLengthFiltered.add(engDict.get(f));
			}
		}
		
		// step 2: next, filter so it only contains words with commonPercent in common, add to commonPercentFiltered
		for(int e=0; e<wordLengthFiltered.size(); e++) {
			double commonPercentReturn = getCommonPercent(word, wordLengthFiltered.get(e));
			if(commonPercentReturn >= commonPercent ) {
				commonPercentFiltered.add(wordLengthFiltered.get(e));
			}
		}
						
		// step 3: use similarity metric on every remaining word, store similarity values into arraylist
		//   		-- indexing corresponds directly to commonPercentFiltered
		for(int w=0; w<commonPercentFiltered.size(); w++) {
			double simValue = getSimilarity(word, commonPercentFiltered.get(w));
			filteredSimilarities.add(simValue);
		}
		
		// step 4: return topN of the words (if there are fewer, return all)
		for(int q=0; q<topN; q++) {
			Double maxValue = 0.0;
			int maxIndex = 0;
			for(int y=0; y<filteredSimilarities.size(); y++) {
				if (filteredSimilarities.get(y) > maxValue) {
					maxValue = filteredSimilarities.get(y);
					maxIndex = y;
				}
			}
			if (maxValue > 0) {
				getTopN.add(commonPercentFiltered.get(maxIndex));
				filteredSimilarities.remove(maxIndex);
				commonPercentFiltered.remove(maxIndex);	
			}
		}
		System.out.println("**** " + getTopN);
		return getTopN;
	}


	public String prettyPrint (ArrayList<String> list) {
		for(int i =0; i<list.size(); i++) {
			System.out.print(i+"."+list.get(i));
		}
		return null;

	}


}
