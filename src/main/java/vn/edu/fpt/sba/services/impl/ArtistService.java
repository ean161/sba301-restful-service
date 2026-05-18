package vn.edu.fpt.sba.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.fpt.sba.dto.responses.AlbumDetailsResponseDTO;
import vn.edu.fpt.sba.dto.responses.AlbumResponseDTO;
import vn.edu.fpt.sba.dto.responses.ArtistDetailsResponseDTO;
import vn.edu.fpt.sba.dto.responses.ArtistResponseDTO;
import vn.edu.fpt.sba.entities.Album;
import vn.edu.fpt.sba.entities.Artist;
import vn.edu.fpt.sba.exceptions.ArtistNotFoundException;
import vn.edu.fpt.sba.repositories.ArtistRepo;
import vn.edu.fpt.sba.services.IArtistService;

import java.util.List;

@Service
public class ArtistService implements IArtistService {
    private final ArtistRepo artistRepo;

    public ArtistService(ArtistRepo artistRepo) {
        this.artistRepo = artistRepo;
    }

    @Override
    public List<Artist> findAll() {
        return artistRepo.findAll();
    }

    @Override
    public Page<Artist> findAll(Pageable pageable) {
        return artistRepo.findAll(pageable);
    }

    @Override
    public Artist findById(Integer id) {
        return artistRepo.findById(id).orElse(null);
    }

    @Override
    public Artist insert(Artist artist) {
        artistRepo.save(artist);
        return this.findById(artist.getId());
    }

    @Override
    public Artist update(Integer id, Artist artist) {
        return artistRepo.findById(id).map(item -> {
            item.setName(artist.getName());
            return artistRepo.save(item);
        }).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        artistRepo.deleteById(id);
    }

    public static List<ArtistResponseDTO> toArtistDTOList(List<Artist> list) {
        return list.stream().map(ArtistService::toArtistDTO).toList();
    }

    public static Page<ArtistResponseDTO> toArtistDTOPaging(Page<Artist> list) {
        return list.map(ArtistService::toArtistDTO);
    }

    public static ArtistResponseDTO toArtistDTO(Artist artist) {
        return new ArtistResponseDTO(artist.getId(), artist.getName());
    }

    public static ArtistDetailsResponseDTO toArtistDetailsDTO(Artist artist) {
        return new ArtistDetailsResponseDTO(artist.getId(), artist.getName(), AlbumService.toAlbumDTOList(artist.getAlbums()));
    }
}
