package com.liuwill.testng;

import com.liuwill.SpringBootDemo.controller.DataController;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Administrator on 2015/10/20.
 */
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class DataControllerTest extends AbstractTestNGSpringContextTests {

    private MockMvc mvc;
    private static final String template = "Hello, %s!";

    @BeforeClass(groups = {"data"})
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new DataController()).build();
    }

    @Test(groups = {"data"})
    public void getGreetingTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.status").value("success"));
    }

    @Test(groups = {"data"})
    public void testGreetingParam() throws Exception {
        String username = "liuwill";
        mvc.perform(MockMvcRequestBuilders.get("/greeting").param("name",username).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.content").value(String.format(template, username)));
    }
}

