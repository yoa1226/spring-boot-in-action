package com.annu.action.web;

import com.annu.action.vo.StudentEditVo;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class StudentControllerTest extends ControllerTest{

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


    @Test
    public void search() throws Exception {
        this.mockMvc.perform(get("/students/search")
                .param("name", "")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //test failed. please use chrome
    @Test
    public void export() throws Exception {
        this.mockMvc.perform(get("/students/export")
                .param("name", "nike")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());
    }
}