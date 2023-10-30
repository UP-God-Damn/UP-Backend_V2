package com.dsm.up_backend_v2.global.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.dsm.up_backend_v2.global.aws.exception.ImageFormFalseException;
import com.dsm.up_backend_v2.global.aws.exception.ImageUploadFailException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RequiredArgsConstructor
@Component
public class S3Util {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.url}")
    private String baseUrl;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public void delete(String objectName) {
        amazonS3.deleteObject(bucketName, objectName);
    }

    public String getUrl(String path) {
        return baseUrl + "/" + path;
    }
    public String upload(MultipartFile image) {
        String extension = verificationFile(image);
        String filePath;
        try {
            filePath = saveImage(image, extension);
        } catch (IOException e) {
            throw ImageUploadFailException.EXCEPTION;
        }

        return filePath;
    }

    private String saveImage(MultipartFile file, String extension) throws IOException {
        String filePath = UUID.randomUUID() + extension;

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        amazonS3.putObject(new PutObjectRequest(bucketName, filePath, file.getInputStream(), objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return filePath;
    }

    public String verificationFile(MultipartFile file){
        if(file == null || file.isEmpty() || file.getOriginalFilename() == null) throw ImageFormFalseException.EXCEPTION;

        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if(!(extension.contains(".png") || extension.contains(".JPG") || extension.contains(".jpg") || extension.contains(".JPEG") || extension.contains(".jpeg") || extension.contains(".WEBP") || extension.contains(".webp"))) {
            throw ImageFormFalseException.EXCEPTION;
        }

        return extension;
    }
}
