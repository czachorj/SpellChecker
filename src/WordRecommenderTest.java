import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WordRecommenderTest {

	@Test
	void testGetSimilarity() {
		
		WordRecommender test1 = new WordRecommender("filename", "otherfile");
		String word1 = "apple";
		String word2 = "arcade";
		assertEquals(test1.getSimilarity(word1, word2),1);
	}
}
//
//	@Test
//	void testGetCommonPercent() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetWordSuggestions() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testPrettyPrint() {
//		fail("Not yet implemented");
//	}
//
//}
