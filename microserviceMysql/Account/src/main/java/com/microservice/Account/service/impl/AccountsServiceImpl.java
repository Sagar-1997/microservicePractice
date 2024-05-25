package com.microservice.Account.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.microservice.Account.Dto.AccountsDto;
import com.microservice.Account.Dto.CustomerDto;
import com.microservice.Account.Entity.Accounts;
import com.microservice.Account.Entity.Customer;
import com.microservice.Account.Exception.CustomerAlreadyExistsException;
import com.microservice.Account.Exception.ResourceNotFoundException;
import com.microservice.Account.Repository.AccountsRepository;
import com.microservice.Account.Repository.CustomerRespository;
import com.microservice.Account.constants.AccountsConstants;
import com.microservice.Account.mapper.AccountsMapper;
import com.microservice.Account.mapper.CustomerMapper;
import com.microservice.Account.service.IAccountsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRespository customerRespository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(new Customer(), customerDto);
        Optional<Customer> customerByMobileNumber = customerRespository
                .findByMobileNumber(customerDto.getMobileNumber());
        if (customerByMobileNumber.isPresent()) {
            throw new CustomerAlreadyExistsException(
                    "Customer already registered with given mobileNumber : " + customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRespository.save(customer);
        accountsRepository.save(createNewAccounts(savedCustomer));
    }

    private Accounts createNewAccounts(Customer customer) {
        Accounts newAccounts = new Accounts();
        newAccounts.setCustomerId(customer.getCustomerId());
        long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
        newAccounts.setAccountNumber(randomAccountNumber);
        newAccounts.setAccountType(AccountsConstants.SAVINGS);
        newAccounts.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccounts;
    }

    @Override
    public CustomerDto fetchAccounts(String mobileNumber) {
        // TODO Auto-generated method stub
        Customer customer = customerRespository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "customerId",
                        customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(account, new AccountsDto());
        customerDto.setAccountsDto(accountsDto);
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber",
                            accountsDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accounts, accountsDto);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRespository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString()));
            CustomerMapper.mapToCustomer(customer, customerDto);
            customerRespository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Optional<Customer> customer = customerRespository.findByMobileNumber(mobileNumber);
        if (!customer.isPresent()) {
            throw new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber);
        }
        accountsRepository.deleteByCustomerId(customer.get().getCustomerId());
        customerRespository.deleteById(customer.get().getCustomerId());
        return true;
    }

}
