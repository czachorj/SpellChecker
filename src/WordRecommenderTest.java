
public class WordRecommenderTest {
	
	public static void main(String[] args) {

			WordRecommender wr = new WordRecommender("engDictionary.txt","filetotest.txt");
			wr.readDict();
			wr.readFile();
			wr.getTypos();
			wr.getSimilarity("oblige","oblige");
			wr.getWordSuggestions("rooom", 3, 0.75, 5);
			wr.getCommonPercent("gardener", "nerdier");
	}
}
