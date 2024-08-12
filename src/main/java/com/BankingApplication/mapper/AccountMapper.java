package com.BankingApplication.mapper;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.entity.Account;

public class AccountMapper {

	public static Account maptoAccount(AccountDto accountdto) {

		Account account = new Account(

				accountdto.getId(), accountdto.getAccountholderName(), accountdto.getBalance()

		);

		return account;
	}

	public static AccountDto mapToAccountDto(Account account) {

		AccountDto accountdto = new AccountDto(

				account.getId(), account.getAccountholderName(), account.getBalance()

		);
		return accountdto;
	}

}
