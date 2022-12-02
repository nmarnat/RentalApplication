package org.example.controller;

import org.example.model.Book;
import org.example.service.BookService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testAddBook() throws Exception {
        Book book = new Book("Test book");

        Mockito.when(bookService.save(Mockito.any(Book.class))).thenReturn(book);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/book?name=Test book")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"name\":\"Test book\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testGetAllBooks() throws Exception {
        Book book1 = new Book("First test book");
        Book book2 = new Book("Second test book");

        List<Book> books = Arrays.asList(book1, book2);

        Mockito.when(bookService.findAll()).thenReturn(books);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/books")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"name\":\"First test book\"},{\"name\":\"Second test book\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void getBookByName() throws Exception {
        Book book = new Book("Test book");

        Mockito.when(bookService.findByName("Test book")).thenReturn(Optional.of(book));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/book?name=Test book")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"name\":\"Test book\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}