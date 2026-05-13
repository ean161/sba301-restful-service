package vn.edu.fpt.sba.dto.responses;

public record AlbumDetailsResponseDTO(
        Integer id,
        String title,
        ArtistResponseDTO artist
) {}
