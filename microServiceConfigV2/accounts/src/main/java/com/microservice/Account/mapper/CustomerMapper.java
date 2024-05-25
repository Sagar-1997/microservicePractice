package com.microservice.Account.mapper;

import com.microservice.Account.Dto.CustomerDetailsDto;
import com.microservice.Account.Dto.CustomerDto;
import com.microservice.Account.Entity.Customer;

public class CustomerMapper {

    public static Customer mapToCustomer(Customer customer, CustomerDto customerDto) {
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setName(customerDto.getName());
        return customer;
    }

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setName(customer.getName());
        return customerDto;
    }

    public static CustomerDetailsDto mapToCustomerDetailsDto(Customer customer, CustomerDetailsDto customerDetailsDto) {
        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());
        return customerDetailsDto;
    }
}
