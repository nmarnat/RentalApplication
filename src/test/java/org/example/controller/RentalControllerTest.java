package org.example.controller;

import org.example.model.Book;
import org.example.model.Rental;
import org.example.model.User;
import org.example.service.RentalService;
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

import java.util.Date;
import java.util.List;

//TODO Fix test
@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = RentalController.class)
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalService rentalService;

    @Test
    void testAddRental() throws Exception {
        User user = new User("Azerty", "Uiop");
        user.setId(1);
        Book book = new Book("Test book");
        book.setId(2);
        List<Book> books = List.of(book);

//        LocalDate startDate = LocalDate.of(2022, 11, 5);
//        LocalDate endDate = LocalDate.of(2022, 11, 6);
        Date startDate = new Date(2022, 10, 5);
        Date endDate = new Date(2022, 10, 6);

        Rental rental = new Rental(user, startDate, endDate, books);

        Mockito.when(rentalService.save(Mockito.any(Rental.class))).thenReturn(rental);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/rental?userName=Uiop&startDate=20221105&endDate=20221106&books=Test book")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"user\":\"Uiop\",\"startDate\":\"2022-11-05T23:00:00.000+0000\",\"endDate\":\"2022-11-06T23:00:00.000+0000\",\"books\":[{\"id\":2,\"name\":\"Test book\"}]}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testGetAllRentals() throws Exception {
        User user = new User("Azerty", "Uiop");
        Book book = new Book("Test book");
        book.setId(1);
        List<Book> books = List.of(book);

//        LocalDate startDate = LocalDate.of(2022, 11, 5);
//        LocalDate endDate = LocalDate.of(2022, 11, 6);
        Date startDate = new Date(2022, 10, 4);
        Date endDate = new Date(2022, 10, 5);

        Rental rental = new Rental(user, startDate, endDate, books);
        List<Rental> rentals = List.of(rental);

        Mockito.when(rentalService.findAll()).thenReturn(rentals);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rentals")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"startDate\":\"2022-11-04T23:00:00.000+0000\",\"endDate\":\"2022-11-05T23:00:00.000+0000\",\"books\":[{\"id\":1,\"name\":\"Test book\"}],\"user\":\"Uiop\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}