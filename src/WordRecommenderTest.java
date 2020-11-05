import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class WordRecommenderTest {

	//testing GetSimilarity

	@Test
	void testGetSimilarity() {

		WordRecommender test1 = new WordRecommender("filename", "otherfile");
		String word1 = "apple";
		String word2 = "arcade";
		assertEquals(test1.getSimilarity(word1, word2),1);
	}
	@Test
	void testGetSimilarity2() {

		WordRecommender test2 = new WordRecommender("filename2", "otherfile2");
		String word1 = "serial";
		String word2 = "cereal";
		assertEquals(test2.getSimilarity(word1, word2),4);
	}
	@Test
	void testGetSimilarity3() {

		WordRecommender test3 = new WordRecommender("filename3", "otherfile3");
		String word1 = "quilt";
		String word2 = "quit";
		assertEquals(test3.getSimilarity(word1, word2),2);
	}


	//testing CommonPercent

	@Test
	void testGetCommonPercent() {
		WordRecommender test4 = new WordRecommender("filename4", "otherfile4");
		String word1 = "sponge";
		String word2 = "spoon";
		assertEquals(test4.getCommonPercent(word1, word2),0.6666666666666666);
	}
	@Test
	void testGetCommonPercent2() {
		WordRecommender test5 = new WordRecommender("filename5", "otherfile5");
		String word1 = "never";
		String word2 = "vendetta";
		assertEquals(test5.getCommonPercent(word1, word2),0.42857142857142855);
	}

	//testing getWordSuggestions





	@Test
	void testGetWordSuggestions() {
		WordRecommender test6 = new WordRecommender("filename4", "otherfile4");

		String word = "bannna";
		int tolerance = 3;
		double commonPercent = .5;
		int topN = 5;

		test6.engDict.add("banana");
		test6.engDict.add("banned");
		test6.engDict.add("banner");
		test6.engDict.add("baaing");
		test6.engDict.add("baking");

		ArrayList<String> answer = new ArrayList<String>();


		answer.add("banana");
		answer.add("banned");
		answer.add("banner");
		answer.add("baaing");
		answer.add("baking");
		assertEquals(test6.getWordSuggestions(word, tolerance, commonPercent, topN), answer);



	}

	@Test
	void testGetWordSuggestions2() {
		WordRecommender test7 = new WordRecommender("filename4", "otherfile4");

		String word = "grat";
		int tolerance = 3;
		double commonPercent = .5;
		int topN = 5;

		test7.engDict.add("brat");
		test7.engDict.add("drat");
		test7.engDict.add("gnat");
		test7.engDict.add("goat");
		test7.engDict.add("grab");


		ArrayList<String> answer = new ArrayList<String>();


		answer.add("brat");
		answer.add("drat");
		answer.add("gnat");
		answer.add("goat");
		answer.add("grab");
		assertEquals(test7.getWordSuggestions(word, tolerance, commonPercent, topN), answer);



	}


	@Test
	void testGetWordSuggestions3() {
		WordRecommender test8 = new WordRecommender("filename4", "otherfile4");

		String word = "sdlfkjxxxxxyoxyxyxyxx";
		int tolerance = 3;
		double commonPercent = .5;
		int topN = 5;
		ArrayList<String> engDict = new ArrayList<>();  //creating an arraylist for the dictionary words
		engDict.add("pickle");
		engDict.add("drat");
		engDict.add("pencil");
		engDict.add("goat");
		engDict.add("potato");

		ArrayList<String> answer = new ArrayList<String>();


		assertEquals(test8.getWordSuggestions(word, tolerance, commonPercent, topN), answer);


	}


	//testing prettyPrint

	@Test
	void testPrettyPrint() {


		WordRecommender test9 = new WordRecommender("filename8", "otherfile8");
		ArrayList<String> answer = new ArrayList<String>();


		answer.add("banana");
		answer.add("banned");
		answer.add("banner");
		answer.add("baaing");
		answer.add("baking");
		assertEquals(test9.prettyPrint(answer),null);



		//testing read dict

	}
	@Test
	void testReadDict() {
		WordRecommender test10 = new WordRecommender("miniDict.txt", "otherfile8");

		assertEquals(test10.readDict(),null);



	}
	
	
	



	//testing read file
	@Test
	void testReadFile() {
		WordRecommender test11 = new WordRecommender("filename", "miniFile.txt");

		assertEquals(test11.readFile(),test11.testFile);
	}
	




	
//testing getTypos
	@Test
	void testgetTypos() {
		WordRecommender test12 = new WordRecommender("miniDict.txt", "miniFile.txt");

		assertEquals(test12.getTypos(),test12.typos);



	}
}
