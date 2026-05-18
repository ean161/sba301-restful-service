package vn.edu.fpt.sba.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.sba.dto.responses.AlbumDetailsResponseDTO;
import vn.edu.fpt.sba.dto.responses.ArtistDetailsResponseDTO;
import vn.edu.fpt.sba.dto.responses.ArtistResponseDTO;
import vn.edu.fpt.sba.entities.Album;
import vn.edu.fpt.sba.entities.Artist;
import vn.edu.fpt.sba.exceptions.ArtistNotFoundException;
import vn.edu.fpt.sba.services.impl.AlbumService;
import vn.edu.fpt.sba.services.impl.ArtistService;
import vn.edu.fpt.sba.utils.APIResponse;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final AlbumService albumService;

    public ArtistController(ArtistService artistService, AlbumService albumService) {
        this.artistService = artistService;
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<APIResponse> getAll() {
        List<Artist> raw = artistService.findAll();
        List<ArtistResponseDTO> list = ArtistService.toArtistDTOList(raw);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> get(@PathVariable Integer id) {
        Artist raw = artistService.findById(id);
        if (raw == null) {
            throw new ArtistNotFoundException();
        }

        ArtistDetailsResponseDTO artist = ArtistService.toArtistDetailsDTO(raw);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), artist));
    }

    @PostMapping
    public ResponseEntity<APIResponse> insert(@RequestBody Artist artist) {
        Artist raw = artistService.insert(artist);
        ArtistDetailsResponseDTO insertedArtist = ArtistService.toArtistDetailsDTO(raw);
        return ResponseEntity.ok(new APIResponse(HttpStatus.CREATED.value(), insertedArtist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> update(@PathVariable Integer id, @RequestBody Artist artist) {
        Artist raw = artistService.update(id, artist);
        if (raw == null) {
            throw new ArtistNotFoundException();
        }

        ArtistDetailsResponseDTO updatedArtist = ArtistService.toArtistDetailsDTO(raw);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), updatedArtist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id) {
        artistService.delete(id);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value()));
    }

    @PostMapping("/{id}/albums")
    public ResponseEntity<APIResponse> insertAlbum(@PathVariable Integer id, @RequestBody Album album) {
        Album raw = albumService.insert(id, album);
        if (raw == null) {
            throw new ArtistNotFoundException();
        }

        AlbumDetailsResponseDTO insertedAlbum = AlbumService.toAlbumDetailsDTO(raw);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), insertedAlbum));
    }
}
