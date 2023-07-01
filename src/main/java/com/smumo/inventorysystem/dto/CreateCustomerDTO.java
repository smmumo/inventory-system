package com.smumo.inventorysystem.dto;

import com.smumo.inventorysystem.entities.Customer;
import jakarta.validation.constraints.*;
//import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class CreateCustomerDTO {
    @Size(max = 10,min = 3,message = "firstName should be more than 3 character")
    @NotBlank(message = "firstName is required")
    private String firstName;
    @Size(max = 10,min = 3,message = "lastName should be more than 3 character")
    @NotBlank(message = "last name is required")
    private String lastName;
    @NotBlank(message = "email is required")
    @Email(message = "The email address is invalid",
            flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String email;
    @NotBlank(message = "Phone Number is required")
    @Size(max = 10,message = "phone number should not be greater than 10 character")
    private String phone;

//    @NotNull(message = "The date of birth is required.")
//    @Past(message = "The date of birth must be in the past.")
//    private Date dateOfBirth;

    public Customer toCustomer(){
       Customer newCustomer = new Customer();
       newCustomer.setEmail(email);
       newCustomer.setLastName(lastName);
       newCustomer.setPhone(phone);
       newCustomer.setFirstName(firstName);
       return newCustomer;
    }
}
