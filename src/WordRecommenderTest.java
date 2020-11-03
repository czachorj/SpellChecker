
public class WordRecommenderTest {
	
	public static void main(String[] args) {

			WordRecommender wr = new WordRecommender("engDictionary.txt","filetotest.txt");
			wr.readDict();
			wr.readFile();
			wr.getTypos();
			wr.getSimilarity("oblige","oblige");
			wr.getCommonPercent("gardener", "nerdier");
			wr.getWordSuggestions("testing", 1, .5, 5);
	}
}
