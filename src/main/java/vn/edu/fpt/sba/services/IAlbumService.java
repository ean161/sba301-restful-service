package vn.edu.fpt.sba.services;

import vn.edu.fpt.sba.entities.Album;
import vn.edu.fpt.sba.entities.Artist;

public interface IAlbumService {
    Album insert(Integer artistId, Album album);
}
