package kz.iitu.itse1908.daniyal.finalspring.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import kz.iitu.itse1908.daniyal.finalspring.FinalSpringApplication;
import kz.iitu.itse1908.daniyal.finalspring.config.Configuration;
import kz.iitu.itse1908.daniyal.finalspring.models.Book;
import kz.iitu.itse1908.daniyal.finalspring.models.BuyRequest;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { FinalSpringApplication.class, Configuration.class })
@WebAppConfiguration
class AdminControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testExistingOfClientController() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("AdminController"));
    }

    @Test
    public void testHomepage() throws Exception {
        this.mockMvc.perform(get("/api/admin")).andDo(print());
    }

    @Test
    @WithMockUser(username="daniyal", password = "123", roles = "ADMIN")
    void getAllBooks() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/admin/getAllBooks")).
                andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @Test
    @WithMockUser(username="daniyal", password = "123", roles = "ADMIN")
    void getBook() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/admin/getBookById/1")).andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }

    @Test
    @WithMockUser(username="daniyal", password = "123", roles = "ADMIN")
    void addBook() throws Exception {
        Book anObject = new Book();
        anObject.setId(Long.valueOf(1));
        anObject.setName("Harry Potter");
        anObject.setAuthor("Rowling");
        anObject.setCover("Hardcover");
        anObject.setGenre("Fantasy");
        anObject.setStock(Long.valueOf(10));
        // more
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(anObject);

        this.mockMvc.perform(post("/api/admin/addBook").contentType(APPLICATION_JSON_UTF8).
                content(requestJson)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="daniyal", password = "123", roles = "ADMIN")
    void updateBook() throws Exception {
        Book anObject = new Book();
        anObject.setId(Long.valueOf(1));
        anObject.setName("Harry Potter");
        anObject.setAuthor("Rowling");
        anObject.setCover("Hardcover");
        anObject.setGenre("Fantasy");
        anObject.setStock(Long.valueOf(10));
        // more
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(anObject);

        this.mockMvc.perform(put("/api/admin/updateBook/3").contentType(APPLICATION_JSON_UTF8).
                content(requestJson)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="daniyal", password = "123", roles = "ADMIN")
    void deleteBook() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();

        MvcResult mvcResult = this.mockMvc.perform(delete("/api/admin/deleteBook/{id}", "5")).
                andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("text/plain;charset=UTF-8", mvcResult.getResponse().getContentType());
    }

    @Test
    @WithMockUser(username="daniyal", password = "123", roles = "ADMIN")
    void getAllFines() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/admin/getAllFines")).
                andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @Test
    @WithMockUser(username="daniyal", password = "123", roles = "ADMIN")
    void addFine() {
    }

    @Test
    @WithMockUser(username="daniyal", password = "123", roles = "ADMIN")
    void getAllTickets() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/admin/getAllFines")).
                andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @Test
    @WithMockUser(username="daniyal", password = "123", roles = "ADMIN")
    void getAllRoles() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/admin/getAllRoles")).
                andExpect(status().isOk()).andReturn();
        Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @Test
    @WithMockUser(username="daniyal", password = "123", roles = "ADMIN")
    void getAllUsers() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/admin/getAllUsers")).
                andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals("application/json", mvcResult.getResponse().
                getContentType());
    }
}