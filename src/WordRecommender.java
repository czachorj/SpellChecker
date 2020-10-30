import java.util.Scanner;
import java.io.*;

public class WordRecommender {

	// read from dictionary file

	String[] readFile(){
		File dictionary = new File ("engDictionary.txt");

		try {
			Scanner scnr = new Scanner(dictionary);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}




}
