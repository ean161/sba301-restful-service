package vn.edu.fpt.sba.services.impl;

import org.springframework.stereotype.Service;
import vn.edu.fpt.sba.entities.Artist;
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
    public Artist findById(Integer id) {
        return artistRepo.findById(id).orElseThrow();
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
        }).orElseThrow(() -> new IllegalArgumentException("Artist not found"));
    }

    @Override
    public void delete(Integer id) {
        if (!artistRepo.existsById(id)) {
            throw new IllegalArgumentException("Artist not found");
        }

        artistRepo.deleteById(id);
    }
}
