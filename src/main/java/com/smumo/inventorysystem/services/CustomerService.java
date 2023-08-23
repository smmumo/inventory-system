package com.smumo.inventorysystem.services;

import com.smumo.inventorysystem.dto.CreateCustomerDTO;
import com.smumo.inventorysystem.dto.CustomerDTO;
import com.smumo.inventorysystem.dto.UpdateCustomerDTO;
import com.smumo.inventorysystem.dto.mapper.CustomerDTOMapper;
import com.smumo.inventorysystem.entities.Customer;
import com.smumo.inventorysystem.exception.CustomerAlreadyExistsException;
import com.smumo.inventorysystem.exception.NoSuchCustomerExistsException;
import com.smumo.inventorysystem.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDTOMapper customerDTOMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerDTOMapper customerDTOMapper) {
        this.customerRepository = customerRepository;
        this.customerDTOMapper = customerDTOMapper;
    }

    public List<CustomerDTO> getCustomerList() {
         return customerRepository.findAll().stream()
                 .map(customerDTOMapper).collect(Collectors.toList());
    }
    public CustomerDTO getCustomerById(long id){
        return customerRepository.findById(id).map(customerDTOMapper)
                .orElseThrow(
                        () -> new NoSuchCustomerExistsException("customer of id [%s] not found".formatted(id)));
    }

    public Customer createCustomer(CreateCustomerDTO customerDTO) {
       // Optional<Customer> existingCustomer = customerRepository.findByEmail(customerDTO.getEmail());
       var exist = customerRepository.selectExistsEmail(customerDTO.getEmail());
        if(exist){
            throw new CustomerAlreadyExistsException("Customer of Email [%s] Already Exist".formatted(customerDTO.getEmail()));
        }else{
            Customer customer = customerDTO.toCustomer(); // new Customer();
//            customer.setEmail(customerDTO.getEmail());
//            customer.setPhone(customerDTO.getPhone());
//            customer.setFirstName(customerDTO.getFirstName());
//            customer.setLastName(customerDTO.getLastName());
            return customerRepository.save(customer);
        }

    }
    public String UpdateCustomer(Long id , UpdateCustomerDTO customerDTO){

        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null){
            //throw execption
            throw new NoSuchCustomerExistsException("No Such Customer exists!!");
        }
        customer.setEmail(customerDTO.email());
        customer.setPhone(customerDTO.phone());
        customer.setFirstName(customerDTO.firstName());
        customer.setLastName(customerDTO.lastName());
        customerRepository.save(customer);
        return "Record updated Successfully";
    }

    public void deleteCustomer(long id) {
       boolean exists = customerRepository.existsById(id);
        if(!exists){
            throw new NoSuchCustomerExistsException("Customer Not Found");
        }
        customerRepository.deleteById(id);
    }

    public List<Customer> getOrders(Long customerId) {
        return customerRepository.findAll();
    }

    public Customer registerCustomer(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findByEmail(customer.getEmail());
        if(existingCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer of Email [%s] Already Exist".formatted(customer.getEmail()));
        }else{
            return customerRepository.save(customer);
        }
    }
}
