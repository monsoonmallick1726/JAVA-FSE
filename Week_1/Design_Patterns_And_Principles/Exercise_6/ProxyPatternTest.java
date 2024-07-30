// Subject Interface
interface Image {
    void display();
}

// Real Subject
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading " + filename + " from remote server.");
    }

    @Override
    public void display() {
        System.out.println("Displaying " + filename);
    }
}
// Proxy
class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}
public class ProxyPatternTest {
    public static void main(String[] args) {
        // Create an array of image filenames
        String[] filenames = {"image1.jpg", "image2.jpg", "image3.jpg"};
        
        // Create ProxyImage objects
        Image[] images = new Image[filenames.length];
        for (int i = 0; i < filenames.length; i++) {
            images[i] = new ProxyImage(filenames[i]);
        }

        // First time: images will be loaded from remote server
        System.out.println("First time display:");
        for (Image image : images) {
            image.display();
        }
        System.out.println();

        // Second time: images will be displayed without loading from remote server
        System.out.println("Second time display (using cached images):");
        for (Image image : images) {
            image.display();
        }
    }
}
