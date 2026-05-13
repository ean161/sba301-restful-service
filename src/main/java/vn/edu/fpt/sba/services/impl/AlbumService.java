package vn.edu.fpt.sba.services.impl;

import org.springframework.stereotype.Service;
import vn.edu.fpt.sba.entities.Album;
import vn.edu.fpt.sba.repositories.AlbumRepo;
import vn.edu.fpt.sba.services.IAlbumService;

import java.util.List;

@Service
public class AlbumService implements IAlbumService {

    private final AlbumRepo albumRepo;
    private final ArtistService artistService;

    public AlbumService(AlbumRepo albumRepo, ArtistService artistService) {
        this.albumRepo = albumRepo;
        this.artistService = artistService;
    }

    public List<Album> findAll() {
        return albumRepo.findAll();
    }

    @Override
    public Album findById(Integer id) {
        return albumRepo.findById(id).orElseThrow();
    }

    @Override
    public Album insert(Integer artistId, Album album) {
        album.setArtist(artistService.findById(artistId));
        return albumRepo.save(album);
    }
}
