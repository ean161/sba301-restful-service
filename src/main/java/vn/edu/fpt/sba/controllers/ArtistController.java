package vn.edu.fpt.sba.controllers;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.sba.entities.Artist;
import vn.edu.fpt.sba.services.ArtistService;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<Artist>> getList() {
        return ResponseEntity.status(HttpStatus.OK).body(artistService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(artistService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<Artist> insert(@RequestBody Artist artist) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistService.insert(artist));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Artist artist) {
        try {
            artistService.update(artist);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            artistService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
