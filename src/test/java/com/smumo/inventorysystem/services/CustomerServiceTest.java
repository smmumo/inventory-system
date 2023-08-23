package com.smumo.inventorysystem.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smumo.inventorysystem.dto.CreateCustomerDTO;
import com.smumo.inventorysystem.dto.CustomerDTO;
import com.smumo.inventorysystem.dto.mapper.CustomerDTOMapper;
import com.smumo.inventorysystem.entities.Customer;
import com.smumo.inventorysystem.exception.CustomerAlreadyExistsException;
import com.smumo.inventorysystem.exception.NoSuchCustomerExistsException;
import com.smumo.inventorysystem.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
//    @MockBean
//    private CustomerRepository customerRepository;
//   // @InjectMocks
//    @Autowired
//    private CustomerService customerService;
//   // private ObjectMapper objectMapper;
//    @Mock
//    private  CustomerDTOMapper customerDTOMapper;
@Mock private CustomerRepository customerRepository;
@Mock private  CustomerDTOMapper customerDTOMapper;
//private  AutoCloseable autoCloseable;
private CustomerService underTest;
@BeforeEach
void setUp(){
  // autoCloseable = MockitoAnnotations.openMocks(this);
  underTest = new CustomerService(customerRepository,customerDTOMapper);
}

//@AfterEach
//void tearDown() throws Exception {
//    autoCloseable.close();
//}

//    @BeforeEach
//    void initUseCase() {
//        customerDTOMapper = Mockito.mock(CustomerDTOMapper.class);
//        customerRepository = Mockito.mock(CustomerRepository.class);
//        customerService = new CustomerService(customerRepository,customerDTOMapper);
//    }

    @Test
    void  GetCustomerList() {
       //when
       underTest.getCustomerList();
       //then
       verify(customerRepository).findAll();
    }

    @Test
    void canAddCustomer() {
    //given
        CreateCustomerDTO customerDTO = new CreateCustomerDTO();
        customerDTO.setEmail("jamila@gmail.com");
        customerDTO.setPhone("071465434");
        customerDTO.setFirstName("Jamila");
        customerDTO.setLastName("Jamila f");

        Customer customer = customerDTO.toCustomer();
        //when
        underTest.createCustomer(customerDTO);


        verify(customerRepository).save(customer);
    }

    @Test
    void willThrowWhenEmailIsTaken() {
        CreateCustomerDTO customerDTO = new CreateCustomerDTO();
        customerDTO.setEmail("jamila@gmail.com");
        customerDTO.setPhone("071465434");
        customerDTO.setFirstName("Jamila");
        customerDTO.setLastName("Jamila f");

        given(customerRepository.selectExistsEmail(anyString())).willReturn(true);

        //when
        //then
        assertThatThrownBy(() -> underTest.createCustomer(customerDTO))
                .isInstanceOf(CustomerAlreadyExistsException.class)
                .hasMessageContaining("Customer of Email [%s] Already Exist".formatted(customerDTO.getEmail()));

        verify(customerRepository,never()).save(any());
}

    @Test
    void canDeleteCustomer() {
        // given
        long id = 10;
        given(customerRepository.existsById(id)).willReturn(true);
        //when
        underTest.deleteCustomer(id);
        //then
        verify(customerRepository).deleteById(id);
    }

    @Test
    void willThrowWhenDeleteCustomerNotFound() {
    long id = 10;
        given(customerRepository.existsById(id)).willReturn(false);
        // when
        // then
        assertThatThrownBy(() -> underTest.deleteCustomer(id))
                .isInstanceOf(NoSuchCustomerExistsException.class)
                .hasMessageContaining("Customer Not Found");

        verify(customerRepository, never()).deleteById(any());
    }

    @Test
    @Disabled
    void  canGetCustomerList() {
        //given
        CustomerDTO customerDTO = new CustomerDTO(
                1L,"simon","mumo","simon65@gmail.com","073432444"
        );
        List<CustomerDTO> customerList = List.of(customerDTO);
       // when(customerRepository.findAll().stream().map(customerDTOMapper).collect(Collectors.toList()))
         //       .thenReturn(customerList) ;
        when(customerRepository.findAll().stream().map(customerDTOMapper).toList()).thenReturn(customerList);

        //when
        // List<CustomerDTO> customerResponseList = customerService.getCustomerList();

         //then
        //assertEquals(customerList.size(),customerResponseList.size());
       // assertThat(customerResponseList.size()).isGreaterThan(0);
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