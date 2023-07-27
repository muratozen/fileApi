package com.example.fileapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column(length=500)
    private String path;

    private BigInteger size;

    @Column(length = 30,unique = true)
    private String name;

    private String extension;


}
