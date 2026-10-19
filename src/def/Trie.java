package def;

import java.util.ArrayList;
import java.util.List;

public class Trie {

    // Node represents one character in the Trie
    private static class TrieNode {

        TrieNode[] children = new TrieNode[26];

        boolean isEndOfWord = false;
    }

    private TrieNode root;

    // Constructor
    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {

        word = word.toLowerCase();

        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);

            // Only process English alphabet characters
            if (ch < 'a' || ch > 'z') {
                continue;
            }

            int index = ch - 'a';

            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            current = current.children[index];
        }

        current.isEndOfWord = true;
    }

    // Find all words beginning with a given prefix
    public List<String> getSuggestions(String prefix) {

        prefix = prefix.toLowerCase();

        List<String> suggestions = new ArrayList<>();

        TrieNode current = root;

        // Find the node corresponding to the prefix
        for (int i = 0; i < prefix.length(); i++) {

            char ch = prefix.charAt(i);

            if (ch < 'a' || ch > 'z') {
                return suggestions;
            }

            int index = ch - 'a';

            if (current.children[index] == null) {
                return suggestions;
            }

            current = current.children[index];
        }

        // Collect words below this node
        collectWords(current, prefix, suggestions);

        return suggestions;
    }

    // Recursively collect words from the Trie
    private void collectWords(
            TrieNode node,
            String currentWord,
            List<String> suggestions) {

        if (node.isEndOfWord) {
            suggestions.add(currentWord);
        }

        for (int i = 0; i < 26; i++) {

            if (node.children[i] != null) {

                char nextCharacter = (char) ('a' + i);

                collectWords(
                        node.children[i],
                        currentWord + nextCharacter,
                        suggestions
                );
            }
        }
    }

    // Check whether a complete word exists
    public boolean contains(String word) {

        word = word.toLowerCase();

        TrieNode current = root;

        for (int i = 0; i < word.length(); i++) {

            char ch = word.charAt(i);

            if (ch < 'a' || ch > 'z') {
                return false;
            }

            int index = ch - 'a';

            if (current.children[index] == null) {
                return false;
            }

            current = current.children[index];
        }

        return current.isEndOfWord;
    }
}