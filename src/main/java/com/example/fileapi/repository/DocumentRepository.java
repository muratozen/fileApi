package com.example.fileapi.repository;

import com.example.fileapi.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document,Integer> {

    @Query(value = "SELECT * FROM files WHERE name = ?1", nativeQuery = true)
    Document checkFile(String name) ;

    @Query(value = "SELECT uuid, name, size , path , extension , create_date, update_date from files", nativeQuery = true)
    List<Document> getAllWithoutId();

    @Query(value = "SELECT * FROM files WHERE uuid = ?1", nativeQuery = true)
    Document findByUUID(String uuid) ;
}
