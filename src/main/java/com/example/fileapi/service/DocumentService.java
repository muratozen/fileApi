package com.example.fileapi.service;

import com.example.fileapi.entity.Document;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface DocumentService {

    public List<Document> getAll();

    public Document save(MultipartFile file) throws IOException;

    public boolean update( String uuid, MultipartFile file) throws IOException;

    public boolean delete(String uuid) throws IOException;

    public boolean checkFileExtension(MultipartFile file);
    public byte[] getFiletoByte(String uuid) throws IOException;
}
