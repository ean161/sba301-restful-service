package vn.edu.fpt.sba.services.impl;

import org.springframework.stereotype.Service;
import vn.edu.fpt.sba.entities.Album;
import vn.edu.fpt.sba.repositories.AlbumRepo;
import vn.edu.fpt.sba.services.IAlbumService;

@Service
public class AlbumService implements IAlbumService {

    private final AlbumRepo albumRepo;
    private final ArtistService artistService;

    public AlbumService(AlbumRepo albumRepo, ArtistService artistService) {
        this.albumRepo = albumRepo;
        this.artistService = artistService;
    }

    @Override
    public Album insert(Integer artistId, Album album) {
        album.setArtist(artistService.findById(artistId));
        return albumRepo.save(album);
    }
}
