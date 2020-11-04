import java.util.*;

public class SpellCheckRunner {
	
	public static void main(String[] args)  {

		WordRecommender test = new WordRecommender("engDictionary.txt","filetotest.txt"); //testing word recommender
		Scanner s = new Scanner(System.in);
		String userInput;

		test.getTypos(); //get Typos for test case

	
		for(int i=0; i<test.getTypos().size(); i++) {
			//if(wordSuggestions>0) {
			System.out.println("The word " + test.getTypos().get(i) + " is mispelled");
			System.out.println("The following suggestions are available:");

			ArrayList<String> list = null; //initializing variable
			test.prettyPrint(list); //printing out word suggestions

			System.out.println("Press 'r' for replace, 'a' for accept as is, 't' for type in manually ");

			userInput = s.next();

			if(userInput == "r") { //if the user selects r, ask them to input the number that they want to replace with
				System.out.println("Your word will now be replaced with one of the suggestions");
				System.out.println("Enter the number corresponding to the word that you want to use for replacement");
				//need to write code to replace words in the output file
			}
			
			if(userInput == "t") { //if the user selects t, ask them to type in the word they want to use
				System.out.println("Please type the word that will be used as the replacement in the output file");
				//need to write code to replace words in the output file
			}

		}
//		else {
//			System.out.println("The word " + test.getTypos().get(i) + " is mispelled");
//			System.out.println("There are 0 suggestions in our dictionary for this word");	
//			System.out.println("Press 'a' for accept as is, or 't' for type in manually");	
//		if(userInput == "t") { //if the user selects t, ask them to type in the word they want to use
//			System.out.println("Please type the word that will be used as the replacement in the output file");
//			//need to write code to replace words in the output file
		}

		//}
		}
}
