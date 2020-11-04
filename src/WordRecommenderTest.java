
public class WordRecommenderTest {
	
	public static void main(String[] args) {

			WordRecommender wr = new WordRecommender("engDictionary.txt","filetotest.txt");
			wr.readDict();
			wr.readFile();
			wr.getTypos();
			wr.getSimilarity("morbit","morbid");
			wr.getCommonPercent("morbit", "morbid");
			wr.getWordSuggestions("morbit", 1, .5, 5);
			wr.prettyPrint(wr.getWordSuggestions("morbit", 1, .5, 5));
	}
}
