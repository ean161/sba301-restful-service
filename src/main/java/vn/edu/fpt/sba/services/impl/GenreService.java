package vn.edu.fpt.sba.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.fpt.sba.dto.responses.GenreResponseDTO;
import vn.edu.fpt.sba.entities.Genre;
import vn.edu.fpt.sba.repositories.GenreRepo;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepo genreRepo;

    public GenreService(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    public List<Genre> findAll() {
        return genreRepo.findAll();
    }

    public Page<Genre> findAll(Pageable pageable) {
        return genreRepo.findAll(pageable);
    }

    public Genre findById(Integer id) {
        return genreRepo.findById(id).orElse(null);
    }

    public Genre insert(Genre genre) {
        return genreRepo.save(genre);
    }

    public Genre update(Integer id, Genre genre) {
        Genre target = this.findById(id);
        if (target == null) {
            return null;
        }

        target.setName(genre.getName());
        return genreRepo.save(target);
    }

    public void delete(Integer id) {
        genreRepo.deleteById(id);
    }

    public static GenreResponseDTO toGenreDTO(Genre genre) {
        return new GenreResponseDTO(genre.getId(), genre.getName());
    }

    public static List<GenreResponseDTO> toGenreDTOList(List<Genre> list) {
        return list.stream().map(GenreService::toGenreDTO).toList();
    }

    public static Page<GenreResponseDTO> toGenreDTOPage(Page<Genre> list) {
        return list.map(GenreService::toGenreDTO);
    }
}
