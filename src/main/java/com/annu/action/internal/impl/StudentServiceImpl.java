package com.annu.action.internal.impl;

import com.annu.action.internal.repository.StudentRepository;
import com.annu.action.service.StudentService;
import com.annu.action.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentVo findOne(Long id) {
        return StudentVo.fromDto(studentRepository.findById(id));
    }

    @Override
    public StudentVo findByEqualToName(String name) {
        return studentRepository.findByEqualToName(name);
    }

}
