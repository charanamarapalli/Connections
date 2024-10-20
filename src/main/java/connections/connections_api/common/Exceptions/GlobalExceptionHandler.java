package connections.connections_api.common.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import connections.connections_api.Entity.CustomResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomResponse> handleUserNotFoundException(UserNotFoundException ex) {
        CustomResponse response = new CustomResponse(ex.getMessage(), 404);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(IncorrectPasswordException.class)
	public ResponseEntity<CustomResponse> handleIncorrectPasswordException(IncorrectPasswordException ex){
		CustomResponse response = new CustomResponse(ex.getMessage(), 401);
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}
	
}
