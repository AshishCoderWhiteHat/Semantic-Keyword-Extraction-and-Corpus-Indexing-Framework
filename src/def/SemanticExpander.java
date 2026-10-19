package def;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SemanticExpander {
    
    // Maps a word to its group of synonyms
    private Map<String, Set<String>> synonymMap;

    public SemanticExpander() {
        synonymMap = new HashMap<>();
        
        // Pre-defining some basic semantic groups for our project
        addGroup("java", "programming");
        addGroup("car", "automobile", "vehicle");
        addGroup("database", "sql");
        addGroup("ai", "artificial", "intelligence");
    }

    // 1. Adds a group of related words into our Map
    public void addGroup(String... words) {
        // Put the words into a HashSet to form a group
        Set<String> group = new HashSet<>(Arrays.asList(words));
        
        // For every word in the group, map it to the entire group
        for (String word : words) {
            synonymMap.put(word, group);
        }
    }

    // 2. Takes a keyword and returns all its related terms
    public Set<String> expand(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        
        // If we have synonyms for this word, return the whole group
        if (synonymMap.containsKey(lowerKeyword)) {
            return synonymMap.get(lowerKeyword);
        } 
        
        // If no synonyms exist, just return a Set with the original word
        Set<String> singleWordSet = new HashSet<>();
        singleWordSet.add(lowerKeyword);
        return singleWordSet;
    }
}