package com.example.fileapi.service;

import com.example.fileapi.entity.Document;
import com.example.fileapi.repository.DocumentRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
public class DocumentService {

    final DocumentRepository documentRepository;

    private final static Path rootLocation = Path.of("./src/main/resources/tmp/");

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> getAll(){
        return documentRepository.findAll();
    }

    public Document save(MultipartFile file) throws IOException {

        Files.copy(
                file.getInputStream(),
                rootLocation.resolve(Objects.requireNonNull(file.getOriginalFilename())),
                StandardCopyOption.REPLACE_EXISTING
        );

        Document doc = new Document();
        doc.setPath(rootLocation +  file.getOriginalFilename());
        doc.setName(FilenameUtils.getBaseName(file.getOriginalFilename()));
        doc.setSize(file.getSize());
        doc.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
        documentRepository.save(doc);
        return doc;
    }
}
