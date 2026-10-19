
package def;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchEngine {

    // Core components
    private List<Document> documents;
    private InvertedIndex index;
    private KeywordExtractor keywordExtractor;
    private SemanticExpander semanticExpander;
    private KMPMatcher kmpMatcher;
    private EditDistance editDistance;
    private Trie trie;

    // Constructor
    public SearchEngine() {

        documents = new ArrayList<>();
        index = new InvertedIndex();
        keywordExtractor = new KeywordExtractor();
        semanticExpander = new SemanticExpander();
        kmpMatcher = new KMPMatcher();
        editDistance = new EditDistance();
        trie = new Trie();
    }

    // =========================================================
    // ADD DOCUMENT
    // =========================================================

    public void addDocument(
            String documentId,
            String content) {

        Document newDoc =
                new Document(documentId, content);

        documents.add(newDoc);
    }

    // =========================================================
    // BUILD INDEX AND TRIE
    // =========================================================

    public void buildFromDocuments() {

        for (Document doc : documents) {

            Set<String> keywords =
                    keywordExtractor.uniqueKeywords(
                            doc.getContent()
                    );

            // Add keywords to inverted index
            index.addDocument(
                    doc.getDocumentId(),
                    keywords
            );

            // Add keywords to Trie
            for (String keyword : keywords) {

                trie.insert(keyword);
            }
        }
    }

    // =========================================================
    // ORIGINAL CONSOLE SEARCH
    // =========================================================

    public void search(String query) {

        System.out.println(
                searchAndReturnResults(query)
        );
    }

    // =========================================================
    // SEARCH METHOD FOR GUI
    // =========================================================

    public String searchAndReturnResults(
            String query) {

        StringBuilder output =
                new StringBuilder();

        output.append(
                "========================================\n"
        );

        output.append(
                "SEARCH QUERY: "
        );

        output.append(query);

        output.append(
                "\n========================================\n\n"
        );

        // Clean query
        query = query.trim().toLowerCase();

        if (query.length() == 0) {

            output.append(
                    "Please enter a valid search query.\n"
            );

            return output.toString();
        }

        // =====================================================
        // STEP 1: SEMANTIC EXPANSION
        // =====================================================

        Set<String> expandedQuery =
                semanticExpander.expand(query);

        output.append(
                "SEMANTIC EXPANSION\n"
        );

        output.append(
                "------------------\n"
        );

        output.append(
                expandedQuery
        );

        output.append(
                "\n\n"
        );

        boolean foundExactMatch = false;

        // =====================================================
        // STEP 2: EXACT SEARCH USING INVERTED INDEX + KMP
        // =====================================================

        output.append(
                "EXACT SEARCH (KMP)\n"
        );

        output.append(
                "------------------\n"
        );

        for (String term : expandedQuery) {

            Set<String> matchingDocIds =
                    index.search(term);

            if (matchingDocIds.isEmpty()) {
                continue;
            }

            foundExactMatch = true;

            output.append(
                    "\nKeyword: "
            );

            output.append(term);

            output.append("\n");

            for (String docId :
                    matchingDocIds) {

                Document matchedDoc =
                        getDocumentById(docId);

                if (matchedDoc != null) {

                    int occurrences =
                            kmpMatcher.countOccurrences(
                                    matchedDoc.getContent(),
                                    term
                            );

                    output.append(
                            "  ✓ "
                    );

                    output.append(docId);

                    output.append(
                            " | Occurrences: "
                    );

                    output.append(
                            occurrences
                    );

                    output.append("\n");
                }
            }
        }

        if (!foundExactMatch) {

            output.append(
                    "No exact matches found.\n"
            );
        }

        // =====================================================
        // STEP 3: FUZZY SEARCH USING EDIT DISTANCE
        // =====================================================

        if (!foundExactMatch) {

            output.append(
                    "\nFUZZY SEARCH (EDIT DISTANCE)\n"
            );

            output.append(
                    "-----------------------------\n"
            );

            boolean foundFuzzyMatch = false;

            for (Document doc : documents) {

                Set<String> keywords =
                        keywordExtractor.uniqueKeywords(
                                doc.getContent()
                        );

                for (String keyword : keywords) {

                    int distance =
                            editDistance.calculate(
                                    query,
                                    keyword
                            );

                    // Allow up to two edits
                    if (distance <= 2) {

                        foundFuzzyMatch = true;

                        int occurrences =
                                kmpMatcher.countOccurrences(
                                        doc.getContent(),
                                        keyword
                                );

                        output.append(
                                "  ✓ "
                        );

                        output.append(
                                doc.getDocumentId()
                        );

                        output.append(
                                " | Similar keyword: "
                        );

                        output.append(
                                keyword
                        );

                        output.append(
                                " | Edit distance: "
                        );

                        output.append(
                                distance
                        );

                        output.append(
                                " | Occurrences: "
                        );

                        output.append(
                                occurrences
                        );

                        output.append("\n");
                    }
                }
            }

            if (!foundFuzzyMatch) {

                output.append(
                        "No similar keywords found.\n"
                );
            }
        }

        output.append(
                "\n========================================\n"
        );

        return output.toString();
    }

    // =========================================================
    // TRIE SUGGESTIONS
    // =========================================================

    public List<String> getSuggestions(
            String prefix) {

        prefix = prefix.trim().toLowerCase();

        if (prefix.length() == 0) {

            return new ArrayList<>();
        }

        return trie.getSuggestions(prefix);
    }

    // =========================================================
    // GET DOCUMENT
    // =========================================================

    private Document getDocumentById(
            String docId) {

        for (Document doc : documents) {

            if (doc.getDocumentId().equals(docId)) {

                return doc;
            }
        }

        return null;
    }

    // =========================================================
    // GET INVERTED INDEX
    // =========================================================

    public InvertedIndex getIndex() {

        return index;
    }

    // =========================================================
    // GET DOCUMENT COUNT
    // =========================================================

    public int getDocumentCount() {

        return documents.size();
    }
}
