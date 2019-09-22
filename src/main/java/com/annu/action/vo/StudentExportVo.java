package com.annu.action.vo;

import com.annu.action.internal.dto.StudentDto;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentExportVo {


    @CsvBindByName(column = "id")
    @CsvBindByPosition(position = 0)
    private Long id;

    @CsvBindByName(column = "姓名")
    @CsvBindByPosition(position = 1)
    private String name;

    @CsvBindByName(column = "年龄")
    @CsvBindByPosition(position = 2)
    private Integer age;

    @CsvBindByName(column = "学校")
    @CsvBindByPosition(position = 3)
    private String school;

    @CsvBindByName(column = "住址")
    @CsvBindByPosition(position = 4)
    private String address;

    public static StudentExportVo fromDto(StudentDto dto) {
        return StudentExportVo.builder()
                .id(dto.getId())
                .name(dto.getName())
                .age(dto.getAge())
                .school(dto.getSchool())
                .address(dto.getAddress())
                .build();
    }

    //较长字符串且是数字时 避免在excel中显示科学计数法格式
    public static String wrapperWithSingleQuote(String field) {
        return "'" + field;
    }


}
