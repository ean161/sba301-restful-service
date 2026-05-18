package vn.edu.fpt.sba.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.sba.entities.Artist;

import java.util.List;

public interface IArtistService {
    List<Artist> findAll();
    Page<Artist> findAll(Pageable pageable);
    Artist findById(Integer id);
    Artist insert(Artist artist);
    Artist update(Integer id, Artist artist);
    void delete(Integer id);
}
