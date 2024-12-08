package mate.academy.rickandmorty.exception;

public class HttpException extends RuntimeException {
    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }
}
