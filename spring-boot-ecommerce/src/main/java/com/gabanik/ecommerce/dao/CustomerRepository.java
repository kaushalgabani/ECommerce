package com.gabanik.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabanik.ecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	
}
