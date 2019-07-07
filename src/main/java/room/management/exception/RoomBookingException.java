package room.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RoomBookingException extends Exception {

	HttpStatus status;
	String message;
	public RoomBookingException(HttpStatus status, String message) {
		super(message);
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
}
