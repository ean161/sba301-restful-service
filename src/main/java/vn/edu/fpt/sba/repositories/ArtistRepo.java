package vn.edu.fpt.sba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.sba.entities.Artist;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Integer> {
    List<Artist> findAll();

    Optional<Artist> findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);
}
