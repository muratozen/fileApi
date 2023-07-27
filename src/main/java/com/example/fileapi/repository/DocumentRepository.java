package com.example.fileapi.repository;

import com.example.fileapi.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File,Integer> {

}
