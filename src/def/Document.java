package def;

public class Document {
    
    // Variables to store the document's ID and its text
    private String documentId;
    private String content;

    // Constructor
    public Document(String documentId, String content) {
        this.documentId = documentId;
        this.content = content;
    }

    // Get document ID
    public String getDocumentId() {
        return documentId;
    }

    // Get document content
    public String getContent() {
        return content;
    }
}