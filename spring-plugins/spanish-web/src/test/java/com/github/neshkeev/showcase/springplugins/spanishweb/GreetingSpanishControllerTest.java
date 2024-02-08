package com.github.neshkeev.showcase.springplugins.spanishweb;

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
class GreetingSpanishControllerTest {

    @Test
    public void testDefault(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/es/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("¡Hola Mundo!"));
    }

    @Test
    public void testParamName(@Autowired MockMvc mvc) throws Exception {
        mvc.perform(get("/es/hello").param("name", "Juan"))
                .andExpect(status().isOk())
                .andExpect(content().string("¡Hola Juan!"));
    }
}