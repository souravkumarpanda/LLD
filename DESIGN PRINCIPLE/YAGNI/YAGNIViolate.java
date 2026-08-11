package yagni;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

// Interface for handling different media types
interface IMediaHandler {
    boolean canHandle(String fileType);

    File process(File file);
}

// Interface for storage providers
interface IStorageProvider {
    void store(File file, String path);

    File retrieve(String path);

    void delete(String path);
}

// Factory for creating media handlers
class MediaHandlerFactory {

    private final Map<String, IMediaHandler> handlers = new HashMap<>();

    public void register(String type, IMediaHandler handler) {
        handlers.put(type, handler);
    }

    public IMediaHandler getHandler(String fileType) {

        IMediaHandler handler = handlers.get(fileType);

        if (handler == null) {
            throw new UnsupportedOperationException(
                    "No handler for type: " + fileType
            );
        }

        return handler;
    }
}

// Cloud storage adapter
class CloudStorageAdapter implements IStorageProvider {

    private final String bucketName;
    private final String region;

    public CloudStorageAdapter(String bucketName, String region) {
        this.bucketName = bucketName;
        this.region = region;
    }

    @Override
    public void store(File file, String path) {
        System.out.println(
                "Uploading " + file.getName()
                        + " to cloud bucket: " + bucketName
                        + " at path: " + path
        );
    }

    @Override
    public File retrieve(String path) {
        System.out.println(
                "Downloading file from: " + path
                        + " in region: " + region
        );

        return null;
    }

    @Override
    public void delete(String path) {
        System.out.println(
                "Deleting file from: " + path
        );
    }
}

// Image handler
class ImageMediaHandler implements IMediaHandler {

    @Override
    public boolean canHandle(String fileType) {
        return fileType.equals("image");
    }

    @Override
    public File process(File file) {
        System.out.println("Processing image: " + file.getName());

        // Resize image to 300x300
        return resize(file, 300, 300);
    }

    private File resize(File file, int width, int height) {

        System.out.println(
                "Resizing " + file.getName()
                        + " to " + width + "x" + height
        );

        // Actual resize implementation would go here
        return file;
    }
}

// Media processing engine
class MediaProcessingEngine {

    private final MediaHandlerFactory handlerFactory;
    private final IStorageProvider storageProvider;

    public MediaProcessingEngine(
            MediaHandlerFactory handlerFactory,
            IStorageProvider storageProvider) {

        this.handlerFactory = handlerFactory;
        this.storageProvider = storageProvider;
    }

    public void upload(File file, String fileType, String path) {

        // Get appropriate media handler
        IMediaHandler handler =
                handlerFactory.getHandler(fileType);

        // Process the file
        File processed = handler.process(file);

        // Store the processed file
        storageProvider.store(processed, path);
    }
}

// Main class
public class YAGNIViolate {

    public static void main(String[] args) {

        // 1. Create the media handler factory
        MediaHandlerFactory handlerFactory =
                new MediaHandlerFactory();

        // 2. Create and register image handler
        ImageMediaHandler imageHandler =
                new ImageMediaHandler();

        handlerFactory.register("image", imageHandler);

        // 3. Create cloud storage provider
        IStorageProvider storageProvider =
                new CloudStorageAdapter(
                        "my-media-bucket",
                        "ap-south-1"
                );

        // 4. Create media processing engine
        MediaProcessingEngine engine =
                new MediaProcessingEngine(
                        handlerFactory,
                        storageProvider
                );

        // 5. Create a sample file
        File imageFile =
                new File("profile.jpg");

        // 6. Upload and process the image
        engine.upload(
                imageFile,
                "image",
                "users/profile.jpg"
        );
    }
}