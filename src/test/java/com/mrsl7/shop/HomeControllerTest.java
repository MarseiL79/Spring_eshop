package com.mrsl7.shop;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import com.mrsl7.shop.controller.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)/*Веб-тест для HomeController*/
public class HomeControllerTest {


    @Autowired
    private MockMvc mockMvc; /*Внедряет MockMvc*/

    @Test

    public void testHomePage() throws Exception {

        mockMvc.perform(get("/").with(user("Mars").password("12345").roles("USER"))) /*Выполняет GET */

                .andExpect(status().isOk()) /*-Ожидает HTTP 200*/

                .andExpect(view().name("index")) /*Ожидает home*/

                .andExpect(content().string(
                        containsString("Bla bla bla,")));
    }
}
