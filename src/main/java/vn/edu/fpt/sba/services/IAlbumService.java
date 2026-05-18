package vn.edu.fpt.sba.services;

import vn.edu.fpt.sba.entities.Album;
import vn.edu.fpt.sba.entities.Artist;

import java.util.List;

public interface IAlbumService {
    List<Album> findAll();
    Album findById(Integer id);
    Album insert(Integer artistId, Album album);
    Album update(Integer id, Album album);
    void delete(Integer id);
}
