package vn.edu.fpt.sba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.sba.entities.Artist;
import vn.edu.fpt.sba.entities.Genre;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepo extends JpaRepository<Genre, Integer> {
    List<Genre> findAll();

    Optional<Genre> findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);
}
