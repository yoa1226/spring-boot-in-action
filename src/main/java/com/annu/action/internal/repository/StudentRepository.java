package com.annu.action.internal.repository;

import com.annu.action.internal.dto.StudentDto;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {

    public StudentDto findById(Long id) {
        return StudentDto.builder()
                .id(1L)
                .name("nike")
                .age(18)
                .address("上海")
                .school("上海大学")
                .build();
    }

    public StudentDto findByEqualToName(String name) {
        return StudentDto.builder()
                .id(1L)
                .name("nike")
                .age(18)
                .address("上海")
                .school("上海大学")
                .build();
    }

    public List<StudentDto> findListByEqualToName(String name) {
        return Lists.newArrayList(StudentDto.builder()
                .id(1L)
                .name("nike")
                .age(18)
                .address("上海")
                .school("上海大学")
                .build());
    }
}
