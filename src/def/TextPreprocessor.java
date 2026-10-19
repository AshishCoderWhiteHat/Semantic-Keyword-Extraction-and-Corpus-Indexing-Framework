package def;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextPreprocessor {
    
    // A HashSet to store our stop words for quick O(1) lookup
    private Set<String> stopWords;

    // Constructor to initialize our stop words
    public TextPreprocessor() {
        // Adding basic stop words that don't add semantic value
        stopWords = new HashSet<>(Arrays.asList(
            "the", "is", "a", "an", "and", "of", "to", "in", "for", "with", "on", "are"
        ));
    }

    // 1. Clean the text: convert to lowercase and remove punctuation
    public String cleanText(String text) {
        if (text == null) {
            return "";
        }
        // Convert everything to lowercase
        text = text.toLowerCase();
        // Replace anything that is NOT a lowercase letter or number with a space
        text = text.replaceAll("[^a-z0-9\\s]", " ");
        
        return text;
    }

    // 2. Tokenize: split the cleaned text into words and remove stop words
    public List<String> tokenize(String text) {
        String cleanedText = cleanText(text);
        
        // Split the text into an array of words wherever there are spaces
        String[] words = cleanedText.split("\\s+");
        
        List<String> validTokens = new ArrayList<>();
        
        // Loop through each word
        for (String word : words) {
            // If the word isn't blank and isn't a stop word, keep it
            if (!word.trim().isEmpty() && !isStopWord(word)) {
                validTokens.add(word);
            }
        }
        return validTokens;
    }

    // 3. Check if a word is in our HashSet of stop words
    public boolean isStopWord(String word) {
        return stopWords.contains(word);
    }
}