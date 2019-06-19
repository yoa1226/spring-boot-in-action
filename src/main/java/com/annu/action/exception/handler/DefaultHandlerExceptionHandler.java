package com.annu.action.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@ResponseStatus(HttpStatus.OK)
@Slf4j
public class DefaultHandlerExceptionHandler {

    /**
     * 处理注解{@link Valid} 抛出的异常
     * 更多用法 <a>https://reflectoring.io/bean-validation-with-spring-boot/</a>
     *
     * @param ex MethodArgumentNotValidException @Valid 验证request body参数不通过会抛出此异常，
     * @return ResponseVo
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<Violation> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("参数校验异常：", ex);
        return ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError ->
                        Violation.builder()
                                .field(fieldError.getField())
                                .msg(fieldError.getDefaultMessage())
                                .build()
                )
                .collect(Collectors.toList());
    }

    /**
     * 代替spring的默认异常处理，参见{@link DefaultHandlerExceptionResolver}
     * @param ex ex
     * @return string
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MissingServletRequestParameterException.class})
    public String handleException(Exception ex) {
        log.error("系统内部异常", ex);
        return ex.getMessage();
    }
}
