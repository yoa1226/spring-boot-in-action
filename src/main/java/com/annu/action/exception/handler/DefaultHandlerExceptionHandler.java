package com.annu.action.exception.handler;

import com.annu.action.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.annotation.Validated;
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

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.annu.action.vo.BusinessReqState.INTERNAL_SERVICE_ERROR;
import static com.annu.action.vo.BusinessReqState.REQUEST_PARAM_FAILURE;

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
    public ResponseVo<List<Violation>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("request body 参数校验异常：", ex);
        var violations = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError ->
                        Violation.builder()
                                .field(fieldError.getField())
                                .msg(fieldError.getDefaultMessage())
                                .build()
                )
                .collect(Collectors.toList());
        return ResponseVo.build(REQUEST_PARAM_FAILURE, violations);
    }

    /**
     * 处理注解{@link Validated} 抛出的异常
     *
     * @param ex ConstraintViolationException @Validated 验证 request query param不同过会抛出此异常
     * @return ResponseVo
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseVo<List<Violation>> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("request query param 参数校验异常：", ex);
        var violations = ex.getConstraintViolations()
                .stream()
                .map(violation-> ((ConstraintViolationImpl) violation))
                .map(violation -> Violation.builder()
                        .field(((PathImpl) violation.getPropertyPath()).getLeafNode().getName())
                        .msg(violation.getMessage())
                        .build())
                .collect(Collectors.toList());
        return ResponseVo.build(REQUEST_PARAM_FAILURE, violations);
    }

    /**
     * 代替spring的默认异常处理，参见{@link DefaultHandlerExceptionResolver}
     * todo  针对不同的异常返回不同的code类型
     * @see org.springframework.http.HttpStatus
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
    public ResponseVo<String> handleException(Exception ex) {
        log.error("系统内部异常", ex);
        return ResponseVo.build(INTERNAL_SERVICE_ERROR,ex.getMessage());
    }
}
