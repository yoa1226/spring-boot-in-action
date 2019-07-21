package com.annu.action.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  BusinessReqState {

    SUCCESS(10200, "请求成功"),

    REQUEST_PARAM_FAILURE(10400,"请求参数错误"),

    INTERNAL_SERVICE_ERROR(10500,"系统内部异常");

    private final Integer code;

    private final String msg;


}
