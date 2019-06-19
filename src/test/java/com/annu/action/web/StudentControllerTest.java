package com.annu.action.web;

import com.annu.action.vo.StudentEditVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void findOne() throws Exception {
        this.mockMvc
                .perform(get("/students/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {
        var student = StudentEditVo.builder()
                .id(1L)
                .name("nike")
                .address("上海")
                .build();
        this.mockMvc
                .perform(post("/students")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(OBJECT_MAPPER.writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isOk());

    }
}