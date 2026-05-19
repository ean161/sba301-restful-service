package vn.edu.fpt.sba.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.edu.fpt.sba.entities.Genre;

import java.util.List;

public interface IGenreService {
    List<Genre> findAll();
    Page<Genre> findAll(Pageable pageable);
    Genre findById(Integer id);
    Genre insert(Integer genreId, Genre genre);
    Genre update(Integer id, Genre genre);
    void delete(Integer id);
}
