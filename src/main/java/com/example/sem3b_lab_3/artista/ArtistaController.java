package com.example.sem3b_lab_3.artista;


import com.example.sem3b_lab_3.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/artista")
public class ArtistaController {

    @Autowired
    ArtistaRepository artistaRepository;

    @PostMapping
    ResponseEntity<Artista> createArtista(@RequestBody Artista artista) {
        return ResponseEntity.ok(artistaRepository.save(artista));
    }

    @GetMapping("/{id}")
    ResponseEntity<Artista> getArtista(@PathVariable Long id) {
        Optional<Artista> foundArtista = artistaRepository.findById(id);
        if (foundArtista.isPresent()) {
            return ResponseEntity.ok(foundArtista.get());
        }
        throw new ResourceNotFoundException("Artista con id " + id + " no encontrado");
    }

}
