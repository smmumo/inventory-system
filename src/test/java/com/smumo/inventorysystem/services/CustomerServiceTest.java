package com.smumo.inventorysystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smumo.inventorysystem.dto.CustomerDTO;
import com.smumo.inventorysystem.dto.mapper.CustomerDTOMapper;
import com.smumo.inventorysystem.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;
   // private ObjectMapper objectMapper;
    @Mock
    private  CustomerDTOMapper customerDTOMapper;
//    @BeforeEach
//    void initUseCase() {
//        customerDTOMapper = Mockito.mock(CustomerDTOMapper.class);
//        customerRepository = Mockito.mock(CustomerRepository.class);
//        customerService = new CustomerService(customerRepository,customerDTOMapper);
//    }

    @Test
    void getCustomerList() {
        //given
        CustomerDTO customerDTO = new CustomerDTO(
                1L,"simon","mumo","simon65@gmail.com","073432444"
        );
        List<CustomerDTO> customerList = List.of(customerDTO);
        when(customerRepository.findAll().stream().map(customerDTOMapper).collect(Collectors.toList()))
                .thenReturn(customerList) ;

        //when
         List<CustomerDTO> customerResponseList = customerService.getCustomerList();

         //then
        assertEquals(customerList.size(),customerResponseList.size());
        assertThat(customerResponseList.size()).isGreaterThan(0);
   }

//    @Test
//    void getCustomerById() {
//    }
//
//    @Test
//    void createCustomer() {
//    }
//
//    @Test
//    void updateCustomer() {
//    }
//
//    @Test
//    void deleteCustomer() {
//    }
}