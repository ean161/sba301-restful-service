package vn.edu.fpt.sba.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.sba.entities.Album;
import vn.edu.fpt.sba.entities.Artist;

import java.util.List;

public interface IAlbumService {
    List<Album> findAll();
    Page<Album> findAll(Pageable pageable);
    Album findById(Integer id);
    Album insert(Integer artistId, Album album);
    Album update(Integer id, Album album);
    void delete(Integer id);
}
