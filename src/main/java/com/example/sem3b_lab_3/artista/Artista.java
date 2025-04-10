package com.example.sem3b_lab_3.artista;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
}
