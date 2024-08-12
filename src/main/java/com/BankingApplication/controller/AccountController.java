package com.BankingApplication.controller;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BankingApplication.dto.AccountDto;
import com.BankingApplication.service.AccountService;
//import com.aman.entity.Employee;
@CrossOrigin(origins="http://localhost:4200")

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private AccountService accountservice;

	public AccountController(AccountService accountservice) {
		super();
		this.accountservice = accountservice;
	}

	// Add account rest api
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountdto) {
		System.out.println(accountdto);
		return new ResponseEntity<>(accountservice.Createacount(accountdto), HttpStatus.CREATED);
	}

	// get single account api
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> get(@PathVariable Long id) {
		AccountDto accountdto = accountservice.getaccountbyid(id);

		return ResponseEntity.ok(accountdto);
	}

	// make deposite account api
	@PutMapping("/{id}/deposit")
	public ResponseEntity<AccountDto> depositeamount(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		Double amount = request.get("amount");
		AccountDto accountdto = accountservice.depositeamount(id, amount);

		return ResponseEntity.ok(accountdto);
	}

	// make withdraw account api
	@PutMapping("/{id}/Withdraw")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {

		Double amount = request.get("amount");
		AccountDto accountdto = accountservice.withdraw(id, amount);

		return ResponseEntity.ok(accountdto);
	}

	@GetMapping 
	public ResponseEntity<List<AccountDto>> getallaccounts() {      

		List<AccountDto> accountdto = accountservice.getallaccounts();

		return ResponseEntity.ok(accountdto);

	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String>delaccount(@PathVariable Long id)
	{
		accountservice.delaccount(id);
		
		return ResponseEntity.ok("Account succesfully deleted");
	}
	
	

}
