package vn.edu.fpt.sba.exceptions;

public class ArtistNotFoundException extends RuntimeException {
    public ArtistNotFoundException() {
        super("Artist not found");
    }
}
