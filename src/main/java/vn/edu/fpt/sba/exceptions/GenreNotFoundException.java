package vn.edu.fpt.sba.exceptions;

public class GenreNotFoundException extends RuntimeException {
    public GenreNotFoundException() {
        super("Genre not found");
    }
}
