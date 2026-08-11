package yagni;

import java.io.File;

// Image Resizer
class ImageResizer {
    public File resize(File imageFile, int width, int height) {
        System.out.println("Resizing image to " + width + "x" + height);
        return imageFile;
    }
}

// Local Storage
class LocalStorage {
    public void save(File file) {
        System.out.println("Saving image: " + file.getName());
    }
}

// Image Uploader
class ImageUploader {
    private final ImageResizer resizer;
    private final LocalStorage storage;

    public ImageUploader(ImageResizer resizer, LocalStorage storage) {
        this.resizer = resizer;
        this.storage = storage;
    }

    public void upload(File imageFile) {
        File resized = resizer.resize(imageFile, 300, 300);
        storage.save(resized);
    }
}

// Main class
public class YAGNIFollowed {
    public static void main(String[] args) {

        // Create dependencies
        ImageResizer resizer = new ImageResizer();
        LocalStorage storage = new LocalStorage();

        // Inject dependencies into ImageUploader
        ImageUploader uploader = new ImageUploader(resizer, storage);

        // Create an image file
        File imageFile = new File("profile.jpg");

        // Upload image
        uploader.upload(imageFile);
    }
}