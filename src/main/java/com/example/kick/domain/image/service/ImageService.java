package com.example.kick.domain.image.service;

import com.example.kick.domain.image.presentation.dto.ImageUrlResponse;
import com.example.kick.infra.s3.S3Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final S3Facade s3Facade;

    public ImageUrlResponse execute(List<MultipartFile> images) {
        List<String> imageUrl = images.stream()
            .map(s3Facade::uploadImage)
            .collect(Collectors.toList());

        return new ImageUrlResponse(imageUrl);
    }
}
