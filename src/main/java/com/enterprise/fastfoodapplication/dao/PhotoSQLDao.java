package com.enterprise.fastfoodapplication.dao;

import com.enterprise.fastfoodapplication.dto.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class PhotoSQLDao implements  IPhotoDAO{

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public void save(Photo photo) {
        photoRepository.save(photo);
    }

    @Override
    public void saveImage(MultipartFile imageFile, Photo photo) throws IOException {
        Path currentPath = Paths.get(".");
        Path absolutePath = currentPath.toAbsolutePath();
        photo.setPath(absolutePath + "/src/main/resources/static/photos/");
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(photo.getPath() + imageFile.getOriginalFilename());
        Files.write(path, bytes);
    }
}
