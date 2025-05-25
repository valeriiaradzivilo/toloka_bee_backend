package com.diplom.toloka_bee_backend.service;

import com.diplom.toloka_bee_backend.model.PhotoModel;
import com.diplom.toloka_bee_backend.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public PhotoModel savePhoto(MultipartFile file) throws Exception {
        PhotoModel photo = new PhotoModel();
        photo.setFilename(file.getOriginalFilename());
        photo.setContentType(file.getContentType());
        photo.setData(file.getBytes());

        return photoRepository.save(photo);
    }

    public byte[] getPhotoBytes(String id) {
        return photoRepository.findById(id)
                .map(PhotoModel::getData)
                .orElse(null);
    }
}
