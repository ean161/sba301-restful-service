package vn.edu.fpt.sba.services;

import vn.edu.fpt.sba.entities.Artist;

import java.util.List;

public interface IArtistService {
    List<Artist> findAll();
    Artist findById(Integer id);
    Artist insert(Artist artist);
    Artist update(Integer id, Artist artist);
    void delete(Integer id);
}
