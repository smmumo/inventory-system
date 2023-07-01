package com.smumo.inventorysystem.dto.mapper;

import com.smumo.inventorysystem.dto.CustomerDTO;
import com.smumo.inventorysystem.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerDTOMapper implements Function<Customer, CustomerDTO> {
    @Override
    public CustomerDTO apply(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }
}
