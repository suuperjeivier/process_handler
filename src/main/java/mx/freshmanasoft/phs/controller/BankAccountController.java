package mx.freshmanasoft.phs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;
import mx.freshmanasoft.phs.service.bankaccount.BankAccountService;

@Controller
@RequestMapping(path="bank-accounts")
public class BankAccountController {
	
	@Autowired
	private BankAccountService service;
	
	@GetMapping
	public @ResponseBody Iterable<BankAccount> getAllBankAccounts(){
		return service.fetch();
	}
	
	@PostMapping
	public BankAccount post(@RequestBody final BankAccount entity) {
		return service.post(entity);
	}
	
	@PutMapping
	public BankAccount put(@RequestBody final BankAccount entity) {
		return service.put(entity);
	}
	
	@DeleteMapping 
	BankAccount delete(@RequestBody final BankAccount entity) {
		return service.delete(entity);
	}
}
