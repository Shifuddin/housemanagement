package housingservice.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import housingservice.response.CustomError;

/**
 * Rest controller to handle incoming error requests.
 * 
 * @author shifuddin
 *
 */
@RestController
public class DefaultController implements ErrorController{
	
	@GetMapping(path="/error")
	public ResponseEntity<?> handleBadRequest(HttpServletRequest request){
		return new ResponseEntity<>(new CustomError(new Date(), "Request not valid", "Please find the API documentation"
				+ "for real endpoints"), HttpStatus.NOT_FOUND);
	}
	@Override
	public String getErrorPath() {
		return "/error";
	}

}
