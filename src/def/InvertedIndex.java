package def;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InvertedIndex {
    
    // The core Data Structure: A HashMap mapping a Keyword to a Set of Document IDs
    private Map<String, Set<String>> index;

    public InvertedIndex() {
        this.index = new HashMap<>();
    }

    // 1. Add a document and its keywords to the index
    public void addDocument(String documentId, Set<String> keywords) {
        for (String keyword : keywords) {
            // If the keyword isn't in the index yet, add it with a new empty HashSet
            index.putIfAbsent(keyword, new HashSet<>());
            
            // Get the set of documents for this keyword and add the current document ID
            index.get(keyword).add(documentId);
        }
    }

    // 2. Search for a keyword and return the Document IDs that contain it
    public Set<String> search(String keyword) {
        // Return the set of doc IDs, or an empty set if the keyword isn't found
        return index.getOrDefault(keyword, new HashSet<>());
    }

    // 3. Display the entire index (useful for the console menu)
    public void displayIndex() {
        System.out.println("\n--- INVERTED INDEX ---");
        for (Map.Entry<String, Set<String>> entry : index.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    // 4. Get all unique keywords currently stored in the index
    public Set<String> getAllKeywords() {
        return index.keySet();
    }

    // 5. Get the total number of unique keywords in the index
    public int size() {
        return index.size();
    }
}