package com.annu.action.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Violation {

    private String field;

    private String msg;

}
