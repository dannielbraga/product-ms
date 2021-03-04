package com.compasso.productms.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler controllerExceptionHandler;

    @BeforeEach
    void before(){
        this.controllerExceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    void handleMethodNotValidExceptionTest(){
        // Arrange
        MethodParameter methodParameter = Mockito.mock(MethodParameter.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        MethodArgumentNotValidException methodArgumentNotValidException = new MethodArgumentNotValidException(methodParameter, bindingResult);

        // Action
        ResponseEntity<ResponseError> response = this.controllerExceptionHandler
                .handleMethodNotValidException(methodArgumentNotValidException);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void handleProductNotFoundTest(){
        // Arrange
        ResponseStatusException responseStatusException = Mockito.mock(ResponseStatusException.class);

        // Action
        ResponseEntity<ResponseError> response = this.controllerExceptionHandler
                .handleProductNotFound(responseStatusException);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


}
