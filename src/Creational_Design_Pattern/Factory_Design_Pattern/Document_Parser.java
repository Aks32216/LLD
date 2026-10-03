package Creational_Design_Pattern.Factory_Design_Pattern;

/*
Problem Statement:
Build a tool that opens files of different types (PDF,Word,CSV) and returns a Document object
you can read uniformly. The file type is detected at runtime from the extension. New formats will be added.
*/

interface Document{
    public void open();
}

class PDF implements Document{
    private String contents;

    PDF(String contents){
        this.contents=contents;
    }

    @Override
    public void open() {
        System.out.println("reading contents of PDF file...");
        System.out.println(contents);
    }
}

class CSV implements Document{
    private String contents;

    CSV(String contents){
        this.contents=contents;
    }

    @Override
    public void open() {
        System.out.println("reading contents of CSV file...");
        System.out.println(contents);
    }
}

class Word implements Document{
    private String contents;

    Word(String contents){
        this.contents=contents;
    }

    @Override
    public void open() {
        System.out.println("reading contents of Word file...");
        System.out.println(contents);
    }
}

class DocumentCreator{
    public static Document createDocument(String filename,String contents){
        String[] names=filename.split("\\.");
        String extension = names[names.length-1];
        System.out.println("Extension "+extension);
        return switch (extension) {
            case "pdf" -> new PDF(contents);
            case "csv" -> new CSV(contents);
            case "docx", "docs" -> new Word(contents);
            default -> throw new IllegalArgumentException();
        };
    }
}

public class Document_Parser {
    public static void main(String[] args) {
        Document document=DocumentCreator.createDocument("name.pdf","Here is the file content for pdf");
        document.open();
        document=DocumentCreator.createDocument("amish.docs","Here is content for word document");
        document.open();
        document=DocumentCreator.createDocument("name.name.csv","here is the content for csv doc");
        document.open();
    }
}
