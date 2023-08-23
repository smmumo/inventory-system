package com.smumo.inventorysystem.repository;

import com.smumo.inventorysystem.entities.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    private  CustomerRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }
    @Test
    void itShouldCheckWhenCustomerEmailExists() {
        //given
        String email = "jamila@gmail.com";
        Customer customer = new Customer(
                1L,"Jamila","Jamila f",
                email,"071465434"
        );
        underTest.save(customer);
        //when
        boolean expected = underTest.selectExistsEmail(email);
        //then
        assertThat(expected).isTrue();
    }
    @Test
    void itShouldCheckWhenCustomerEmailDoesNotExists() {
        //given
        String email = "jamila@gmail.com";
        //when
        boolean expected = underTest.selectExistsEmail(email);
        //then
        assertThat(expected).isFalse();
    }
}