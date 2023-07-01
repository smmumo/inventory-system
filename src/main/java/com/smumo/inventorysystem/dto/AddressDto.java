package com.smumo.inventorysystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    @NotBlank(message = "country is required")
    private String country;
    @NotBlank(message = "city is required")
    private String city;
    @NotBlank(message = "zipCode is required")
    private String zipCode;
    @NotBlank(message = "street is required")
    private String street;
    @NotBlank(message = "state is required")
    private String state;


}
