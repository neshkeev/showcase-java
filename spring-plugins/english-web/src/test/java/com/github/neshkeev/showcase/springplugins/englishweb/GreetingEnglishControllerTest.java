package com.github.neshkeev.showcase.springplugins.englishweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GreetingEnglishControllerTest {

    @Test
    public void testDefault(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/en/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }

    @Test
    public void testParamName(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/en/hello").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, John!"));

    }
}