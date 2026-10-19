package def;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KeywordExtractor {
    
    // We need our preprocessor to clean the text first
    private TextPreprocessor preprocessor;

    public KeywordExtractor() {
        this.preprocessor = new TextPreprocessor();
    }

    // 1. Get only unique keywords using a HashSet
    public Set<String> uniqueKeywords(String text) {
        // First, get the clean, stop-word-free tokens
        List<String> tokens = preprocessor.tokenize(text);
        
        // Put them in a HashSet. A HashSet automatically removes duplicates!
        return new HashSet<>(tokens);
    }

    // 2. Extract the most frequent keywords up to a certain limit
    public List<String> extractKeywords(String text, int limit) {
        List<String> tokens = preprocessor.tokenize(text);
        
        // HashMap to store Word -> Frequency count
        Map<String, Integer> frequencyMap = new HashMap<>();

        // Count how many times each word appears
        for (String word : tokens) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // Move the HashMap entries into an ArrayList so we can sort them
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(frequencyMap.entrySet());

        // Sort the list in descending order (highest frequency first)
        entryList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Create a final list for our top keywords
        List<String> topKeywords = new ArrayList<>();
        
        // Loop through the sorted list and pick the top 'limit' keywords
        for (int i = 0; i < Math.min(limit, entryList.size()); i++) {
            topKeywords.add(entryList.get(i).getKey());
        }

        return topKeywords;
    }
}