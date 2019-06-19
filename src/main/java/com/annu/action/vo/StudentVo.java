package com.annu.action.vo;

import com.annu.action.internal.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentVo {

    private Long id;

    private String name;

    private Integer age;

    private String school;

    private String address;

    public  static StudentVo fromDto(StudentDto dto){
        return StudentVo.builder()
                .id(dto.getId())
                .name(dto.getName())
                .age(dto.getAge())
                .school(dto.getSchool())
                .address(dto.getAddress())
                .build();
    }

}
