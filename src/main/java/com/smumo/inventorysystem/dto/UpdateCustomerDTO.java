package com.smumo.inventorysystem.dto;

public record UpdateCustomerDTO(
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
