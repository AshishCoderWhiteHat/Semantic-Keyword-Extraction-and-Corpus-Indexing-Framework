package def;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("SEMANTIC KEYWORD EXTRACTION AND CORPUS INDEXING");
        System.out.println("==============================================\n");

        SearchEngine engine = new SearchEngine();

        // 1. Prepare and load the files
        setupSampleDocuments();
        loadDocuments(engine);

        // 2. Process the text and build the Inverted Index
        engine.buildFromDocuments();

        System.out.println(
                "Documents indexed: "
                + engine.getDocumentCount()
        );

        System.out.println(
                "Unique keywords in system: "
                + engine.getIndex().size()
                + "\n"
        );

        // 3. Start the Console User Interface
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\nChoose an option:");
            System.out.println("1. Search");
            System.out.println("2. Display Inverted Index");
            System.out.println("3. Exit");
            System.out.print("\nEnter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":

                    System.out.print("Enter keyword: ");

                    String query = scanner.nextLine();

                    engine.search(query);

                    break;

                case "2":

                    engine.getIndex().displayIndex();

                    break;

                case "3":

                    running = false;

                    System.out.println(
                            "Exiting the system. Goodbye!"
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }
        }

        scanner.close();
    }

    // =========================================================
    // METHOD USED BY THE GUI
    // =========================================================

    /*
     * This method prepares the SearchEngine for the GUI.
     *
     * It:
     * 1. Creates the sample documents if necessary.
     * 2. Loads the documents.
     * 3. Builds the Inverted Index and Trie.
     */
    public static void setupForGUI(SearchEngine engine) {

        setupSampleDocuments();

        loadDocuments(engine);

        engine.buildFromDocuments();
    }

    // =========================================================
    // SAMPLE DOCUMENT CREATION
    // =========================================================

    /*
     * Creates a "documents" folder and adds sample files
     * if the folder does not already exist.
     */
    private static void setupSampleDocuments() {

        File dir = new File("documents");

        if (!dir.exists()) {

            dir.mkdir();

            createFile(
                    "documents/Doc1.txt",
                    "Java is a popular programming language. "
                    + "Java is used for software development "
                    + "and data structures."
            );

            createFile(
                    "documents/Doc2.txt",
                    "Artificial intelligence and machine learning "
                    + "are used in modern computer applications."
            );

            createFile(
                    "documents/Doc3.txt",
                    "Databases store information. SQL is a language "
                    + "used to work with database systems."
            );

            createFile(
                    "documents/Doc4.txt",
                    "Automobiles are vehicles. Modern cars use "
                    + "computer systems and software for navigation."
            );
        }
    }

    // =========================================================
    // FILE CREATION
    // =========================================================

    /*
     * Creates a text file and writes the given content.
     */
    private static void createFile(
            String path,
            String content) {

        try {

            FileWriter writer = new FileWriter(path);

            writer.write(content);

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error creating file: " + path
            );
        }
    }

    // =========================================================
    // DOCUMENT LOADING
    // =========================================================

    /*
     * Reads all .txt files from the documents folder
     * and adds them to the SearchEngine.
     */
    private static void loadDocuments(
            SearchEngine engine) {

        File dir = new File("documents");

        File[] files = dir.listFiles();

        if (files != null) {

            for (File file : files) {

                // Only process text files
                if (file.isFile()
                        && file.getName().endsWith(".txt")) {

                    try {

                        // Read the complete file
                        String content =
                                new String(
                                        Files.readAllBytes(
                                                Paths.get(
                                                        file.getPath()
                                                )
                                        )
                                );

                        // Add document to SearchEngine
                        engine.addDocument(
                                file.getName(),
                                content
                        );

                    } catch (IOException e) {

                        System.out.println(
                                "Error reading file: "
                                + file.getName()
                        );
                    }
                }
            }
        }
    }
}