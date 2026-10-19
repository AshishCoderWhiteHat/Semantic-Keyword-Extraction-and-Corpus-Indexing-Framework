package def;

public class EditDistance {

    // Calculates the minimum number of edits
    // needed to convert word1 into word2.
    public int calculate(String word1, String word2) {

        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();

        int m = word1.length();
        int n = word2.length();

        // DP table
        int[][] dp = new int[m + 1][n + 1];

        // Convert word1 into an empty string
        // using deletions.
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        // Convert an empty string into word2
        // using insertions.
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                // Characters are the same
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];

                } else {

                    // Insert a character
                    int insert = dp[i][j - 1];

                    // Delete a character
                    int delete = dp[i - 1][j];

                    // Replace a character
                    int replace = dp[i - 1][j - 1];

                    dp[i][j] = 1 + Math.min(
                            insert,
                            Math.min(delete, replace)
                    );
                }
            }
        }

        return dp[m][n];
    }
}