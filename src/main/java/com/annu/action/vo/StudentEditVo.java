package com.annu.action.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentEditVo {

    @ApiModelProperty("学生id")
    @NotNull
    private Long id;

    @ApiModelProperty("姓名")
    @NotBlank
    private String name;

    @ApiModelProperty("年龄")
    @NotNull
    private Integer age;

    @NotBlank
    private String school;

    @NotBlank
    private String address;
}
