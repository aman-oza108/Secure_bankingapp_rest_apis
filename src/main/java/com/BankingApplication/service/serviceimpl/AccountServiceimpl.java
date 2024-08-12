package com.BankingApplication.service.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.entity.Account;
import com.BankingApplication.mapper.AccountMapper;
import com.BankingApplication.repository.AccountRepository;
import com.BankingApplication.service.AccountService;

@Service
public class AccountServiceimpl implements AccountService {

	private AccountRepository repo;

	public AccountServiceimpl(AccountRepository repo) {
		super();
		this.repo = repo;
	}

	@Override
	public AccountDto Createacount(AccountDto accountdto) {

		Account account = AccountMapper.maptoAccount(accountdto);

		Account saveaccount = repo.save(account);

		return AccountMapper.mapToAccountDto(saveaccount);
	}

	@Override
	public AccountDto getaccountbyid(Long id) {

		Account account = repo.findById(id).orElseThrow(() -> new RuntimeException("Account does not excists"));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto depositeamount(Long id, double amount) {

		Account account = repo.findById(id).orElseThrow(() -> new RuntimeException("Account does not excists"));

		double totalbalance = account.getBalance() + amount;

		account.setBalance(totalbalance);

		Account saveaccount = repo.save(account);
		return AccountMapper.mapToAccountDto(saveaccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {

		Account account = repo.findById(id).orElseThrow(() -> new RuntimeException("Account does not excists"));

		if (account.getBalance() < amount) {
			throw new RuntimeException("Insufficient Balance !!");
		}

		double totalbalance = account.getBalance() - amount;

		account.setBalance(totalbalance);

		Account saveaccount = repo.save(account);

		return AccountMapper.mapToAccountDto(saveaccount);
	}

	@Override
	public List<AccountDto> getallaccounts() {
		return repo.findAll().stream().map((account)->AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
	}

	@Override
	public void delaccount(Long id) {
		
		Account account = repo.findById(id).orElseThrow(() -> new RuntimeException("Account does not excists"));
		
		repo.delete(account);
		
	}

	

}
