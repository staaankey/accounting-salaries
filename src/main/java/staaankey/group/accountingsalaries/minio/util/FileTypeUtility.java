package staaankey.group.accountingsalaries.minio.util;

import cn.hutool.core.io.FileTypeUtil;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import static java.util.Objects.nonNull;

@UtilityClass
public class FileTypeUtility {

    private static final String IMAGE_TYPE = "image/";

    public static String getFileType(MultipartFile multipartFile) {
        String type = null;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            type = FileTypeUtil.getType(inputStream);

            if (nonNull(type) && isImageType(type)) {
                return IMAGE_TYPE + type;
            }
        } catch (IOException ignored) {

        }

        return type;
    }

    private boolean isImageType(String type) {
        return type.equalsIgnoreCase("JPG")
                || type.equalsIgnoreCase("JPEG")
                || type.equalsIgnoreCase("PNG");
    }

}

