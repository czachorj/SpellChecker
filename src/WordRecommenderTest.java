
public class WordRecommenderTest {
	
	public static void main(String[] args) {

			WordRecommender wr = new WordRecommender("engDictionary.txt","filetotest.txt");
			wr.readDict();
			wr.readFile();
			wr.getTypos();
			wr.getSimilarity("oblivion","oblige");
	}
}
