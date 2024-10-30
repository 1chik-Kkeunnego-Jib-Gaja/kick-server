package com.example.kick.infra.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Facade implements ImageUtil {

    private final S3Properties s3Properties;
    private final AmazonS3 amazonS3;

    @Override
    public String uploadImage(MultipartFile image) {
        if (image.isEmpty()) {
            throw new IllegalArgumentException("image file is empty");
        }

        String fileName = s3Properties.getBucket() + "/" + UUID.randomUUID() + image.getOriginalFilename();

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                s3Properties.getBucket(),
                fileName,
                image.getInputStream(),
                getObjectMetadata(image)
            );

            amazonS3.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new RuntimeException("failed to save image", e);
        }

        return getFileUrl(fileName);
    }

    private ObjectMetadata getObjectMetadata(MultipartFile image) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(image.getSize());
        objectMetadata.setContentType(image.getContentType());

        return objectMetadata;
    }

    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(s3Properties.getBucket(), fileName).toString();
    }
}
