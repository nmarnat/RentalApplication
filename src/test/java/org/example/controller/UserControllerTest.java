package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
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
@WebMvcTest(value = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testAddUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setFirstName("Nicola");
        user.setLastName("Marnat");

        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user?id=1&firstName=Nicola&lastName=Marnat")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":1,\"firstName\":\"Nicola\",\"lastName\":\"Marnat\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1);
        user1.setFirstName("Azerty");
        user1.setLastName("Uiop");

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("Qsdfgh");
        user2.setLastName("Jklm");

        List<User> users = Arrays.asList(user1, user2);

        Mockito.when(userService.findAll()).thenReturn(users);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"id\":1,\"firstName\":\"Azerty\",\"lastName\":\"Uiop\"},{\"id\":2,\"firstName\":\"Qsdfgh\",\"lastName\":\"Jklm\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User();
        user.setId(1);
        user.setFirstName("Azerty");
        user.setLastName("Uiop");

        Mockito.when(userService.findById(1)).thenReturn(Optional.of(user));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/userId?id=1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":1,\"firstName\":\"Azerty\",\"lastName\":\"Uiop\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void getUserByLastName() throws Exception {
        User user = new User();
        user.setId(1);
        user.setFirstName("Azerty");
        user.setLastName("Uiop");

        Mockito.when(userService.findByLastName("Uiop")).thenReturn(Optional.of(user));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/userName?lastName=Uiop")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":1,\"firstName\":\"Azerty\",\"lastName\":\"Uiop\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }
}