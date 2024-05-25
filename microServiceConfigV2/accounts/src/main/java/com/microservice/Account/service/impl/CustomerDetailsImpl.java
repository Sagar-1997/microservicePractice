package com.microservice.Account.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.Account.Dto.AccountsDto;
import com.microservice.Account.Dto.CardsDto;
import com.microservice.Account.Dto.CustomerDetailsDto;
import com.microservice.Account.Dto.LoansDto;
import com.microservice.Account.Entity.Accounts;
import com.microservice.Account.Entity.Customer;
import com.microservice.Account.Exception.ResourceNotFoundException;
import com.microservice.Account.Repository.AccountsRepository;
import com.microservice.Account.Repository.CustomerRespository;
import com.microservice.Account.mapper.AccountsMapper;
import com.microservice.Account.mapper.CustomerMapper;
import com.microservice.Account.service.ICustomerService;
import com.microservice.Account.service.client.CardsFeignClient;
import com.microservice.Account.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerDetailsImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRespository customerRespository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        // TODO Auto-generated method stub
        Customer customer = customerRespository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "CustomerId",
                        customer.getCustomerId().toString()));
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer,
                new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        ResponseEntity<CardsDto> fetchCardDetails = cardsFeignClient.fetchCardDetails(mobileNumber);
        ResponseEntity<LoansDto> fetchLoanDetails = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setCardsDto(fetchCardDetails.getBody());
        customerDetailsDto.setLoansDto(fetchLoanDetails.getBody());
        return customerDetailsDto;
    }

}
