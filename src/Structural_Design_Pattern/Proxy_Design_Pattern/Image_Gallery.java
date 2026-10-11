package Structural_Design_Pattern.Proxy_Design_Pattern;

interface Image {
    void display();
    String getFileName();
}

class HighResolutionImage implements Image {
    private String fileName;
    private byte[] imageData;

    public HighResolutionImage(String fileName) {
        this.fileName = fileName;
        loadImageFromDisk();
    }

    private void loadImageFromDisk() {
        System.out.println("Loading image: " + fileName + " from disk (Expensive Operation)...");
        try {
            Thread.sleep(200);
            this.imageData = new byte[10 * 1024 * 1024];
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Image " + fileName + " loaded successfully.");
    }

    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}


class ImageProxy implements Image{
    private final String fileName;
    private HighResolutionImage highResolutionImage;

    ImageProxy(String fileName){
        this.fileName=fileName;
    }

    @Override
    public void display() {
        if(highResolutionImage==null){
            synchronized(ImageProxy.class){
                if(highResolutionImage==null){
                    System.out.println("ImageProxy: display() requested for " + fileName + ". Loading high-resolution image...");
                    highResolutionImage = new HighResolutionImage(fileName);
                }
            }
        } else {
            System.out.println("ImageProxy: Using cached high-resolution image for " + fileName);
        }
        highResolutionImage.display();
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}

public class Image_Gallery {
    public static void main(String[] args) {
        System.out.println("Application Started. Initializing image proxies for gallery...");

        Image image1 = new ImageProxy("photo1.jpg");
        Image image2 = new ImageProxy("photo2.png");
        Image image3 = new ImageProxy("photo3.gif");

        System.out.println("\nGallery initialized. No images actually loaded yet.");
        System.out.println("Image 1 Filename: " + image1.getFileName());

        System.out.println("\nUser requests to display " + image1.getFileName());
        image1.display();

        System.out.println("\nUser requests to display " + image1.getFileName() + " again.");
        image1.display();

        System.out.println("\nUser requests to display " + image3.getFileName());
        image3.display();

        System.out.println("\nApplication finished. Note: photo2.png was never loaded.");
    }
}
