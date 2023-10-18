package staaankey.group.accountingsalaries.minio.service;

import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import staaankey.group.accountingsalaries.minio.config.MinioConfiguration;
import staaankey.group.accountingsalaries.minio.exception.ResourceNotFoundException;
import staaankey.group.accountingsalaries.minio.exception.ResourceWriteException;
import staaankey.group.accountingsalaries.minio.util.FileTypeUtility;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final MinioClient minioClient;
    private final MinioConfiguration minioConfiguration;


    public boolean bucketExists(String bucketName) {
        try {
            BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build();

            return minioClient.bucketExists(bucketExistsArgs);
        } catch (Exception e) {
            return false;
        }
    }


    public void makeBucket(String bucketName) throws ResourceWriteException {
        if (bucketExists(bucketName)) {
            throw new ResourceWriteException(String.format("Bucket with name: %s already exists", bucketName));
        }

        try {
            MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build();

            minioClient.makeBucket(makeBucketArgs);
        } catch (Exception e) {
            throw new ResourceWriteException(String.format("Cannot create bucket with name: %s", bucketName));
        }
    }


    public List<String> listBucketName() throws ResourceNotFoundException {
        List<Bucket> bucketList;

        try {
            bucketList = listBuckets();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Cannot represent all buckets names");
        }

        return bucketList.stream()
                .map(Bucket::name)
                .collect(Collectors.toList());
    }


    public void removeBucket(String bucketName) throws ResourceWriteException, ResourceNotFoundException {
        checkBucketExist(bucketName);

        try {
            for (Result<Item> result : listObjects(bucketName)) {
                Item item = result.get();

                checkBucketEmptiness(bucketName, item);
            }

            RemoveBucketArgs removeBucketArgs = RemoveBucketArgs.builder()
                    .bucket(bucketName)
                    .build();

            minioClient.removeBucket(removeBucketArgs);
        } catch (Exception e) {
            throw new ResourceWriteException(e.getMessage());
        }

    }


    public List<String> listImageNames(String bucketName) throws ResourceWriteException, ResourceNotFoundException {
        checkBucketExist(bucketName);

        List<String> listObjectNames = new ArrayList<>();

        for (Result<Item> result : listObjects(bucketName)) {
            Item item;
            try {
                item = result.get();
            } catch (Exception e) {
                throw new ResourceWriteException("Cannot represent images names");
            }

            listObjectNames.add(item.objectName());
        }

        return listObjectNames;
    }


    public String uploadImage(MultipartFile file, String bucketName) throws ResourceWriteException {
        try {
            bucketName = StringUtils.isNotBlank(bucketName) ? bucketName : minioConfiguration.getBucketName();

            makeBucketIfItDoesntExist(bucketName);

            String fileName = file.getOriginalFilename();

            String objectName = UUID.randomUUID().toString().replaceAll("-", "")
                    + requireNonNull(fileName).substring(fileName.lastIndexOf("."));
            putObject(bucketName, file, objectName);

            return String.format("%S/%S/%S", minioConfiguration.getEndpoint(), bucketName, objectName);
        } catch (Exception e) {
            throw new ResourceWriteException(e.getMessage());
        }
    }


    public InputStream downloadImage(String bucketName, String objectName) throws ResourceNotFoundException {
        checkBucketExist(bucketName);

        try {
            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();

            return minioClient.getObject(getObjectArgs);
        } catch (Exception e) {
            throw new ResourceNotFoundException(
                    String.format("Cannot find image with name: %s", objectName)
            );
        }
    }


    public void removeImage(String bucketName, String imageName) throws ResourceWriteException {
        if (!bucketExists(bucketName)) return;

        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(imageName)
                    .build();

            minioClient.removeObject(removeObjectArgs);
        } catch (Exception e) {
            throw new ResourceWriteException(
                    String.format("Cannot remove image: %s, in bucket: %s", imageName, bucketName)
            );
        }
    }

    private List<Bucket> listBuckets()
            throws ServerException, InsufficientDataException,
            ErrorResponseException, IOException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidResponseException,
            XmlParserException, InternalException {

        return minioClient.listBuckets();
    }

    private void checkBucketEmptiness(String bucketName, Item item) throws ResourceWriteException {
        if (item.size() > 0) {
            throw new ResourceWriteException(String.format("Bucket: %s not empty", bucketName));
        }
    }

    private void checkBucketExist(String bucketName) throws ResourceNotFoundException {
        if (!bucketExists(bucketName)) {
            throw new ResourceNotFoundException(
                    String.format("Cannot find bucket with name: %s", bucketName)
            );
        }
    }

    private Iterable<Result<Item>> listObjects(String bucketName) throws ResourceNotFoundException {
        checkBucketExist(bucketName);

        ListObjectsArgs listObjectsArgs = ListObjectsArgs.builder()
                .bucket(bucketName)
                .build();

        return minioClient.listObjects(listObjectsArgs);
    }

    private void putObject(String bucketName, MultipartFile multipartFile, String filename)
            throws ServerException, InsufficientDataException,
            ErrorResponseException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidResponseException,
            XmlParserException, InternalException {

        try (InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes())) {

            String fileType = checkImageTypeWithItsReturning(multipartFile);

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(filename)
                            .stream(inputStream, -1, minioConfiguration.getImageSize())
                            .contentType(fileType)
                            .build()
            );
        } catch (IOException | ResourceWriteException ignored) {

        }
    }

    private String checkImageTypeWithItsReturning(MultipartFile multipartFile) throws ResourceWriteException {
        String fileType = FileTypeUtility.getFileType(multipartFile);

        if (isNull(fileType)) {
            throw new ResourceWriteException("Upload only jpg or png files");
        }

        return fileType;
    }

    private void makeBucketIfItDoesntExist(String bucketName) throws ResourceWriteException {
        if (!this.bucketExists(bucketName)) {
            this.makeBucket(bucketName);
        }
    }

}

