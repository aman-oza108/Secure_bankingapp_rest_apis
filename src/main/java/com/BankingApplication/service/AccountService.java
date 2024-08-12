package com.BankingApplication.service;

import java.util.List;

import com.BankingApplication.dto.AccountDto;


public interface AccountService {

	//6..
	AccountDto Createacount(AccountDto accountdto); 
	
    AccountDto getaccountbyid(Long id); 
    
    AccountDto depositeamount(Long id, double amount);
	
    AccountDto withdraw(Long id, double amount);
    
    List<AccountDto>getallaccounts();
    
    void delaccount(Long id);
}
