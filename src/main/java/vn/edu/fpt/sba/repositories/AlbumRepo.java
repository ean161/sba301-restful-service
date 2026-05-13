package vn.edu.fpt.sba.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.fpt.sba.entities.Album;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Integer> {

}
