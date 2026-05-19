package vn.edu.fpt.sba.controllers.v1;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.fpt.sba.dto.responses.GenreResponseDTO;
import vn.edu.fpt.sba.entities.Genre;
import vn.edu.fpt.sba.exceptions.GenreNotFoundException;
import vn.edu.fpt.sba.services.impl.GenreService;
import vn.edu.fpt.sba.utils.APIResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
public class GenreControllerV1 {

    private final GenreService genreService;

    public GenreControllerV1(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public ResponseEntity<APIResponse> getAll() {
        List<Genre> raw = genreService.findAll();
        List<GenreResponseDTO> list = GenreService.toGenreDTOList(raw);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), list));
    }

    @GetMapping("/paging")
    public ResponseEntity<APIResponse> getAll(Pageable pageable) {
        Page<Genre> raw = genreService.findAll(pageable);
        Page<GenreResponseDTO> list = GenreService.toGenreDTOPage(raw);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> get(@PathVariable Integer id) {
        Genre raw = genreService.findById(id);
        if (raw == null) {
            throw new GenreNotFoundException();
        }

        GenreResponseDTO genre = GenreService.toGenreDTO(raw);
        return ResponseEntity.ok(new APIResponse(HttpStatus.OK.value(), genre));
    }

    @PostMapping
    public ResponseEntity<APIResponse> insert(@RequestBody Genre genre) {
        Genre raw = genreService.insert(genre);
        GenreResponseDTO insertedGenre = GenreService.toGenreDTO(raw);
        return ResponseEntity.status(HttpStatus.CREATED).body(new APIResponse(HttpStatus.CREATED.value(), insertedGenre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> update(@PathVariable Integer id, @RequestBody Genre genre) {
        Genre raw = genreService.update(id, genre);
        GenreResponseDTO updatedGenre = GenreService.toGenreDTO(raw);
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK.value(), updatedGenre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id) {
        genreService.delete(id);
        return ResponseEntity.ok().body(new APIResponse(HttpStatus.OK.value()));
    }
}
