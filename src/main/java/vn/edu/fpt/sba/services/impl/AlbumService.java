package vn.edu.fpt.sba.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.fpt.sba.dto.responses.AlbumDetailsResponseDTO;
import vn.edu.fpt.sba.dto.responses.AlbumResponseDTO;
import vn.edu.fpt.sba.entities.Album;
import vn.edu.fpt.sba.entities.Artist;
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

    public Page<Album> findAll(Pageable pageable) {
        return albumRepo.findAll(pageable);
    }

    @Override
    public Album findById(Integer id) {
        return albumRepo.findById(id).orElse(null);
    }

    public static List<AlbumResponseDTO> toAlbumDTOList(List<Album> list) {
        return list.stream().map(AlbumService::toAlbumDTO).toList();
    }

    public static Page<AlbumResponseDTO> toAlbumDTOPaging(Page<Album> list) {
        return list.map(AlbumService::toAlbumDTO);
    }

    @Override
    public Album insert(Integer artistId, Album album) {
        Artist artist = artistService.findById(artistId);
        if (artist == null) {
            return null;
        }

        album.setArtist(artist);
        albumRepo.save(album);
        return this.findById(album.getId());
    }

    @Override
    public Album update(Integer id, Album album) {
        return albumRepo.findById(id).map(item -> {
            item.setTitle(album.getTitle());
            return albumRepo.save(item);
        }).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        albumRepo.deleteById(id);
    }

    public static AlbumResponseDTO toAlbumDTO(Album album) {
        return new AlbumResponseDTO(album.getId(), album.getTitle());
    }

    public static AlbumDetailsResponseDTO toAlbumDetailsDTO(Album album) {
        return new AlbumDetailsResponseDTO(album.getId(), album.getTitle(), ArtistService.toArtistDTO(album.getArtist()));
    }
}
