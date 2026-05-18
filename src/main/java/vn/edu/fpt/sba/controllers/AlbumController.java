package vn.edu.fpt.sba.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.sba.dto.responses.AlbumDetailsResponseDTO;
import vn.edu.fpt.sba.dto.responses.AlbumResponseDTO;
import vn.edu.fpt.sba.dto.responses.ArtistDetailsResponseDTO;
import vn.edu.fpt.sba.dto.responses.ArtistResponseDTO;
import vn.edu.fpt.sba.entities.Album;
import vn.edu.fpt.sba.entities.Artist;
import vn.edu.fpt.sba.exceptions.AlbumNotFoundException;
import vn.edu.fpt.sba.exceptions.ArtistNotFoundException;
import vn.edu.fpt.sba.services.impl.AlbumService;
import vn.edu.fpt.sba.services.impl.ArtistService;
import vn.edu.fpt.sba.utils.APIResponse;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<APIResponse> getAll() {
        List<Album> raw = albumService.findAll();
        List<AlbumResponseDTO> list = AlbumService.toAlbumDTOList(raw);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), list));
    }

    @GetMapping("/paging")
    public ResponseEntity<APIResponse> getAll(Pageable pageable) {
        Page<Album> raw = albumService.findAll(pageable);
        Page<AlbumResponseDTO> list = AlbumService.toAlbumDTOPaging(raw);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> get(@PathVariable Integer id) {
        Album raw = albumService.findById(id);
        if (raw == null) {
            throw new AlbumNotFoundException();
        }

        AlbumDetailsResponseDTO album = AlbumService.toAlbumDetailsDTO(raw);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), album));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> update(@PathVariable Integer id, @RequestBody Album album) {
        Album raw = albumService.update(id, album);
        if (raw == null) {
            throw new AlbumNotFoundException();
        }

        AlbumDetailsResponseDTO updatedArtist = AlbumService.toAlbumDetailsDTO(raw);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), updatedArtist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id) {
        albumService.delete(id);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value()));
    }
}
