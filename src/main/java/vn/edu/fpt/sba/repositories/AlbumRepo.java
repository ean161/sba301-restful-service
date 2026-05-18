package vn.edu.fpt.sba.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.sba.entities.Album;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Integer> {
    List<Album> findAll();

    Page<Album> findAll(Pageable pageable);

    Optional<Album> findById(Integer integer);
}
