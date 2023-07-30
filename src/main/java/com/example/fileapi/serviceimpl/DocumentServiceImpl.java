package com.example.fileapi.serviceimpl;

import com.example.fileapi.entity.Document;
import com.example.fileapi.repository.DocumentRepository;
import com.example.fileapi.service.DocumentService;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    private final static Path rootLocation = Path.of("./src/main/resources/tmp/");

    public DocumentServiceImpl(DocumentRepository documentRepository) {

        this.documentRepository = documentRepository;
    }

    public List<Document> getAll(){
        return documentRepository.findAll();
    }

    public Document save(MultipartFile file) throws IOException {

        Document document = documentRepository.checkFile(FilenameUtils.getBaseName(file.getOriginalFilename()));
        if (document == null){
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
        }else{
            Files.copy(
                    file.getInputStream(),
                    rootLocation.resolve(Objects.requireNonNull(file.getOriginalFilename())),
                    StandardCopyOption.REPLACE_EXISTING
            );
            document.setPath(rootLocation + file.getOriginalFilename());
            document.setName(FilenameUtils.getBaseName(file.getOriginalFilename()));
            document.setSize(file.getSize());
            document.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
            document.setUpdateDate(new Date());
            documentRepository.save(document);
            return document;
        }
    }

    public boolean update(String uuid, MultipartFile file) throws IOException {
        Document tempD = documentRepository.findByUUID(uuid);
        if (tempD != null){
            Files.copy(
                    file.getInputStream(),
                    rootLocation.resolve(Objects.requireNonNull(file.getOriginalFilename())),
                    StandardCopyOption.REPLACE_EXISTING
            );
            Files.delete( Path.of(rootLocation + "/" +  tempD.getName() + "." +  tempD.getExtension()));
            tempD.setPath(rootLocation + file.getOriginalFilename());
            tempD.setName(FilenameUtils.getBaseName(file.getOriginalFilename()));
            tempD.setSize(file.getSize());
            tempD.setExtension(FilenameUtils.getExtension(file.getOriginalFilename()));
            tempD.setUpdateDate(new Date());
            documentRepository.save(tempD);
            return true;
        }else{
            return false;
        }
    }

    public boolean delete(String uuid) throws IOException {
        Document tempD = documentRepository.findByUUID(uuid);
        if (tempD != null){
            Files.delete( Path.of(rootLocation + "/" +  tempD.getName() + "." +  tempD.getExtension()));
            documentRepository.deleteById(tempD.getId());
            return true;
        }else{
            return false;
        }
    }

    public boolean checkFileExtension(MultipartFile file){
        List<String> allowedFileExt = Arrays.asList("png", "jpeg", "jpg", "docx", "pdf", "xlsx");
        return allowedFileExt.contains(FilenameUtils.getExtension(file.getOriginalFilename()));
    }

    public byte[] getFiletoByte(String uuid) throws IOException {
        Document tempD = documentRepository.findByUUID(uuid);
        if (tempD != null){
            return Files.readAllBytes(Path.of(rootLocation + "/" +  tempD.getName() + "." +  tempD.getExtension()));
        }else{
            return null;
        }


    }


}
