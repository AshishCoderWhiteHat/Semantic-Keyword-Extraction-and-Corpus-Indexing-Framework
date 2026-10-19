package def;

public class KMPMatcher {

    // 1. Build the Longest Prefix Suffix (LPS) array
    private int[] buildLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0; // Length of the previous longest prefix suffix
        int i = 1;
        
        lps[0] = 0; // The first element is always 0

        // Loop to calculate lps[i] for i = 1 to m-1
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else { // (pattern[i] != pattern[len])
                if (len != 0) {
                    len = lps[len - 1]; // Fall back to previous longest prefix
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    // 2. Count how many times the keyword (pattern) appears in the document (text)
    public int countOccurrences(String text, String pattern) {
        // Edge case: if text or pattern is empty, return 0
        if (pattern == null || pattern.isEmpty() || text == null || text.isEmpty()) {
            return 0;
        }

        // Convert both to lowercase for case-insensitive matching
        text = text.toLowerCase();
        pattern = pattern.toLowerCase();

        int n = text.length();
        int m = pattern.length();
        
        // Step 1: Preprocess the pattern to create the LPS array
        int[] lps = buildLPS(pattern);

        int count = 0;
        int i = 0; // index for text
        int j = 0; // index for pattern

        // Step 2: Traverse the text
        while (i < n) {
            if (pattern.charAt(j) == text.charAt(i)) {
                j++;
                i++;
            }
            
            // If we found a complete match
            if (j == m) {
                count++;
                // Reset j using the LPS array to continue searching for more matches
                j = lps[j - 1]; 
            } 
            // If there is a mismatch after j matches
            else if (i < n && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1]; // Don't match from the beginning, use LPS array
                } else {
                    i++; // If j is 0, just move to the next character in text
                }
            }
        }
        return count;
    }
}