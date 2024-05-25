package com.microservice.Account.mapper;

import com.microservice.Account.Dto.AccountsDto;
import com.microservice.Account.Entity.Accounts;

public class AccountsMapper {

    public static Accounts mapToAccounts(Accounts accounts, AccountsDto accountsDto) {
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        return accounts;
    }

    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        return accountsDto;
    }
}
