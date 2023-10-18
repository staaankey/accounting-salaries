package staaankey.group.accountingsalaries.minio.web.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import staaankey.group.accountingsalaries.minio.exception.ResourceNotFoundException;
import staaankey.group.accountingsalaries.minio.exception.ResourceWriteException;
import staaankey.group.accountingsalaries.minio.service.MinioService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequestMapping("/minio/buckets")
@RestController
@RequiredArgsConstructor
public class MinioController {

    private final MinioService minioService;

    @GetMapping
    public ResponseEntity<List<String>> showBucketName() throws ResourceNotFoundException {
        return ResponseEntity.ok(minioService.listBucketName());
    }

    @GetMapping("/{bucketName}")
    public ResponseEntity<List<String>> show(@PathVariable String bucketName) throws ResourceWriteException, ResourceNotFoundException {
        return ResponseEntity.ok(minioService.listImageNames(bucketName));
    }

    @GetMapping(value = "/{bucketName}/{imageName}",
            produces = {
                    MediaType.IMAGE_PNG_VALUE,
                    MediaType.IMAGE_JPEG_VALUE,
            }
    )
    public ResponseEntity<byte[]> download(
            @PathVariable("bucketName") String bucketName,
            @PathVariable("imageName") String imageName
    ) throws IOException, ResourceNotFoundException {
        InputStream in = minioService.downloadImage(bucketName, imageName);

        return ResponseEntity.ok()
                .body(IOUtils.toByteArray(in));
    }

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file, String bucketName) throws ResourceWriteException {
        return minioService.uploadImage(file, bucketName);
    }

    @PostMapping("/{bucketName}")
    public ResponseEntity<?> addBucket(@PathVariable String bucketName) throws ResourceWriteException {
        minioService.makeBucket(bucketName);

        return ResponseEntity.ok(String.format("Create bucket: %s", bucketName));
    }

    @DeleteMapping("/{bucketName}")
    public ResponseEntity<?> deleteBucketName(@PathVariable String bucketName) throws ResourceWriteException, ResourceNotFoundException {
        minioService.removeBucket(bucketName);

        return ResponseEntity.ok(String.format("Successfully delete bucket: %s", bucketName));
    }

    @DeleteMapping("/{bucketName}/{imageName}")
    public ResponseEntity<?> deleteImage(
            @PathVariable("bucketName") String bucketName,
            @PathVariable("imageName") String imageName
    ) throws ResourceWriteException {
        minioService.removeImage(bucketName, imageName);

        return ResponseEntity.ok(
                String.format("Successfully delete image: %s, in bucket: %s", imageName, bucketName)
        );
    }

}

