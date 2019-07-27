package com.annu.action.web;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest extends ControllerTest {

    @Test
    public void findOne() throws Exception {
        this.mockMvc
                .perform(get("/users/one").header("token",""))
                .andDo(print())
                .andExpect(status().isOk());
    }
}