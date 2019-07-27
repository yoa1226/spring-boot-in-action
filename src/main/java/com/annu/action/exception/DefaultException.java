package com.annu.action.exception;

import com.annu.action.vo.BusinessState;
import lombok.Getter;

@Getter
public class DefaultException extends RuntimeException {

    private final Integer code;

    public DefaultException(BusinessState businessState, String message) {
        super(message);
        this.code = businessState.getCode();
    }

    public DefaultException(BusinessState state) {
        super(state.getMsg());
        this.code = state.getCode();
    }
}
