package vn.edu.fpt.sba.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.fpt.sba.dto.responses.AlbumDetailsResponseDTO;
import vn.edu.fpt.sba.dto.responses.AlbumResponseDTO;
import vn.edu.fpt.sba.dto.responses.ArtistResponseDTO;
import vn.edu.fpt.sba.entities.Album;
import vn.edu.fpt.sba.services.IAlbumService;
import vn.edu.fpt.sba.services.IArtistService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final IAlbumService albumService;
    private final IArtistService artistService;

    public AlbumController(IAlbumService albumService, IArtistService artistService) {
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @GetMapping
    public List<AlbumResponseDTO> getAll() {
        List<Album> albums = albumService.findAll();
        List<AlbumResponseDTO> list = albums.stream().map(album -> {
            return new AlbumResponseDTO(album.getId(), album.getTitle());
        }).toList();
        return list;
    }

    @GetMapping("/{id}")
    public AlbumDetailsResponseDTO getById(@PathVariable Integer id) {
        Album album = albumService.findById(id);
        return new AlbumDetailsResponseDTO(
                album.getId(),
                album.getTitle(),
                new ArtistResponseDTO(
                        album.getArtist().getId(),
                        album.getArtist().getName()
                )
        );
    }
}
