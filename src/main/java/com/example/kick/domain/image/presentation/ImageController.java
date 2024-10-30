package com.example.kick.domain.image.presentation;

import com.example.kick.domain.image.presentation.dto.ImageUrlResponse;
import com.example.kick.domain.image.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/images")
@RestController
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "이미지 업로드")
    @PostMapping
    public ImageUrlResponse saveImage(@RequestPart List<MultipartFile> images) {
        return imageService.execute(images);
    }
}
