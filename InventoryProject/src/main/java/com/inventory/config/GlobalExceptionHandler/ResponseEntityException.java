package com.inventory.config.GlobalExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.MappingException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class ResponseEntityException {

	@Autowired
	private MessageSource messageSource;

	@Value("${spring.hepl.link}")
	private String helpLink;

	@Value("${spring.global.exception.code}")
	private String globalExceptionCode;

	private String getMessage(String key) {
		return messageSource.getMessage(key, null, "Default message", LocaleContextHolder.getLocale());
	}

	// Default exception
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception exception) {

		String message = getMessage("Exception.message");
		String detailMessage = exception.getLocalizedMessage();
		int code = 1;
		String moreInformation = "http://localhost:8080/api/v1/exception/1";
		log.error(message, detailMessage, exception);
		ApiErrorResponse response = new ApiErrorResponse(message, detailMessage, null, code, moreInformation);

		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Not found url handler
	// @Override
	@ExceptionHandler(NoHandlerFoundException.class)
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException exception,
			HttpServletRequest request) {
		// exceptionService.errorLog(e, request);
		String message = getMessage("NoHandlerFoundException.message") + exception.getHttpMethod() + " "
				+ exception.getRequestURL();
		String detailMessage = exception.getLocalizedMessage();
		int code = 2;
		String moreInformation = "http://localhost:8080/api/v1/exception/2";
		log.error(message, detailMessage, exception);
		ApiErrorResponse response = new ApiErrorResponse(message, detailMessage, null, code, moreInformation);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	// Not support HTTP Method
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException exception, HttpServletRequest request) {
		// exceptionService.errorLog(e, request);
		String message = getMessageFromHttpRequestMethodNotSupportedException(exception);
		String detailMessage = exception.getLocalizedMessage();
		int code = 3;
		String moreInformation = "http://localhost:8080/api/v1/exception/3";
		log.error(message, detailMessage, exception);
		ApiErrorResponse response = new ApiErrorResponse(message, detailMessage, null, code, moreInformation);
		return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
	}

	private String getMessageFromHttpRequestMethodNotSupportedException(
			HttpRequestMethodNotSupportedException exception) {
		String message = exception.getMethod() + " " + getMessage("HttpRequestMethodNotSupportedException.message");
		for (HttpMethod method : exception.getSupportedHttpMethods()) {
			message += method + " ";
		}
		return message;
	}

	// Not support media type
	@ExceptionHandler({ HttpMediaTypeNotSupportedException.class })
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exception,
			HttpServletRequest request) {

		String message = getMessageFromHttpMediaTypeNotSupportedException(exception);
		String detailMessage = exception.getLocalizedMessage();
		int code = 4;
		String moreInformation = "http://localhost:8080/api/v1/exception/4";
		log.error(message, detailMessage, exception);
		ApiErrorResponse response = new ApiErrorResponse(message, detailMessage, null, code, moreInformation);

		return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	private String getMessageFromHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
		String message = exception.getContentType() + " " + getMessage("HttpMediaTypeNotSupportedException.message");
		for (MediaType method : exception.getSupportedMediaTypes()) {
			message += method + ", ";
		}
		return message.substring(0, message.length() - 2);
	}

	// BindException: This exception is thrown when fatal binding errors occur.
	// MethodArgumentNotValidException: This exception is thrown when argument
	// annotated with @Valid failed validation:
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpServletRequest request) {

		String message = getMessage("MethodArgumentNotValidException.message");
		String detailMessage = exception.getLocalizedMessage();
		// error
		Map<String, String> errors = new HashMap<>();
		for (ObjectError error : exception.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		}
		int code = 5;
		String moreInformation = "http://localhost:8080/api/v1/exception/5";
		log.error(message, detailMessage, exception);
		ApiErrorResponse response = new ApiErrorResponse(message, detailMessage, errors, code, moreInformation);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// bean validation error
	// bạn có thể định nghĩa các ràng buộc (constraints) trên các trường của đối
	// tượng thực thể bằng các chú thích (@NotNull, @Size, @Email, v.v.) hoặc thông
	// qua XML.
	// Trường không được để trống (@NotNull, @NotEmpty, @NotBlank).
	// Độ dài trường vượt quá giới hạn cho phép (@Size).
	// Trường không hợp lệ đối với một kiểu dữ liệu cụ thể (@Email, @Pattern).
	// Giá trị trường không nằm trong phạm vi cho phép (@Min, @Max). V.v.
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {

		String message = getMessage("MethodArgumentNotValidException.message");
		String detailMessage = exception.getLocalizedMessage();
		// error
		Map<String, String> errors = new HashMap<>();
		for (ConstraintViolation violation : exception.getConstraintViolations()) {
			String fieldName = violation.getPropertyPath().toString();
			String errorMessage = violation.getMessage();
			errors.put(fieldName, errorMessage);
		}
		int code = 5;
		String moreInformation = "http://localhost:8080/api/v1/exception/5";
		log.error(message, detailMessage, exception);
		ApiErrorResponse response = new ApiErrorResponse(message, detailMessage, errors, code, moreInformation);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// MissingServletRequestPartException: This exception is thrown when when the
	// part of a multipart request not found
	// MissingServletRequestParameterException: This exception is thrown when
	// request missing parameter:
	// Missing parameter on request(url) when send
	@ExceptionHandler({ MissingServletRequestParameterException.class })
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			MissingServletRequestParameterException exception, HttpServletRequest request) {

		String message = exception.getParameterName() + " "
				+ getMessage("MissingServletRequestParameterException.message");
		String detailMessage = exception.getLocalizedMessage();
		int code = 6;
		String moreInformation = "http://localhost:8080/api/v1/exception/6";
		log.error(message, detailMessage, exception);
		ApiErrorResponse response = new ApiErrorResponse(message, detailMessage, null, code, moreInformation);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// TypeMismatchException: This exception is thrown when try to set bean property
	// with wrong type.
	// MethodArgumentTypeMismatchException: This exception is thrown when method
	// argument is not the expected type:
	// wrong type parameter
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exception) {

		String message = exception.getName() + " " + getMessage("MethodArgumentTypeMismatchException.message")
				+ exception.getRequiredType().getName();
		String detailMessage = exception.getLocalizedMessage();
		int code = 7;
		String moreInformation = "http://localhost:8080/api/v1/exception/7";
		log.error(message, detailMessage, exception);
		ApiErrorResponse response = new ApiErrorResponse(message, detailMessage, null, code, moreInformation);

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {

		String message = "DataIntegrityViolationException Message";
		String detailMessage = exception.getLocalizedMessage();
		int code = 8;
		String moreInformation = "http://localhost:8080/api/v1/exception/8";
		log.error(message, detailMessage, exception);
		ApiErrorResponse response = new ApiErrorResponse(message, detailMessage, null, code, moreInformation);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ MappingException.class })
	public ResponseEntity<Object> handleMappingException(MappingException exception) {

		String message = getMessageFromMappingException(exception);
		String detailMessage = exception.getLocalizedMessage();
		int code = 9;
		String moreInformation = "http://localhost:8080/api/v1/exception/9";
		log.error(message, detailMessage, exception);
		ApiErrorResponse response = new ApiErrorResponse(message, detailMessage, null, code, moreInformation);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	private String getMessageFromMappingException(MappingException exception) {
		String message = "ModelMapper mapping errors: ";
		for (ErrorMessage method : exception.getErrorMessages()) {
			message += method + ", ";
		}
		return message.substring(0, message.length() - 2);
	}

}
