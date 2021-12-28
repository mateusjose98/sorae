package pkg.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    private final String message;
    private final HttpStatus httpStatus;
    private final List<String> messages;

    public CustomException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.messages = null;
    }

    public CustomException(List<String> messages, HttpStatus httpStatus) {
        this.message = null;
        this.httpStatus = httpStatus;
        this.messages = messages;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

	public List<String> getMessages() {
		return messages;
	}
}
