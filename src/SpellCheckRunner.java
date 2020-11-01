import java.util.ArrayList;
import java.util.Scanner;

public class SpellCheckRunner {
	public static void main(String[] args) {

		WordRecommender test = new WordRecommender("filetotest.txt"); //testing word recommender
		Scanner s = new Scanner(System.in);

		test.getTypos(); //get Typos for test case
		
		for(int i=0; i<test.getTypos().size(); i++) {
			System.out.println("The word " + test.getTypos().get(i) + " is mispelled");
			System.out.println("The following suggestions are available:");
			
			ArrayList<String> list = null; //initializing variable
			test.prettyPrint(list); //printing out word suggestions
			
			
			
		}
	}
}
