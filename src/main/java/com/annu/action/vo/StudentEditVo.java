package com.annu.action.vo;

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

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Integer age;

    @NotBlank
    private String school;

    @NotBlank
    private String address;
}
