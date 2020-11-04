import java.util.*;
import java.io.*;

public class SpellCheckRunner {
	
public static void main(String[] args) {
	
		ArrayList<String> allNewWords = new ArrayList<>(); //creates an arraylist that contains the edited file, with corrections

		Scanner s = new Scanner(System.in);
		System.out.println("Enter the name of a file to spellcheck: ");
		String userFile = s.next();
		
		WordRecommender wr = new WordRecommender("engDictionary.txt",userFile+".txt"); //this is where you can change your dictionary
		
		wr.readDict();
		allNewWords = wr.readFile();
		int typoSize = wr.getTypos().size();
				
		// loop for as many times as there are typos and get user corrections
		for(int i=0; i<typoSize; i++) {
			System.out.println("The word " + wr.getTypos().get(i) + " is mispelled");
			
	// ************ TAS: BELOW IS WHERE YOU CAN CHANGE TOLERANCE, TOPN, and SIMILARITY
	// ************ TAS: BELOW IS WHERE YOU CAN CHANGE TOLERANCE, TOPN, and SIMILARITY
	// ************ TAS: BELOW IS WHERE YOU CAN CHANGE TOLERANCE, TOPN, and SIMILARITY
						
			if ((wr.getWordSuggestions(wr.getTypos().get(i), 3, .5, 5)).isEmpty()) {    // if there are no suggestions
				System.out.println("There are 0 suggestions in our dictionary for this word.");
				System.out.println("Press 'a' for accept as is or 't' for type in manually ");
			} else {
				System.out.println("The following suggestions are available:");
				wr.prettyPrint(wr.getWordSuggestions(wr.getTypos().get(i), 3, .5, 5));
				System.out.println("Press 'r' for replace, 'a' for accept as is, 't' for type in manually ");
			}
			Boolean invalidUserEntry = false;
			
			//as long as the user enters an invalid response, keep asking them for another response. if it is valid response, execute.
			while(!invalidUserEntry) {
				char userInput = s.next().charAt(0);     //take the first character typed by user
				if(userInput == 'r') { // if the user selects r, ask them to input the number that they want to replace with
					System.out.println("Your word will now be replaced with one of the suggestions");
					System.out.println("Enter the number corresponding to the word that you want to use for replacement");
					int numSelected = s.nextInt();
					int indexR = allNewWords.indexOf(wr.getTypos().get(i)); // find index of typo word
					allNewWords.set(indexR, wr.getWordSuggestions(wr.getTypos().get(i), 3, .5, 5).get(numSelected-1)); // replace it with corrected word
					invalidUserEntry = true;
				} else if(userInput == 't') { // if the user selects t, ask them to type in the word they want to use
					System.out.println("Please type the word that will be used as the replacement in the output file");
					String wordTyped = s.next();
					int indexT = allNewWords.indexOf(wr.getTypos().get(i)); // find index of typo word
					allNewWords.set(indexT, wordTyped); // replace it with user typed word
					invalidUserEntry = true;
				} else if(userInput == 'a') { // if the user selects a, do nothing
					invalidUserEntry = true;  
				} else {
					System.out.println("Press 'r' for replace, 'a' for accept as is, 't' for type in manually");
					invalidUserEntry = false;
				}
			}
		}
		try {
			// try creating the spell checked file
			PrintWriter pw = new PrintWriter(userFile + "_chk.txt");
	 		int fileSize = allNewWords.size();
	 		
	 		for(int d=0; d<fileSize; d++)  {
	 			pw.print(allNewWords.get(d) + " ");
				pw.flush();
	 		}
	 			
		} catch (FileNotFoundException e) {
			System.out.println("***ERROR");
			e.printStackTrace();
		}
	System.out.println("Your spellchecked file name is : " + userFile + "_chk.txt");
	}
}