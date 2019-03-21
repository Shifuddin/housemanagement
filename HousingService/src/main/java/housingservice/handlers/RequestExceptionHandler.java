package housingservice.handlers;

import java.util.Date;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import housingservice.controllers.HouseController;
import housingservice.response.CustomError;
/**
 * Global handler to catch all sorts of exception from this microservice
 * @author shifuddin
 * @since 03.08.2019
 * @version 0.0.1
 */
@ControllerAdvice
public class RequestExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Set the default loggger comes with spring boot
	 */
	Logger logger = LoggerFactory.getLogger(HouseController.class);
	/**
	 * Catch HttpRequestMethodNotSupported exception
	 * Returns Response Entity with custom error 
	 */
	@Override
	public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.warn(ex.getMessage());
		return new ResponseEntity<Object>(new CustomError(new Date(), request.getDescription(false), ex.getMessage()),
				status);
	}

	/**
	 * Catch HttpMediaTypeNotSupported exception
	 * Returns Response Entity with custom error 
	 */
	@Override
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.warn(ex.getMessage());
		return new ResponseEntity<Object>(new CustomError(new Date(), request.getDescription(false), ex.getMessage()),
				status);
	}

	/**
	 * Catch HttpMediaTypeNotAcceptable exception
	 * Returns Response Entity with custom error
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.warn(ex.getMessage());
		return new ResponseEntity<>(status);

	}
	/**
	 * Catch MethodArgumentNotValid (hibernate validation) exception
	 * Returns Response Entity with custom error
	 */
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		logger.warn(ex.getMessage());
		return new ResponseEntity<Object>(
				new CustomError(new Date(), "Hibernate Validation Failed", ex.getBindingResult().toString()), status);
	}
	/**
	 * Catch all other hibernate exceptions.
	 * Returns Response entity with custom error.
	 * @param he
	 * @param request
	 * @return
	 */

	@ExceptionHandler(value = { HibernateException.class })
	public ResponseEntity<Object> handleHibernateException(HibernateException he, WebRequest request) {
		logger.warn(he.getMessage());
		return new ResponseEntity<Object>(new CustomError(new Date(), "Hibernate Exception", he.getMessage()),
				HttpStatus.BAD_REQUEST);

	}
}
