package com.annu.action.vo;

import lombok.Builder;
import lombok.Data;

import static com.annu.action.vo.BusinessReqState.SUCCESS;

@Data
@Builder
public class ResponseVo<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <T> ResponseVo<T> success(T data) {
        return ResponseVo.<T>builder()
                .code(SUCCESS.getCode())
                .msg(SUCCESS.getMsg())
                .data(data)
                .build();
    }

    public static <T> ResponseVo<T> build(BusinessReqState state, T data) {
        return ResponseVo.<T>builder()
                .code(state.getCode())
                .msg(state.getMsg())
                .data(data)
                .build();

    }


}
