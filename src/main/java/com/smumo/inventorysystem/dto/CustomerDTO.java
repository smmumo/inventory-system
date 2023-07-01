package com.smumo.inventorysystem.dto;

public record CustomerDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone
) {
}
