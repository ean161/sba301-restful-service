package vn.edu.fpt.sba.services;

import org.springframework.stereotype.Service;
import vn.edu.fpt.sba.entities.Artist;
import vn.edu.fpt.sba.repositories.ArtistRepo;

import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepo artistRepo;

    public ArtistService(ArtistRepo artistRepo) {
        this.artistRepo = artistRepo;
    }

    public List<Artist> findAll() {
        return artistRepo.findAll();
    }

    public Artist findById(Integer id) {
        return artistRepo.findById(id).orElseThrow();
    }

    public Artist insert(Artist artist) {
        artistRepo.save(artist);
        return this.findById(artist.getId());
    }

    public void update(Artist artist) {
        artistRepo.findById(artist.getId()).map(item -> {
            item.setName(artist.getName());
            return artistRepo.save(item);
        }).orElseThrow();
    }

    public void delete(Integer id) {
        if (!artistRepo.existsById(id)) {
            throw new IllegalArgumentException("Artist not found");
        }

        artistRepo.deleteById(id);
    }
}
