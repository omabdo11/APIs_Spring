package com.spring.boot.service.Impl;

import com.spring.boot.ModelMapper.AccountMapper;
import com.spring.boot.controllar.vm.AccountResponseVm;
import com.spring.boot.dto.AccountDto;
import com.spring.boot.model.Account;
import com.spring.boot.repo.AccountRepo;
import com.spring.boot.service.AccountService;
import jakarta.transaction.SystemException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountRepo accountRepo;

//   @Autowired
//   private ModelMapper modelMapper;

    @Override
    public List<AccountDto> getAccountsDto() {
        List<Account> accounts = accountRepo.findAll();

        return extractAccounts(accounts);
    }

    @Override
    public AccountDto CreatAccount(AccountDto accountDto) throws SystemException {
        if(Objects.nonNull(accountDto.getId())) {
            throw new SystemException("id must be null");
        }
      Optional<Account> ExistAccount= accountRepo.findByUserName(accountDto.getUserName());
        if(ExistAccount.isPresent()) {
            throw new SystemException("Account already exist with same username : " + accountDto.getUserName());
        }

        Account account= AccountMapper.ACCOUNT_MAPPER.toAccount(accountDto);


//        Account account= modelMapper.map(accountDto, Account.class);
//        Account account = new Account();
//        account.setUserName(accountDto.getUserName());
//        account.setPhoneNumber(accountDto.getPhoneNumber());
//        account.setPassword(accountDto.getPassword());
        accountRepo.save(account);
         return accountDto ;
    }

    @Override
    public AccountDto UpdateAccount(AccountDto accountDto) throws SystemException {
        if(Objects.isNull(accountDto.getId())) {
            throw new SystemException("id must be not  null");
        }
       Optional<Account> optionalAccount= accountRepo.findById(accountDto.getId());
        if(optionalAccount.isEmpty()){
            throw new SystemException("id not exist"+accountDto.getId());
        }

       Account account= AccountMapper.ACCOUNT_MAPPER.toAccount(accountDto);

//        Account account= modelMapper.map(accountDto, Account.class);
//        Account account = new Account();
//        account.setId(accountDto.getId());
//        account.setUserName(accountDto.getUserName());
//        account.setPhoneNumber(accountDto.getPhoneNumber());
//        account.setPassword(accountDto.getPassword());

        accountRepo.save(account);
        return accountDto ;
    }

    @Override
    public boolean deleteAccount(Long id) {
        Optional<Account> optionalAccount= accountRepo.findById(id);
        if(optionalAccount.isEmpty()){
            return false;
        }
        accountRepo.deleteById(id);
        return true;
    }

    @Override
    public List<AccountDto> search(String searchValue) throws SystemException {
        if(Objects.isNull(searchValue)) {
            throw new SystemException("search value must be not null");
        }
        List<Account> accounts = accountRepo.findByUserNameContains(searchValue);
        return extractAccounts(accounts );
    }

    @Override
    public List<AccountDto> getByPhone(String phone) throws SystemException {
        if(Objects.isNull(phone)) {
            throw new SystemException("search value must be not null");
        }
        List<Account> accounts=accountRepo.findByUserNameContains(phone);

        return extractAccounts(accounts);
    }

    private  List<AccountDto>  extractAccounts(List<Account> accounts ) {
        List<AccountDto>  accountDtos = new ArrayList<>();

        for (Account ac  : accounts) {
            AccountDto accountDto = AccountMapper.ACCOUNT_MAPPER.toAccountDto(ac);
            accountDto.setLength(String.valueOf(ac.getUserName().length()));


          //  accountDto.setPassword(null);  // to ignore password saved already
//            AccountDto accountDto = new AccountDto();
//            accountDto.setId(ac.getId());
//            accountDto.setUserName(ac.getUserName());
//            accountDto.setPhoneNumber(ac.getPhoneNumber());
//            accountDto.setLength(String.valueOf(ac.getUserName().length()));

            accountDtos.add(accountDto);

        }
        return accountDtos;
    }
}
