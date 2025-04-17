package com.spring.boot.service;


import com.spring.boot.controllar.vm.AccountResponseVm;
import com.spring.boot.dto.AccountDto;
import com.spring.boot.model.Account;
import jakarta.transaction.SystemException;

import java.util.List;

public interface AccountService {

    //List<Account> getAccounts();

   List<AccountDto> getAccountsDto();

    AccountDto CreatAccount(AccountDto accountDto) throws SystemException;

    AccountDto UpdateAccount(AccountDto accountDto) throws SystemException;

    boolean deleteAccount(Long id);

    List< AccountDto> search(String searchValue) throws SystemException;

    List<AccountDto> getByPhone(String phone) throws SystemException;
}