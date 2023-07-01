package com.smumo.inventorysystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smumo.inventorysystem.dto.CustomerDTO;
import com.smumo.inventorysystem.services.CustomerService;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.reflect.Array.get;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {
    //https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @Autowired
    private ObjectMapper objectMapper;
    private final String baseUrl="/api/v1/customers";

    @Test
    void testGetCustomersList() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO(
                1L,"simon","mumo","simon65@gmail.com","073432444"
        );
        List<CustomerDTO> customerList = List.of(customerDTO);

        when(customerService.getCustomerList())
                .thenReturn(customerList);

        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
               // .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());


    }
    @Test
    void testGetCustomerById() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO(
                1L,"simon","mumo","simon65@gmail.com","073432444"
        );
        //List<CustomerDTO> customerList = List.of(customerDTO);

        when(customerService.getCustomerById(1L)).thenReturn(customerDTO);

        mockMvc.perform(MockMvcRequestBuilders.get( baseUrl+"/{id}",1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customerDTO.id()));
    }


    @Test
    void createCustomerTest() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO(
                1L,"simon","mumo","simon65@gmail.com","073432444"
        );
        mockMvc.perform(MockMvcRequestBuilders.post(baseUrl+"/create")
                .content(asJsonString(customerDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void updateCustomerTest() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO(
                1L,"simon","mumo","simon65@gmail.com","073432444"
        );
        mockMvc.perform(MockMvcRequestBuilders.put(baseUrl+"/update/{id}",1L)
                .content(asJsonString(customerDTO)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    @Test
    void deleteCustomerTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl+"/{id}",1L))
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}