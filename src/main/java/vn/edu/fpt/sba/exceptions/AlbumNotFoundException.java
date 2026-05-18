package vn.edu.fpt.sba.exceptions;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException() {
        super("Album not found");
    }
}
