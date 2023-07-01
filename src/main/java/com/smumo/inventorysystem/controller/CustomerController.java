package com.smumo.inventorysystem.controller;

import com.smumo.inventorysystem.dto.CreateCustomerDTO;
import com.smumo.inventorysystem.dto.CustomerDTO;
import com.smumo.inventorysystem.dto.UpdateCustomerDTO;
import com.smumo.inventorysystem.entities.Customer;
import com.smumo.inventorysystem.services.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public List<CustomerDTO> getCustomers(){
        return customerService.getCustomerList();
    }
    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(   @NotBlank(message = "id is required")
                                          @PathVariable  long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CreateCustomerDTO customer){
        Customer savedCustomer = customerService.createCustomer(customer);
        //URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
             //   .buildAndExpand(savedCustomer.getId()).toUri();
        return ResponseEntity.ok("success");
       // return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@Valid @RequestBody Customer customer){
        Customer savedCustomer = customerService.registerCustomer(customer);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCustomer(@NotBlank @PathVariable Long id,
                                                 @RequestBody UpdateCustomerDTO customer ){
        customerService.UpdateCustomer(id,customer);
        return ResponseEntity.ok("success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@NotBlank @PathVariable long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/orders/{customerId}")
    public List<Customer> getOrders( @PathVariable  Long customerId){
        return  customerService.getOrders(customerId);
    }

}
