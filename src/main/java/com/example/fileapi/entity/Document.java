package com.example.fileapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="files")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int Id;

    private String path;

    private Long size;

    private String UUID = java.util.UUID.randomUUID().toString();

    @Column(length = 500)
    private String name;

    private String extension;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    private Date updateDate;

}
