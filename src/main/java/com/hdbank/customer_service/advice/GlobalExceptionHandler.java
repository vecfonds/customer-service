package com.hdbank.customer_service.advice;

import com.hdbank.customer_service.dto.response.BaseResponse;
import com.hdbank.customer_service.shared.enumeration.ResponseEnum;
import com.hdbank.customer_service.shared.exception.ResourceNotFoundException;
import com.hdbank.customer_service.shared.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    private static final String COMMON_ERROR_MESSAGE_TEMPLATE = "Got error: [%s], with Message: [%s]";

//    private String buildErrorMessage(Exception ex) {
//        return String.format(COMMON_ERROR_MESSAGE_TEMPLATE, ex.getClass().getName(), ex.getMessage());
//    }

    private BaseResponse<String> buildCommonResponse(ResponseEnum errorInfo, String errorData) {
        var response = BaseResponse.of();
        response.setResponseCode(errorInfo.getResponseCode());
        response.setMessage(errorInfo.getMessage());
        response.setData(errorData);
        return response;
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<String> handleUnCaughtException(Exception ex) {
//        log.error(buildErrorMessage(ex));
        return buildCommonResponse(ResponseEnum.INTERNAL_ERROR, ex.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<String> handleBadRequestException(BadRequestException ex) {
//        log.error(buildErrorMessage(ex));
        return buildCommonResponse(ex.getErrorInfo(), ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<String> handleNotFoundException(ResourceNotFoundException ex) {
//        log.error(buildErrorMessage(ex));
        return buildCommonResponse(ex.getErrorInfo(), ex.getMessage());
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<String> invalidInputException(MethodArgumentNotValidException ex) {
//        log.error(buildErrorMessage(ex));
        StringBuilder sb = new StringBuilder();
        ex.getFieldErrors().forEach(fieldError -> {
            sb.append(" ** ErrorField=").append(fieldError.getField()).append(", ");
            sb.append("ErrorCode=").append(fieldError.getCode()).append(", ");
            sb.append("ErrorMessage=").append(fieldError.getDefaultMessage()).append(".");
        });
        return buildCommonResponse(ResponseEnum.BAD_REQUEST, sb.toString());
    }
}
