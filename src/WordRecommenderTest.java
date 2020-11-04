import static org.junit.jupiter.api.Assertions.*;

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



	@Test
	void testGetWordSuggestions() {
		WordRecommender test6 = new WordRecommender("filename6", "otherfile6");
		
	}
}
//
//	@Test
//	void testPrettyPrint() {
//		fail("Not yet implemented");
//	}
//
//}
