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
        return ResponseEntity.ok().body(artistService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(artistService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Artist> insert(@RequestBody Artist artist) {
        return ResponseEntity.ok().body(artistService.insert(artist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artist> update(@PathVariable Integer id, @RequestBody Artist artist) {
        try {
            return ResponseEntity.ok().body(artistService.update(id, artist));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            artistService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
