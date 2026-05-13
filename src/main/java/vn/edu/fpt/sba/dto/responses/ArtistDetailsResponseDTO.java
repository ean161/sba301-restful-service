package vn.edu.fpt.sba.dto.responses;

import java.util.List;

public record ArtistDetailsResponseDTO(
        Integer id,
        String name,
        List<AlbumResponseDTO> albums
) {
}
