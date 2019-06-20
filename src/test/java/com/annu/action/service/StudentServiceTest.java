package com.annu.action.service;

import com.annu.action.internal.repository.StudentRepository;
import com.annu.action.vo.StudentVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void findOne() {
//        given(this.studentService.findOne(1L))
//                .willReturn(StudentVo.builder()
//                        .id(2L)
//                        .name("mike")
//                        .address("北京")
//                        .build());
        System.out.println(studentService.findOne(1L));
    }

    /**
     * 利用mockito做测试：
     * 在方法{@link StudentRepository#findByEqualToName}没有实现，
     * 而又需要对{@link StudentService#findByEqualToName(String)}测试
     * 可以mock没有实现的方法，指定方法的参数和返回，测试时传相同的参数即可
     */
    @Test
    public void findByEqualToName() {
        given(studentRepository.findByEqualToName("mike"))
                .willReturn(StudentVo.builder()
                        .id(2L)
                        .name("mike")
                        .address("北京")
                        .build());
        System.out.println(studentService.findByEqualToName("mike"));
    }
}