package com.example.sem3b_lab_3.artista;


import com.example.sem3b_lab_3.exceptions.ResourceConflictException;
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
        Optional<Artista> foundArtista = artistaRepository.getByUsername(artista.getUsername());

        if (foundArtista.isEmpty()) {
            return ResponseEntity.ok(artistaRepository.save(artista));
        }

        throw new ResourceConflictException("Artista con el nombre " + artista.getUsername() + " ya existe");
    }

    @PutMapping("/{id}")
    ResponseEntity<Artista> updateArtista(@PathVariable Long id, @RequestBody Artista artista) {
        Optional<Artista> foundArtistaById = artistaRepository.findById(id);

        if (foundArtistaById.isPresent()) {
            foundArtistaById.get().setUsername(artista.getUsername());
            return ResponseEntity.ok(artistaRepository.save(foundArtistaById.get()));
        }

        throw new ResourceNotFoundException("Artista con id " + id + " no encontrado");
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
