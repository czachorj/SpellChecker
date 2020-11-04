import java.io.*;
import java.util.*;

public class WordRecommenderTest {
	
	public static void main(String[] args) {
		
		ArrayList<String> correctedWords = new ArrayList<>(); //creating arraylist that contains the user's chosen corrections
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the name of a file to spellcheck: ");
		String userFile = s.next();
		 
		try {
			PrintWriter pw = new PrintWriter(userFile + "_chk.txt");
			//pw.println("kkmvmkvmk");
			//pw.flush();
		} catch (FileNotFoundException e) {
			System.out.println("***ERROR");
			e.printStackTrace();
		}
				
		WordRecommender wr = new WordRecommender("engDictionary.txt",userFile+".txt");
		wr.readDict();
		wr.readFile();
		
		int typoSize = wr.getTypos().size();
		
		for(int i=0; i<typoSize; i++) {
			System.out.println("The word " + wr.getTypos().get(i) + " is mispelled");
			System.out.println("The following suggestions are available:");
			wr.prettyPrint(wr.getWordSuggestions(wr.getTypos().get(i), 3, .5, 5));
			System.out.println("Press 'r' for replace, 'a' for accept as is, 't' for type in manually ");
			Boolean invalidUserEntry = false;
			while(!invalidUserEntry) {
				char userInput = s.next().charAt(0);
				
				// TODO: if zero suggestions
				// userInputs are added to correctedWords arraylist
				if(userInput == 'r') { //if the user selects r, ask them to input the number that they want to replace with
					System.out.println("Your word will now be replaced with one of the suggestions");
					System.out.println("Enter the number corresponding to the word that you want to use for replacement");
					int numSelected = s.nextInt();
					correctedWords.add((wr.getWordSuggestions(wr.getTypos().get(i), 3, .5, 5).get(numSelected-1)));
					invalidUserEntry = true;
				} else if(userInput == 't') { //if the user selects t, ask them to type in the word they want to use
					System.out.println("Please type the word that will be used as the replacement in the output file");
					String wordTyped = s.next();
					correctedWords.add(wordTyped);
					invalidUserEntry = true;
				} else if(userInput == 'a') { //if the user selects a, do nothing
					String wordSaved = wr.getTypos().get(i);
					correctedWords.add(wordSaved);
					invalidUserEntry = true;
				} else {
					System.out.println("Press 'r' for replace, 'a' for accept as is, 't' for type in manually");
					invalidUserEntry = false;
				}
			}
		}
	System.out.println("Your spellchecked file name is : " + userFile + "_chk.txt");
	}
}
