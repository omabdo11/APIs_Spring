package com.spring.boot.controllar;


import com.spring.boot.controllar.vm.AccountResponseVm;
import com.spring.boot.dto.AccountDto;
import com.spring.boot.model.Account;
import com.spring.boot.service.AccountService;
import jakarta.transaction.SystemException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/account")
   public ResponseEntity<List<AccountDto>> getAccountsDto() {

        return  ResponseEntity.ok(accountService.getAccountsDto());
    }

    @PostMapping("/createaccount")
    public ResponseEntity<AccountDto> addAccounts(@RequestBody @Valid AccountDto accountDto) throws SystemException {

        return ResponseEntity.created(URI.create("/createaccount")).body(accountService.CreatAccount(accountDto)) ;
    }

    @PutMapping("/Updateaccount")
    public ResponseEntity<AccountDto> updateAccounts(@RequestBody @Valid AccountDto accountDto) throws SystemException {

        return ResponseEntity.ok(accountService.UpdateAccount(accountDto));
    }

    //http://localhost:7070/deleteaccount?id=
    @DeleteMapping("/deleteaccount")
    public ResponseEntity<Void> deleteAccounts(@RequestParam Long id) {
        accountService.deleteAccount(id);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AccountDto>> search(@RequestParam String searchValue) throws SystemException {

        return  ResponseEntity.ok(accountService.search(searchValue));
    }


    @GetMapping("/search/phone")
    public ResponseEntity<List<AccountDto>> getByPhone(@RequestParam String phone) throws SystemException {

        return  ResponseEntity.ok(accountService.getByPhone(phone));
    }

}
