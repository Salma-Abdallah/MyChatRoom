package gov.iti.jets.utilities;


import gov.iti.jets.dtos.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ImageUtilities {

    public static String storeImage(User userDto) {
        try {
            Path filePath = Paths
                    .get("userPic/" + userDto.getPhoneNumber() + "/profile" + "." + userDto.getPictureExtension());
            Path parent = filePath.getParent();
            if (!Files.exists(parent)) {
                Files.createDirectories(parent);
            }

            Files.write(filePath,
                    userDto.getPicture(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE);
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static byte[] loadImage(String url) {
        try {
            return Files.readAllBytes(Paths.get(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
