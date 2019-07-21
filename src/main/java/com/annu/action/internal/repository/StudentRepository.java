package com.annu.action.internal.repository;

import com.annu.action.internal.dto.StudentDto;
import org.springframework.stereotype.Repository;

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
}
