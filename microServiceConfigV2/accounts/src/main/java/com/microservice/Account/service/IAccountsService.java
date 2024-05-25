package com.microservice.Account.service;

import com.microservice.Account.Dto.CustomerDto;

public interface IAccountsService {

    public void createAccount(CustomerDto customerDto);

    public CustomerDto fetchAccounts(String mobileNumber);

    public boolean updateAccount(CustomerDto customerDto);

    public boolean deleteAccount(String mobileNumber);
}
