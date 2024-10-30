package com.example.kick.infra.s3;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUtil {
    String uploadImage(MultipartFile image);
}
