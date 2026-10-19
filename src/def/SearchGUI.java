
package def;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class SearchGUI extends JFrame {

    private SearchEngine engine;

    private JTextField searchField;
    private JTextArea resultArea;

    private JLabel statusLabel;
    private JLabel documentCountLabel;
    private JLabel keywordCountLabel;

    // =========================================================
    // COLOR THEME
    // =========================================================

    private final Color DARK_BLUE =
            new Color(31, 52, 78);

    private final Color BLUE =
            new Color(52, 101, 164);

    private final Color LIGHT_BLUE =
            new Color(230, 238, 248);

    private final Color BACKGROUND =
            new Color(242, 244, 247);

    private final Color WHITE =
            Color.WHITE;

    private final Color TEXT =
            new Color(45, 45, 45);

    private final Color GREEN =
            new Color(48, 122, 84);

    private final Color BORDER =
            new Color(210, 215, 222);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public SearchGUI() {

        // Create search engine
        engine = new SearchEngine();

        // Load documents and build index
        Main.setupForGUI(engine);

        // Window settings
        setTitle(
                "Semantic Keyword Extraction and Corpus Indexing Framework"
        );

        setSize(1000, 700);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        // Background
        getContentPane().setBackground(
                BACKGROUND
        );

        createInterface();
    }

    // =========================================================
    // CREATE INTERFACE
    // =========================================================

    private void createInterface() {

        setLayout(
                new BorderLayout(0, 0)
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(
                        new GridLayout(2, 1)
                );

        headerPanel.setBackground(
                DARK_BLUE
        );

        headerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        20,
                        18,
                        20
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "SEMANTIC KEYWORD SEARCH ENGINE",
                        SwingConstants.CENTER
                );

        titleLabel.setForeground(
                WHITE
        );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Corpus Indexing  •  KMP  •  Edit Distance  •  Trie",
                        SwingConstants.CENTER
                );

        subtitleLabel.setForeground(
                new Color(210, 220, 235)
        );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // MAIN CENTER PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
                );

        mainPanel.setBackground(
                BACKGROUND
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        20,
                        10,
                        20
                )
        );

        // =====================================================
        // SEARCH PANEL
        // =====================================================

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        searchPanel.setBackground(
                WHITE
        );

        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                12,
                                15,
                                12,
                                15
                        )
                )
        );

        JLabel searchLabel =
                new JLabel(
                        "Search Query"
                );

        searchLabel.setForeground(
                TEXT
        );

        searchLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        searchField =
                new JTextField();

        searchField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        searchField.setForeground(
                TEXT
        );

        searchField.setBackground(
                WHITE
        );

        searchField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                7,
                                10,
                                7,
                                10
                        )
                )
        );

        JButton searchButton =
                createButton(
                        "SEARCH",
                        BLUE
                );

        JButton clearButton =
                createButton(
                        "CLEAR",
                        new Color(110, 118, 128)
                );

        JButton indexButton =
                createButton(
                        "VIEW INDEX",
                        DARK_BLUE
                );

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                6,
                                0
                        )
                );

        buttonPanel.setBackground(
                WHITE
        );

        buttonPanel.add(searchButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(indexButton);

        searchPanel.add(
                searchLabel,
                BorderLayout.WEST
        );

        searchPanel.add(
                searchField,
                BorderLayout.CENTER
        );

        searchPanel.add(
                buttonPanel,
                BorderLayout.EAST
        );

        mainPanel.add(
                searchPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // RESULTS PANEL
        // =====================================================

        JPanel resultsPanel =
                new JPanel(
                        new BorderLayout()
                );

        resultsPanel.setBackground(
                WHITE
        );

        resultsPanel.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

        // Results title
        JLabel resultsTitle =
                new JLabel(
                        "  SEARCH RESULTS"
                );

        resultsTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        resultsTitle.setForeground(
                DARK_BLUE
        );

        resultsTitle.setOpaque(
                true
        );

        resultsTitle.setBackground(
                LIGHT_BLUE
        );

        resultsTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        5,
                        8,
                        5
                )
        );

        resultsPanel.add(
                resultsTitle,
                BorderLayout.NORTH
        );

        // Text area
        resultArea =
                new JTextArea();

        resultArea.setEditable(
                false
        );

        resultArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        resultArea.setForeground(
                TEXT
        );

        resultArea.setBackground(
                WHITE
        );

        resultArea.setLineWrap(
                true
        );

        resultArea.setWrapStyleWord(
                true
        );

        resultArea.setMargin(
                new Insets(
                        15,
                        15,
                        15,
                        15
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        resultArea
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        resultsPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                resultsPanel,
                BorderLayout.CENTER
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // STATUS BAR
        // =====================================================

        JPanel statusPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                10,
                                0
                        )
                );

        statusPanel.setBackground(
                WHITE
        );

        statusPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                1,
                                0,
                                0,
                                0,
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                8,
                                20,
                                8,
                                20
                        )
                )
        );

        documentCountLabel =
                createStatusLabel(
                        "Documents indexed: "
                                + engine.getDocumentCount()
                );

        keywordCountLabel =
                createStatusLabel(
                        "Unique keywords: "
                                + engine.getIndex().size()
                );

        statusLabel =
                createStatusLabel(
                        "● Ready"
                );

        statusLabel.setForeground(
                GREEN
        );

        statusPanel.add(
                documentCountLabel
        );

        statusPanel.add(
                keywordCountLabel
        );

        statusPanel.add(
                statusLabel
        );

        add(
                statusPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // WELCOME MESSAGE
        // =====================================================

        resultArea.setText(
                "Welcome to the Semantic Keyword Search Engine!\n\n"

                + "This system demonstrates:\n\n"

                + "  • Inverted Indexing\n"
                + "  • KMP Exact Pattern Matching\n"
                + "  • Edit Distance Fuzzy Matching\n"
                + "  • Trie Prefix Suggestions\n"
                + "  • Semantic Keyword Expansion\n\n"

                + "Enter a keyword above and click SEARCH.\n"
                + "Use VIEW INDEX to inspect the indexed corpus."
        );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        searchButton.addActionListener(
                e -> performSearch()
        );

        clearButton.addActionListener(
                e -> clearSearch()
        );

        indexButton.addActionListener(
                e -> displayIndex()
        );

        // ENTER KEY
        searchField.addActionListener(
                e -> performSearch()
        );
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color background) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(
                WHITE
        );

        button.setBackground(
                background
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        12,
                        8,
                        12
                )
        );

        return button;
    }

    // =========================================================
    // CREATE STATUS LABEL
    // =========================================================

    private JLabel createStatusLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        label.setForeground(
                TEXT
        );

        return label;
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void performSearch() {

        String query =
                searchField.getText().trim();

        if (query.isEmpty()) {

            resultArea.setText(
                    "Please enter a search query."
            );

            statusLabel.setText(
                    "● No query entered"
            );

            statusLabel.setForeground(
                    new Color(180, 70, 60)
            );

            return;
        }

        // =====================================================
        // TRIE SUGGESTIONS
        // =====================================================

        List<String> suggestions =
                engine.getSuggestions(query);

        StringBuilder output =
                new StringBuilder();

        output.append(
                "==================================================\n"
        );

        output.append(
                "                  SEARCH RESULTS\n"
        );

        output.append(
                "==================================================\n\n"
        );

        output.append(
                "Query: "
        );

        output.append(
                query
        );

        output.append(
                "\n\n"
        );

        // =====================================================
        // TRIE
        // =====================================================

        output.append(
                "TRIE SUGGESTIONS\n"
        );

        output.append(
                "----------------\n"
        );

        if (suggestions.isEmpty()) {

            output.append(
                    "No prefix suggestions found.\n"
            );

        } else {

            for (String suggestion :
                    suggestions) {

                output.append(
                        "  • "
                );

                output.append(
                        suggestion
                );

                output.append(
                        "\n"
                );
            }
        }

        output.append(
                "\n"
        );

        // =====================================================
        // SEARCH ENGINE
        // =====================================================

        String searchResults =
                engine.searchAndReturnResults(
                        query
                );

        output.append(
                searchResults
        );

        // =====================================================
        // DISPLAY
        // =====================================================

        resultArea.setText(
                output.toString()
        );

        resultArea.setCaretPosition(
                0
        );

        statusLabel.setText(
                "● Search completed: " + query
        );

        statusLabel.setForeground(
                GREEN
        );
    }

    // =========================================================
    // DISPLAY INVERTED INDEX
    // =========================================================

    private void displayIndex() {

        Set<String> keywords =
                engine.getIndex().getAllKeywords();

        StringBuilder output =
                new StringBuilder();

        output.append(
                "==================================================\n"
        );

        output.append(
                "                  INVERTED INDEX\n"
        );

        output.append(
                "==================================================\n\n"
        );

        output.append(
                "Total unique keywords: "
        );

        output.append(
                keywords.size()
        );

        output.append(
                "\n\n"
        );

        output.append(
                "KEYWORD                  DOCUMENTS\n"
        );

        output.append(
                "-----------------------------------------------\n"
        );

        for (String keyword :
                keywords) {

            Set<String> documents =
                    engine.getIndex().search(
                            keyword
                    );

            output.append(
                    String.format(
                            "%-24s %s",
                            keyword,
                            documents
                    )
            );

            output.append(
                    "\n"
            );
        }

        output.append(
                "\n-----------------------------------------------\n"
        );

        output.append(
                "The inverted index maps each keyword\n"
                + "to the documents containing that keyword."
        );

        resultArea.setText(
                output.toString()
        );

        resultArea.setCaretPosition(
                0
        );

        statusLabel.setText(
                "● Displaying inverted index"
        );

        statusLabel.setForeground(
                DARK_BLUE
        );
    }

    // =========================================================
    // CLEAR SEARCH
    // =========================================================

    private void clearSearch() {

        searchField.setText("");

        resultArea.setText(
                "Search cleared.\n\n"
                + "Enter a new keyword to begin searching."
        );

        statusLabel.setText(
                "● Ready"
        );

        statusLabel.setForeground(
                GREEN
        );

        searchField.requestFocus();
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(
                () -> {

                    SearchGUI gui =
                            new SearchGUI();

                    gui.setVisible(true);
                }
        );
    }
}
