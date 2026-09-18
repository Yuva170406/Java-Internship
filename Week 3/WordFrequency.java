import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class WordFrequency {
    public static void main(String[] args) {

        HashMap<String, Integer> words = new HashMap<>();

        try {
            // Read input file
            FileReader reader = new FileReader("input.txt");

            StringBuilder content = new StringBuilder();

            int ch;

            while ((ch = reader.read()) != -1) {
                content.append((char) ch);
            }

            reader.close();

            // Convert to lowercase and remove punctuation
            String text = content.toString()
                    .toLowerCase()
                    .replaceAll("[^a-zA-Z0-9 ]", " ");

            // Split into words
            String[] wordList = text.split("\\s+");

            // Count word frequency
            for (String word : wordList) {

                if (!word.isEmpty()) {
                    words.put(word, words.getOrDefault(word, 0) + 1);
                }
            }

            // Write result to output file
            FileWriter writer = new FileWriter("output.txt");

            for (String word : words.keySet()) {
                writer.write(word + " : " + words.get(word) + "\n");
            }

            writer.close();

            System.out.println("Word frequency counted successfully.");
            System.out.println("Result written to output.txt");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}