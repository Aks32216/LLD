package Structural_Design_Pattern.Composite_Design_Pattern;

import java.util.ArrayList;
import java.util.List;

interface FileSystemItem {
    int getSize();
    void printStructure(String indent);
    void delete();
}

class File implements FileSystemItem{
    private int size;
    private String name;

    File(String name){
        this.size=0;
        this.name=name;
    }

    File(String name,int size){
        this.size=size;
        this.name=name;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "- " + name + " (" + size + " KB)");
    }

    @Override
    public void delete() {
        System.out.println("Deleting file: " + name);
    }
}

class Folder implements FileSystemItem{
    private String name;
    private List<FileSystemItem> fileSystemItems=new ArrayList<>();

    Folder(String name){
        this.name=name;
    }

    public void add(FileSystemItem fileSystemItem){
        fileSystemItems.add(fileSystemItem);
    }

    public void remove(FileSystemItem fileSystemItem){
        fileSystemItems.remove(fileSystemItem);
    }

    @Override
    public int getSize() {
        int totalSize=0;
        for(FileSystemItem fileSystemItem: fileSystemItems){
            totalSize+=fileSystemItem.getSize();
        }
        return totalSize;
    }

    @Override
    public void printStructure(String indent) {
        System.out.println(indent + "+ " + name + "/");
        for (FileSystemItem item : fileSystemItems) {
            item.printStructure(indent + "  ");
        }
    }

    @Override
    public void delete() {
        for (FileSystemItem item : fileSystemItems) {
            item.delete();
        }
        System.out.println("Deleting folder: " + name);
    }
}

public class File_System {
    public static void main(String[] args) {
        FileSystemItem file1 = new File("readme.txt", 5);
        FileSystemItem file2 = new File("photo.jpg", 1500);
        FileSystemItem file3 = new File("data.csv", 300);

        Folder documents = new Folder("Documents");
        documents.add(file1);
        documents.add(file3);

        Folder pictures = new Folder("Pictures");
        pictures.add(file2);

        Folder home = new Folder("Home");
        home.add(documents);
        home.add(pictures);

        System.out.println("---- File Structure ----");
        home.printStructure("");

        System.out.println("\nTotal Size: " + home.getSize() + " KB");

        System.out.println("\n---- Deleting All ----");
        home.delete();
    }
}
